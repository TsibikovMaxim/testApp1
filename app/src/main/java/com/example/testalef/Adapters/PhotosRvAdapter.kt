package com.example.testalef.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testalef.R

class PhotosRvAdapter(var context: Context, var list: List<String>, val listener1: PhotosRvAdapter.onItemClickListener): RecyclerView.Adapter<PhotosRvAdapter.ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        Glide.with(context)
            .load(list[position])
            .placeholder(R.drawable.gray_place)
            .error(R.drawable.ic_close)
            .centerCrop()
            .into(holder.photo_iv)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var photo_iv = itemView.findViewById<ImageView>(R.id.photo_item_iv)
        init{
            photo_iv.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            listener1.onItemClick(position)
        }
    }
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
}