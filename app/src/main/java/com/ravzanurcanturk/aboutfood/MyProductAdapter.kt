package com.ravzanurcanturk.aboutfood

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravzanurcanturk.aboutfood.databinding.RecyclerRowBinding

class MyProductAdapter (val productList: ArrayList<MyProduct>) : RecyclerView.Adapter<MyProductAdapter.MyProductHolder>(){
    class MyProductHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProductHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProductHolder(binding)
    }

    override fun onBindViewHolder(holder: MyProductHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = productList.get(position).name

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MyProductDetailActivity::class.java)
            intent.putExtra("product", productList.get(position))
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

}