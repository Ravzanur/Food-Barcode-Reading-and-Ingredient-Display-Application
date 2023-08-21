package com.ravzanurcanturk.aboutfood

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravzanurcanturk.aboutfood.databinding.RecyclerRowBinding

class IngredientAdapter(val ingredientList: ArrayList<Ingredient>) : RecyclerView.Adapter<IngredientAdapter.IngredientHolder>() {

    class IngredientHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = ingredientList.get(position).name

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, IngredientsDetailActivity::class.java)
            intent.putExtra("ingredient", ingredientList.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }


}