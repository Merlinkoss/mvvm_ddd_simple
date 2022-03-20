package com.demo.anton_kondratiuk.features.countryList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.anton_kondratiuk.databinding.CountryItemBinding
import com.demo.anton_kondratiuk.models.CountryUIModel
import java.util.Collections

class CountryListAdapter(val action: (countryToOpen: CountryUIModel) -> Unit) :
    ListAdapter<CountryUIModel, CountryListAdapter.ItemViewHolder>(CountryDiffCallback()) {

    private lateinit var binding: CountryItemBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ItemViewHolder {
        binding = CountryItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ItemViewHolder(binding, viewGroup.context)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, pos: Int) {
        onBindViewHolder(holder, pos, Collections.emptyList())
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, pos: Int, payload: List<Any>) {
        viewHolder.bind(getItem(pos))
    }

    override fun getItemCount(): Int = currentList.size

    inner class ItemViewHolder(private val itemBinding: CountryItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: CountryUIModel) {
            itemBinding.countryParentContainer.setOnClickListener { action(item) }
            itemBinding.countryName.text = item.name
            item.flagUrl?.let{
                Glide
                        .with(context)
                        .load(it)
                        .thumbnail()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(itemBinding.countryFlag)
            }
        }
    }
}