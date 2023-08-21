package com.ravzanurcanturk.aboutfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ravzanurcanturk.aboutfood.databinding.ActivityIngredientsDetailBinding
import com.ravzanurcanturk.aboutfood.databinding.ActivityIngredientsPageBinding

class IngredientsPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIngredientsPageBinding
    private lateinit var ingredientList : ArrayList<Ingredient>
    private lateinit var db : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientsPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ingredientList = ArrayList<Ingredient>()

        db = Firebase.firestore

        /*
        val ingr1 = Ingredient("Şeker", "aa")
        val ingr2 = Ingredient("Nişasta", "aa")
        val ingr3 = Ingredient("Peynir Altı Suyu Tozu", "aaa")
        val ingr4 = Ingredient("Kıvam Arttırıcı (Guar Gam)", "Guar zamkı, gıda, yem ve endüstriyel uygulamalarda kıvam arttırıcı ve dengeleyici faydalı özelliklere sahip guar fasulyelerinden ekstrakte edilen bir galaktomannan polisakkarittir. Guar tohumlarının kabuğu mekanik olarak alınır, su ile karıştırılır, öğütülür ve uygulamaya göre elenir. Aşırı kullanım sonucu vücutta gaz, ishal, şişkinlik ve kramplar gibi sindirim sorunlarına yol açar.")
        val ingr5 = Ingredient("Salep Tozu", "aa")
        val ingr6 = Ingredient("Tarçın", "Tarçın, yaprak dökmeyen ve aromatik kokulu bir ağaç çeşidini oluşturuyor. Ağacın iç kabuğunun kurutulup öğütülmesi ile elde ediliyor. Bu kabukların iç içe konularak rulo gibi kıvrılmasıyla ise çubuk tarçın üretiliyor. Keskin kokusu, tatlı ve yakıcı lezzetiyle tarçın bir baharat olarak pek çok tarifte kullanılıyor.")
        val ingr7 = Ingredient("Aroma Verici(salep)", "aa")
        val ingr8 = Ingredient("İyotlu Tuz", "aaa")
*/
        val intent = intent
        //val ingredientArray = intent.getStringArrayExtra("ingredients")
        val ingredientString = intent.getStringExtra("ingredients")

        if (ingredientString != null) {
            getIngredients(ingredientString)
        }
        /*if (ingredientArray != null) {
            getIngredients(ingredientArray)
        }*/
        //getIngredients()
        /*
        ingredientList.add(ingr1)
        ingredientList.add(ingr2)
        */


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = IngredientAdapter(ingredientList)
        binding.recyclerView.adapter = adapter



    }

    fun getIngredients(ingredients : String){

        val tempIngredientsArray = ingredients.split("\n").toTypedArray()
        //for (eleman in ingredients)

        for (eleman in tempIngredientsArray){
            var name = eleman
            var comment:String = ""



            db.collection("Ingredients")
                .whereEqualTo("name", name)
                .addSnapshotListener { snapshot, exception ->

                    if(exception != null){
                        //Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
                        Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
                    }else{
                        if(snapshot != null){
                            if(!snapshot.isEmpty){

                                val documents = snapshot.documents
                                for (document in documents){

                                    comment = document.get("description") as String

                                }

                            }
                        }
                    }



                }
            comment = "Adını çoğumuz duymamış olabilir; asesülfam potasyum, en sık kullanılan, aynı zamanda tartışmalı yapay tatlandırıcılardan biridir. " +
                    "Şekerden iki yüz kat daha tatlı olmasına rağmen kalori olarak bir değeri yoktur. Dil üzerindeki tat reseptörlerini uyararak etkisini gösterir. Böylece birçok yiyecek ve içecekten kalori almadan çok daha fazla tat alma imkanı sunmaktadır. Aspartam ve sükraloz gibi diğer tatlandırıcılar ile birlikte acı tatlarını maskelemek için yiyecekler içinde kullanılan formları da bulunmaktadır. Asesülfam potasyumun ilginç yanı vücutta değişime uğramaz, depolanmaz, tamamı idrara geçerek atılır."

            var ingredient = Ingredient(name, comment)
            ingredientList.add(ingredient)

        }

    }






}