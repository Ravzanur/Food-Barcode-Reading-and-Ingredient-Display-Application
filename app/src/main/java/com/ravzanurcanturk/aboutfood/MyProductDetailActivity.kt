package com.ravzanurcanturk.aboutfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ravzanurcanturk.aboutfood.databinding.ActivityMyProductDetailBinding

class MyProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyProductDetailBinding
    //val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var db : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProductDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        db = Firebase.firestore

        val intent = intent
        val selectedProduct = intent.getSerializableExtra("product") as MyProduct

        binding.productName.text = selectedProduct.name
        binding.productDescription.text = selectedProduct.description


        binding.deleteButton.setOnClickListener {

            val query = db.collection("MyProducts")
                .whereEqualTo("name", binding.productName.text)

            query.get()
                .addOnSuccessListener { querySnapshot ->
                    // Sorgu başarılı bir şekilde tamamlandığında yapılacak işlemler
                    for (document in querySnapshot.documents) {
                        // Her bir belgeyi silin
                        document.reference.delete()
                            .addOnSuccessListener {
                                // Belge başarıyla silindiğinde yapılacak işlemler
                                val intent = Intent(this@MyProductDetailActivity, HomePageActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { error ->
                                // Belge silme başarısız olduğunda yapılacak işlemler ve hata işleme
                            }
                    }
                }
                .addOnFailureListener { error ->
                    // Sorgu başarısız olduğunda yapılacak işlemler ve hata işleme
                }

        }

        binding.notesButton.setOnClickListener {

            val intent = Intent(this@MyProductDetailActivity, NotesActivity::class.java)
            intent.putExtra("name", binding.productName.text.toString())
            startActivity(intent)
        }


    }


}


