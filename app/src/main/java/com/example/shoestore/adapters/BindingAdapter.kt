package com.example.shoestore.adapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
 fun TextInputLayout.setErrorText(errorMessage: String?) {
    this.error = errorMessage
}
