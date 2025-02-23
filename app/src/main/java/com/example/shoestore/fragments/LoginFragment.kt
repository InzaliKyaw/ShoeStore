package com.example.shoestore.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding
import com.example.shoestore.model.ShoeViewModel
import com.example.shoestore.model.UserViewModel


class LoginFragment : Fragment() {

    private val userviewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    /*
    Why {} Doesn't Work, but () Does
💡 In Kotlin, there are two ways to pass a function as an argument:

Using a lambda {}

The function inside {} executes immediately.
The return value of the block is ignored if it's not explicitly used.
Passing a function result using ()

The function result is evaluated first, then passed as an argument.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this

        binding.apply {
            viewModel = userviewModel
             this.btnLogin.setOnClickListener {
                 userviewModel?.validateInput()
                 userviewModel?.checkCredential()
                 // If the input is valid, save the username in SharedPreferences and navigate to the WelcomeFragment.
                 userviewModel.isUserValid.value?.let {
                     if (it){
                         sharedPref?.let {
                             with (sharedPref.edit()) {
                                 putString("USERNAME", userviewModel.userName.value)
                                 apply()
                             }
                             findNavController(requireView()).navigate(R.id.action_loginFragment_to_welcomeFragment)
                         }
                     }
                 }
             }
            // Observe validation error messages from the UserViewModel for the username and password fields.
            // When an error message is not null or empty, display it in the respective TextInputLayout's error property.

            // Observing the username error LiveData to show validation errors in the userLayout.
            userviewModel?.usernameError?.observe(viewLifecycleOwner) { errorMessage ->
                if (!errorMessage.isNullOrEmpty())
                    this.userLayout.error = errorMessage
            }

            // Observing the password error LiveData to show validation errors in the passLayout.
            userviewModel?.passwordError?.observe(viewLifecycleOwner) { errorMessage ->
                if (!errorMessage.isNullOrEmpty())
                    this.passLayout.error = errorMessage
            }



        }

        // Inflate the layout for this fragment
        return binding.root
    }
}