package com.example.fit_nutrition.profile.ui.profile

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fit_nutrition.databinding.FragmentCalcBinding
import com.example.fit_nutrition.domain.CalculateCaloricDeficitUseCase
import com.example.fit_nutrition.core.data.preferences.PreferencesManager
import com.example.fit_nutrition.profile.data.ProfileRepositoryImpl
import com.example.fit_nutrition.profile.domain.ProfileRepository
import com.example.fit_nutrition.profile.domain.model.Profile
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by createViewModelLazy(
        ProfileViewModel::class,
        { this.viewModelStore }
    )
    private var _binding: FragmentCalcBinding? = null
    private val binding get() = _binding!!


    private lateinit var profileRepository: ProfileRepository
    private val calculateCaloricDeficitUseCase = CalculateCaloricDeficitUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userSharedPreferences = requireContext().getSharedPreferences(
            "user_prefs",
            Context.MODE_PRIVATE
        )
        val preferencesManager = PreferencesManager(userSharedPreferences)
        profileRepository = ProfileRepositoryImpl(preferencesManager)
        viewModel.sync()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalcBinding.inflate(inflater, container, false)
        // Load saved data
        // loadData()
        // Set save button click listener


        binding.button.setOnClickListener {
            saveData()
            calculateAndShowDeficit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.content.collect { profile ->

                    profile?.let {
                        binding.resultTextView.text

                    }
                }
            }
        }

    }

    private fun saveData() {
        Thread {
            Handler(Looper.getMainLooper()).post {
                val age = binding.Age.text.toString()
                val weight = binding.Weight.text.toString()
                val height = binding.Height.text.toString()
                val selectedGenderId = binding.radioGroupGender.checkedRadioButtonId
                val gender =
                    if (selectedGenderId == binding.radioButtonMale.id) "Male" else "Female"


            }

        }

    }

    private fun loadData() {
        val profile = profileRepository.readProfile()
        binding.Age.setText(profile.age)
        binding.Weight.setText(profile.weight)
        binding.Height.setText(profile.height)
        binding.Height.setText(profile.gender)
        if (profile.gender == "Male") {
            binding.radioGroupGender.check(binding.radioButtonMale.id)
        } else {
            binding.radioGroupGender.check(binding.radioButtonFemale.id)
        }
    }

    private fun calculateAndShowDeficit() {
        val age = binding.Age.text.toString().toIntOrNull() ?: 0
        val weight = binding.Weight.text.toString().toFloatOrNull() ?: 0f
        val height = binding.Height.text.toString().toFloatOrNull() ?: 0f
        val isMale = binding.radioGroupGender.checkedRadioButtonId == binding.radioButtonMale.id

        val caloricDeficit = calculateCaloricDeficitUseCase.execute(age, weight, height, isMale)
        binding.resultTextView.text = "Caloric Deficit: $caloricDeficit kcal"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}
