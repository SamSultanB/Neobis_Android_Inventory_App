package com.example.inventory.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inventory.MyRecyclerViewAdapter
import com.example.inventory.R
import com.example.inventory.databinding.FragmentMainPageBinding
import com.example.inventory.model.Item
import com.example.inventory.presenter.PresenterImpl

class MainPageFragment : Fragment(), com.example.inventory.view.View {

    lateinit var binding: FragmentMainPageBinding
    private lateinit var presenterImpl: PresenterImpl
    private var adapter = MyRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        presenterImpl = PresenterImpl(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewMain.layoutManager = GridLayoutManager(this.context, 2)
        presenterImpl.attachView(this)
        presenterImpl.getAllItems()
        binding.recyclerViewMain.adapter = adapter
        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainPageFragment_to_createNewCrossFragment)
        }

        //Click to details page
        adapter.onItemClickToDetails = {
            val bundle = Bundle()
            bundle.putParcelable(getString(R.string.bundleKey), it)
            findNavController().navigate(R.id.action_mainPageFragment_to_detailsFragment, bundle)
        }

    }

    override fun showAllItems(items: LiveData<List<Item>>) {
        items.observe(viewLifecycleOwner, {itemList->adapter.setItemList(itemList)})
    }

    override fun showError(error: String) {
        TODO("Not yet implemented")
    }


}