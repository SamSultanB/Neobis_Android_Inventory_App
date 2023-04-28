package com.example.inventory.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.MyRecyclerViewAdapter
import com.example.inventory.databinding.FragmentArchivePageBinding
import com.example.inventory.databinding.FragmentMainPageBinding
import com.example.inventory.model.Cross

class MainPageFragment : Fragment() {

    lateinit var binding: FragmentMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewMain.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerViewMain.adapter = MyRecyclerViewAdapter(arrayListOf(Cross(1, "Nike", 15, "Nke", 15, false), Cross(1, "Nike", 15, "Nke", 15, false)))

    }

}