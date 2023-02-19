package com.tossdesu.recipestest.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
//import coil.load
import com.tossdesu.recipestest.R
import com.tossdesu.recipestest.databinding.ItemRecipeBinding
import com.tossdesu.recipestest.domain.entity.Recipe
import javax.inject.Inject


class RecipesAdapter @Inject constructor() :
    ListAdapter<Recipe, RecipeHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context))
        return RecipeHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        val item = getItem(holder.bindingAdapterPosition)
        with(holder.binding) {
            if (item.image.isNotEmpty()) {
                Glide.with(root.context)
                    .load(item.image)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(ivPhoto)
            } else {
                Glide.with(root.context).clear(ivPhoto)
                ivPhoto.setImageResource(R.drawable.no_image)
            }

//            if (item.image.isNotEmpty()) {
//                ivPhoto.load(item.image) {
//                    placeholder(R.drawable.no_image)
//                }
//            } else {
//                ivPhoto.load(R.drawable.no_image)
//            }
            tvTitle.text = item.name
        }
    }
}