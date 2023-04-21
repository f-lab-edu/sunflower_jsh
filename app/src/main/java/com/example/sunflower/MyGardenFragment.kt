package com.example.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sunflower.databinding.FragmentMyGardenBinding

class MyGardenFragment : Fragment() {
    private lateinit var binding: FragmentMyGardenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyGardenBinding.inflate(inflater, container, false)
        return binding.root
    }
}
