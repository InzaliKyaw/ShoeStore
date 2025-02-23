package com.example.shoestore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.shoestore.databinding.ShoeListItemBinding
import com.example.shoestore.vos.Shoes

class ShoeAdapter(private val context: Context, private val items: MutableList<Shoes>) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Shoes = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ShoeListItemBinding
        val view: View

        if (convertView == null) {
            binding = ShoeListItemBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding

        } else {
            view = convertView
            binding = view.tag as ShoeListItemBinding
        }
//        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
//        layoutParams.setMargins(16, 16, 16, 16) // Left, Top, Right, Bottom
//        view.layoutParams = layoutParams
        binding.item = items[position]  // Data Binding happens here
        return view
    }

    // Function to update data and refresh ListView
    fun setNewData(newList: List<Shoes>) {
        items.clear()          // Clear old data
        items.addAll(newList)  // Add new data
        notifyDataSetChanged() // Refresh UI
    }
}
