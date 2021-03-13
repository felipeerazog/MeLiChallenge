package com.guerrero.melichallenge.products.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guerrero.melichallenge.R
import com.guerrero.melichallenge.databinding.ItemViewHolderBinding
import com.guerrero.melichallenge.products.business.Product

/**
 * Author: Felipe Guerrero
 */
class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemViewHolderBinding.bind(view)

    fun bind(product: Product) {
        binding.tvTitle.text = product.title
        binding.tvPrice.text = product.getPriceInCurrencyFormat()
        Glide
            .with(itemView.context)
            .load(product.thumbnail)
            .placeholder(R.drawable.ic_product_without_image)
            .into(binding.ivProductImage)
    }

}
