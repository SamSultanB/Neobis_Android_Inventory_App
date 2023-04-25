package com.example.inventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inventory.databinding.FragmentArchivePageBinding

class ArchivePageFragment : Fragment() {

    lateinit var binding: FragmentArchivePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArchivePageBinding.inflate(inflater, container, false)
        return binding.root
    }

}