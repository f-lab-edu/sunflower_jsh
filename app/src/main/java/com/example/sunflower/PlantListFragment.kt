package com.example.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunflower.databinding.FragmentPlantListBinding

class PlantListFragment : Fragment() {

    private lateinit var binding: FragmentPlantListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
