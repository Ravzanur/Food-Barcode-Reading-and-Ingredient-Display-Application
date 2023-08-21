package com.ravzanurcanturk.aboutfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ravzanurcanturk.aboutfood.databinding.ActivityProductPageBinding
import android.R
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

class ProductPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProductPageBinding
    private lateinit var db : FirebaseFirestore

    //var tempIngredientsArray: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val barcode = intent.getStringExtra("barcode").toString()

        binding.barcodeText.text = barcode.toString()

        db = Firebase.firestore

        getData(barcode)

    }

    fun getData(barcode : String){

        //var ingredientsArray: Array<String> = arrayOf()

        db.collection("Products")
            .whereEqualTo("Barcode", barcode)
            .addSnapshotListener { value, error ->
                if (error != null){
                    Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
                }else{
                    if (value != null){
                        if (!value.isEmpty){

                            val documents = value.documents

                            for (document in documents){

                                binding.barcodeText.text = document.get("Barcode") as String
                                binding.productNameText.text = document.get("Name") as String

                                val text = document.get("Ingredients").toString()
                                val ingredientsArray = text.split(",").toTypedArray()

                                //val array = arrayOf<String>()
                                //val array = text.split(",").toTypedArray()
                                //binding.ingredientsText.text = text.split(",").toTypedArray().toString()

                                var dizi : String = ""
                                for (eleman in ingredientsArray){
                                    dizi += eleman + "\n"
                                }
                                binding.ingredientsText.text = dizi

                                /*val intent = Intent(this@ProductPageActivity, IngredientsPageActivity::class.java)
                                intent.putExtra("ingredients", ingredientsArray)
                                //binding.ingredientsText.text = document.get("Ingredients") as String
                                startActivity(intent)*/

                            }
                        }
                    }

                }
            }



    }

    fun showProductDetail(view: View){

        //val tempText = text
        val tempText = binding.ingredientsText.text.toString()
        //val tempIngredientsArray = tempText.split("\n").toTypedArray()


        /*intent.putExtra("barcode",  binding.barcodeText.text)
        intent.putExtra("barcode",   binding.productNameText.text)
        intent.putExtra("barcode",  binding.ingredientsText.text)*/
        val intent = Intent(this@ProductPageActivity, IngredientsPageActivity::class.java)
        intent.putExtra("ingredients", tempText)
        startActivity(intent)

    }

    fun addLibrary(view: View){

        val postMap = hashMapOf<String,Any>()
        postMap.put("barcode",binding.barcodeText.text.toString())
        postMap.put("name",binding.productNameText.text.toString())
        postMap.put("description",binding.ingredientsText.text.toString())
        postMap.put("date",Timestamp.now())

        db.collection("MyProducts").add(postMap).addOnSuccessListener {task ->

            Toast.makeText(applicationContext,"Ürün eklendi.",Toast.LENGTH_LONG).show()
                finish()
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }



}
