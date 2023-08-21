package com.ravzanurcanturk.aboutfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ravzanurcanturk.aboutfood.databinding.ActivityIngredientsDetailBinding

class IngredientsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngredientsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientsDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val selectedIngredient = intent.getSerializableExtra("ingredient") as Ingredient

        binding.ingredientName.text = selectedIngredient.name
        binding.ingredientComment.text = selectedIngredient.comment

    }
}