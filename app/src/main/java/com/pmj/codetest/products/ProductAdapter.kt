package com.pmj.codetest.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmj.codetest.CodeTestApplication
import com.pmj.codetest.R
import kotlinx.android.synthetic.main.item_collection.view.*

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var list: List<Product> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItem(list: List<Product>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class ProductViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
        )

        fun bind(popular: Product) {
            itemView.tvProductTitle.text = popular.title
            itemView.tvProductPrice.text = popular.discount
            Glide.with(CodeTestApplication.instance).load(popular.imageUrl).into(itemView.ivProduct)
        }
    }
}
