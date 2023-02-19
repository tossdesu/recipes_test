package com.tossdesu.recipestest.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.tossdesu.recipestest.domain.entity.Recipe

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}