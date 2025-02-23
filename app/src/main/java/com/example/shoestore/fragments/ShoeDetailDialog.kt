package com.example.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.shoestore.R
import com.example.shoestore.databinding.BottomSheetLayoutBinding
import com.example.shoestore.model.ShoeViewModel
import com.example.shoestore.vos.Shoes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShoeDetailDialog : BottomSheetDialogFragment() {
     private lateinit var binding: BottomSheetLayoutBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = DataBindingUtil.inflate(
            inflater, R.layout.bottom_sheet_layout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set the view height
//        view.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

        binding.submitButton.setOnClickListener {

            val shoeName = binding.txtShoeName.text?.toString()
            val shoeDescription = binding.txtShoeDescription.text?.toString()

            if (shoeName.isNullOrEmpty()) {
                binding.txtShoeName.error = "Please Enter Shoe Name"
            }
            if (shoeDescription.isNullOrEmpty()) {
                binding.txtShoeDescription.error = "Please Enter Shoe Description"
            }
            if (!shoeName.isNullOrEmpty() && !shoeDescription.isNullOrEmpty()) {
                val shoe = Shoes(shoeName, shoeDescription)
                viewModel.addNewShoe(shoe) // Add the new shoe to the shared ViewModel
                dismiss()
            }
        }
    }


}
