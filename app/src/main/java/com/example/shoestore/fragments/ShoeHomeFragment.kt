package com.example.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.shoestore.R
import com.example.shoestore.adapters.ShoeAdapter
import com.example.shoestore.databinding.FragmentShoeHomeBinding
import com.example.shoestore.model.ShoeViewModel
import com.example.shoestore.vos.Shoes


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeHomeFragment : Fragment() {
    /**
     * by: Indicates that the property’s getter and setter behavior is delegated to another object
     * Without by viewModels()
     * private val viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)
     * With by viewModels():
     * private val viewModel: ShoeViewModel by viewModels()
     */

    private val viewModel: ShoeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_home, container, false
        )
        val adapter = ShoeAdapter(requireContext(), mutableListOf())
        binding.listView.adapter = adapter

        viewModel.getShoesList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setNewData(it.toMutableList())
            }
        })

        binding.addFab.setOnClickListener {
            val bottomSheet = ShoeDetailDialog()
            fragmentManager?.let {
                    it1 -> bottomSheet.show(it1, "MyBottomSheetDialog")

            }


        }

        // Inflate the layout for this fragment
        return binding.root
    }

}