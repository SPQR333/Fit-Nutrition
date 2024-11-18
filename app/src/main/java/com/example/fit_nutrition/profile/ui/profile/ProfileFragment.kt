package com.example.fit_nutrition.profile.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fit_nutrition.core.data.preferences.PreferencesManager
import com.example.fit_nutrition.databinding.FragmentCalcBinding
import com.example.fit_nutrition.profile.data.ProfileRepositoryImpl
import com.example.fit_nutrition.profile.data.di.MyApplication
import com.example.fit_nutrition.profile.domain.ProfileRepository
import com.example.fit_nutrition.profile.domain.model.Profile
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProfileViewModel


    private val profileRepository: ProfileRepository by lazy {
        val profileSharedPreferences = requireContext().getSharedPreferences(
            "user_prefs",
            Context.MODE_PRIVATE
        )
        val preferencesManager = PreferencesManager(profileSharedPreferences)
        ProfileRepositoryImpl(preferencesManager)
    }

   /* private val viewModel: ProfileViewModel by createViewModelLazy(
        viewModelClass = ProfileViewModel::class,
        storeProducer = { viewModelStore },
        factoryProducer = {
            ProfileViewModelFactory(
                profileRepository,
                CalculateCaloricDeficitUseCase()
            )
        }
    )
*/
    private var binding: FragmentCalcBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        (requireActivity().application as MyApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this,viewModelFactory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCalcBinding.inflate(inflater, container, false)
            .also { initButton() }
            .root

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.contRes.collect { result ->
                result?.let {
                    binding?.tvCalories?.text = it
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.content.collect { profile ->
                    profile?.let {
                        binding?.etAge?.setText(it.age)
                        binding?.etHeight?.setText(it.height.toString())
                        binding?.etWeight?.setText(it.weight.toString())

                    }
                }


            }
            viewModel.contRes.collect{result ->
                result.let {
                    binding?.tvCalories?.text = it
                }
            }
        }

    }

    private fun initButton() {
        binding?.btnApply?.setOnClickListener {
            val selectedGenderId = binding?.rgGender?.checkedRadioButtonId
            val gender = if (selectedGenderId == binding?.rbMale?.id) "Male" else "Female"

            viewModel.saveProfile(
                Profile(
                    age = binding?.etAge?.text.toString().toInt(),
                    weight = binding?.etWeight?.text.toString().toFloat(),
                    height = binding?.etHeight?.text.toString().toFloat(),
                    gender = gender
                )
            )
            viewModel.calculateCalories(
                Profile(
                    age = binding?.etAge?.text.toString().toInt(),
                    weight = binding?.etWeight?.text.toString().toFloat(),
                    height = binding?.etHeight?.text.toString().toFloat(),
                    gender = gender
            )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }
}
