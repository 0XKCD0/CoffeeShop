package com.xyz.coffeeshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xyz.coffeeshop.Model.ItemsModel
import com.xyz.coffeeshop.databinding.ViewholderOfferBinding

class OfferAdapter(val items: MutableList<ItemsModel>) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    private var context: Context? = null
    class ViewHolder(val binding:ViewholderOfferBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderOfferBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferAdapter.ViewHolder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$" + items[position].price.toString()

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {  }
    }

    override fun getItemCount(): Int = items.size
}