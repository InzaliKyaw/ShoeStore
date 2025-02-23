package com.example.shoestore.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel:ViewModel() {
    val userName = MutableLiveData<String>()
    val passWord = MutableLiveData<String>()
    val usernameError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()
    val isFormValid = MutableLiveData<Boolean>()
    var isUserValid = MutableLiveData<Boolean>()


    fun validateInput() {
        var isValid = true


        // Username Validation
        if (userName.value.isNullOrEmpty()) {
            usernameError.value = "Username cannot be empty"
            isValid = false
        }  else {
            usernameError.value = null
        }

        // Password Validation
        if (passWord.value.isNullOrEmpty()) {
            passwordError.value = "Password cannot be empty"
            isValid = false
        } else {
            passwordError.value = null
        }
        isFormValid.value = isValid
    }

    fun checkCredential(){
        isFormValid.value?.let {
            if(it){
                if (userName.value == "User1" && passWord.value == "User1"){
                    isUserValid.value = true
                }
            }
        }

    }
}