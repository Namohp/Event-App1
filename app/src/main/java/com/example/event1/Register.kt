package com.example.event1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.event1.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()


        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rb.setOnClickListener {
            val email = binding.re.text.toString()
            val password = binding.rp.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this@Register , Login ::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Registration Unsuccessful", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(this, "Fields should not be empty", Toast.LENGTH_SHORT).show()
            }

        }
        binding.LoginText.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
            finish()
        }

    }
}