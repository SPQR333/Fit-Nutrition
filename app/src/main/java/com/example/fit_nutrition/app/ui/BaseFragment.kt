package com.example.fit_nutrition.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.fit_nutrition.R
import com.example.fit_nutrition.profile.ui.profile.ProfileFragment


class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        val buttonNavigate = view?.findViewById<Button>(R.id.buttonNavigate)
        buttonNavigate?.setOnClickListener {

            val profileFragment = ProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frag_container, profileFragment)
                .addToBackStack(null)
                .commit()

        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {

    }
}
