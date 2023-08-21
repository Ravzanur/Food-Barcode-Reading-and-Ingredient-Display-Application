package com.ravzanurcanturk.aboutfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ravzanurcanturk.aboutfood.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth


    }


    fun signUpClicked(view : View){

        val email = binding.emailRegisterText.text.toString()
        val password = binding.passwordRegisterText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this, "Lütfen email ve şifre alanını doldurunuz!!", Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val intent = Intent(this@SignUpActivity, HomePageActivity::class.java)
                    Toast.makeText(this@SignUpActivity, "Üye olduğunuz için teşekkür ederiz :)", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                    finish()

                }.addOnFailureListener{
                    Toast.makeText(this@SignUpActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }

    }
}