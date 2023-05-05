package com.example.inventory.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inventory.MyRecyclerViewAdapter
import com.example.inventory.databinding.FragmentArchivePageBinding
import com.example.inventory.model.Item
import com.example.inventory.presenter.presenterImpl.PresenterArchiveImpl
import com.example.inventory.view.ViewArchive

class ArchivePageFragment : Fragment(), ViewArchive {

    lateinit var binding: FragmentArchivePageBinding
    lateinit var presenterArchiveImpl: PresenterArchiveImpl
    private var adapter = MyRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        presenterArchiveImpl = PresenterArchiveImpl(requireContext())
        binding = FragmentArchivePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewArchive.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerViewArchive.adapter = adapter
        presenterArchiveImpl.attachView(this)
        presenterArchiveImpl.getAllArchived()

    }

    override fun showAllArchived(items: LiveData<List<Item>>) {
        items.observe(viewLifecycleOwner, {itemList -> adapter.setItemList(itemList)})
    }

    override fun showError(errorMessage: String) {
        TODO("Not yet implemented")
    }

}