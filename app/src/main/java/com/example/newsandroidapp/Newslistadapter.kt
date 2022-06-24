package com.example.newsandroidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsListadapter( private val listener: NewsItemClicked):RecyclerView.Adapter<NewViewHolder>(){
    private val items: ArrayList<MyData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewViewHolder(view)
        view.setOnClickListener{

            listener.onItemClicked(items[viewHolder.adapterPosition])

        }
        return viewHolder
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
   val currentItem = items[position]
        holder.titleView.text= currentItem.title
        holder.author.text= currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }


fun updateNews(updateNews: ArrayList<MyData>){
    items.clear()
    items.addAll(updateNews)
    notifyDataSetChanged()

}

}
class NewViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val titleView:TextView = itemView.findViewById(R.id.title)
    val image : ImageView = itemView.findViewById(R.id.image)
    val author : TextView = itemView.findViewById(R.id.authorname)
}
interface NewsItemClicked{
    fun onItemClicked(item: MyData)
}