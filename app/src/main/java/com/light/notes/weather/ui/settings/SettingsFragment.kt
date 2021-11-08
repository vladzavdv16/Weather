package com.light.notes.weather.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.light.notes.weather.R
import com.light.notes.weather.databinding.FragmentMainBinding
import com.light.notes.weather.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)
    }


}