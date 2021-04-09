package com.googleanalytics.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.googleanalytics.R
import com.googleanalytics.model.CategoriesName
import com.googleanalytics.ui.DetailsActivity

class ProductAdapter(var activity: Activity, var data: MutableList<CategoriesName>) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvProduct: TextView = itemView.findViewById(R.id.tv_product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvProduct.text = data[position].name
        holder.tvProduct.setOnClickListener {
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("categoryName", data[position].name)
            activity.startActivity(intent)
        }

    }


}