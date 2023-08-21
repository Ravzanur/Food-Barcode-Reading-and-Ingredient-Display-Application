package com.ravzanurcanturk.aboutfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ravzanurcanturk.aboutfood.databinding.ActivityLibraryBinding

class LibraryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLibraryBinding
    //private lateinit var myProductList : ArrayList<MyProduct>
    private lateinit var db : FirebaseFirestore

    val productArrayList : ArrayList<MyProduct> = ArrayList()
    var adapter : MyProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        db = Firebase.firestore


        getDataFromFirestore()


        binding.recyclerView1.layoutManager = LinearLayoutManager(this)
        adapter = MyProductAdapter(productArrayList)
        binding.recyclerView1.adapter = adapter


    }





    fun getDataFromFirestore(){

        db.collection("MyProducts")
            .orderBy("date", Query.Direction.ASCENDING)
            .addSnapshotListener{ snapshot, exception->

            if (exception != null) {
                Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        productArrayList.clear()

                        val documents = snapshot.documents
                        for (document in documents) {
                            val name = document.get("name") as String
                            val description = document.get("description") as String

                            //val timestamp = document.get("date") as Timestamp
                            //val date = timestamp.toDate()

                            val product = MyProduct(name, description)
                            productArrayList.add(product)
                        }
                        adapter!!.notifyDataSetChanged()

                    }
                }


            }


        }






    }
}