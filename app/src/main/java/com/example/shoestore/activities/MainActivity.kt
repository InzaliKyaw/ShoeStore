package com.example.shoestore.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.shoestore.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportActionBar?.title = "Home"
        val navController = findNavController(R.id.hostFragment)
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        val userValue = sharedPref.getString("USERNAME", null)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when {
                userValue != null -> {
                    if (destination.id != R.id.shoeHomeFragment) {
                        navController.navigate(R.id.shoeHomeFragment)
                    }
                }

            }
        }

//            when (destination.id) {
//                R.id.loginFragment -> supportActionBar?.hide() // Hide ActionBar for specific Fragment
//                R.id.welcomeFragment -> supportActionBar?.hide()
//                R.id.instructionFragment -> supportActionBar?.hide()
//
//                else -> supportActionBar?.show()  // Show ActionBar for other Fragments
//            }
//        }
    }
}
