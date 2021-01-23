package com.pmj.codetest.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmj.codetest.CodeTestApplication
import com.pmj.codetest.R
import kotlinx.android.synthetic.main.item_carousel.view.*

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    private var list: List<Carousel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItem(list: List<Carousel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class CarouselViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        )

        fun bind(item: Carousel) {
            Glide.with(CodeTestApplication.instance).load(item.imageUrl).into(itemView.ivCarousel)
        }
    }
}