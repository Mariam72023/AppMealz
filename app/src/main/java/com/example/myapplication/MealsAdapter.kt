package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Category
import com.example.myapplication.databinding.ItemMealsCategoryBinding

class MealsAdapter() : ListAdapter<Category, MealsAdapter.ViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =ItemMealsCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val itemBinding: ItemMealsCategoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: Category) {
            itemBinding.categoryName.text = category.strCategory
            itemBinding.categoryDesc.text = category.strCategoryDescription
            Glide.with(itemBinding.root.context).load(category.strCategoryThumb).into(itemBinding.categoryImage)
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem == newItem
        }
    }
}



/*
class MealsAdapter : RecyclerView.Adapter<MealsAdapter.MealsCategoryViewHolder>() {
    private lateinit var list: List<Category>
    fun setList(newList: List<Category>) {
        val diffResult = DiffUtil.calculateDiff(CategoryDiffUtil(list, newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MealsCategoryViewHolder(private val bind: ItemMealsCategoryBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bind(category: Category) {
            bind.categoryName.text = category.strCategory
            bind.categoryDesc.text = category.strCategoryDescription
            Glide.with(itemView.context).load(category.strCategoryThumb).into(bind.categoryImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bind = ItemMealsCategoryBinding.inflate(inflater, parent, false)
        return MealsCategoryViewHolder(bind)

    }

    override fun getItemCount(): Int {
     return  list.size
    }

    override fun onBindViewHolder(holder: MealsCategoryViewHolder, position: Int) {
val currentItem=list[position]
        holder.bind(currentItem)
    }
}

class CategoryDiffUtil(private val oldList: List<Category>, private val newList: List<Category>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].idCategory == newList[newItemPosition].idCategory
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}*/