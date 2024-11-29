package com.example.fit_nutrition.profile.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.createViewModelLazy
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
    private var binding: FragmentCalcBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Внедрение зависимостей через Dagger
        (requireActivity().application as MyApplication).appComponent.inject(this)

        // Инициализация ViewModel через Dagger
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalcBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Настройка UI и кнопок
        initButton()

        // Подписка на данные ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.content.collect { profile ->
                        profile?.let {
                            binding?.etAge?.setText(it.age.toString())
                            binding?.etHeight?.setText(it.height.toString())
                            binding?.etWeight?.setText(it.weight.toString())
                        }
                    }
                }

                launch {
                    viewModel.contRes.collect { result ->
                        result?.let {
                            binding?.tvCalories?.text = it
                        }
                    }
                }
            }
        }
    }

    private fun initButton() {
        binding?.btnApply?.setOnClickListener {
            val selectedGenderId = binding?.rgGender?.checkedRadioButtonId
            val gender = if (selectedGenderId == binding?.rbMale?.id) "Male" else "Female"

            val profile = Profile(
                age = binding?.etAge?.text.toString().toIntOrNull() ?: 0,
                weight = binding?.etWeight?.text.toString().toFloatOrNull() ?: 0f,
                height = binding?.etHeight?.text.toString().toFloatOrNull() ?: 0f,
                gender = gender
            )

            viewModel.saveProfile(profile)
            viewModel.calculateCalories(profile)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // Освобождаем binding, чтобы избежать утечек памяти
    }
}
