package com.example.shoestore.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.vos.Shoes

class ShoeViewModel:ViewModel(){
    private var shoesList = MutableLiveData<List<Shoes>>().apply {
        value = listOf(
        Shoes("Nike Air", "A lightweight running shoe"),
        Shoes("Adidas Ultraboost", "High-performance comfort"),
        Shoes("Puma RS-X", "A stylish and cushioned sneaker"),
        Shoes("Reebok Nano", "Ideal for cross-training workouts"),
        Shoes("Asics Gel-Kayano", "Designed for long-distance running"))
    }

    // Expose LiveData as read-only to the UI
    fun getShoesList() = shoesList

    // Function to add a new fruit
    fun addNewShoe(newShoe: Shoes) {
        val currentList = shoesList.value ?: emptyList()
        shoesList.value = currentList + newShoe // Create a new list with the new fruit

    }

}