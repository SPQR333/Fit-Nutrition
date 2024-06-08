package com.example.fit_nutrition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.example.fit_nutrition.R
import com.example.fit_nutrition.databinding.FragmentCalcBinding
import com.example.fit_nutrition.domain.CalculateCaloricDeficitUseCase

class CalcFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private var _binding: FragmentCalcBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferencesManager: PreferencesManager
    private val calculateCaloricDeficitUseCase = CalculateCaloricDeficitUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalcBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize preferences manager
        preferencesManager = PreferencesManager(requireContext())

        // Load saved data
        loadData()

        // Set save button click listener
        binding.button.setOnClickListener {
            saveData()
            calculateAndShowDeficit()
        }

        return view
    }

    private fun saveData() {
        val age = binding.Age.text.toString()
        val weight = binding.Weight.text.toString()
        val height = binding.Height.text.toString()
        val selectedGenderId = binding.radioGroupGender.checkedRadioButtonId
        val gender = if (selectedGenderId == binding.radioButtonMale.id) "Male" else "Female"

        preferencesManager.saveData(age, weight, height, gender)
    }

    private fun loadData() {
        val data = preferencesManager.loadData()
        binding.Age.setText(data["AGE"])
        binding.Weight.setText(data["WEIGHT"])
        binding.Height.setText(data["HEIGHT"])
        val gender = data["GENDER"]
        if (gender == "Male") {
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
        // Ваши статические методы или константы, если нужно
    }
}
