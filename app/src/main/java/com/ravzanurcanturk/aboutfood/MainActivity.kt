package com.ravzanurcanturk.aboutfood

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ravzanurcanturk.aboutfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        if (currentUser != null){
            val intent = Intent(this@MainActivity, HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun signIn(view : View){
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this, "Lütfen mailinizi ve şifrenizi girin!", Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val intent = Intent(this@MainActivity, HomePageActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }
    }

    fun signUp(view: View){
        val intent = Intent(this@MainActivity, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}