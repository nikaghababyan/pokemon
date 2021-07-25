package com.example.pokemon.fragment.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.entity.localmodels.PokemonItem
import com.example.pokemon.R
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter(
    private var itemList: List<PokemonItem>,
    var data: (pokemonUrl: String) -> Unit,
) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateList(list: List<PokemonItem>?) {
        list?.let {
            itemList = it
            notifyDataSetChanged()
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: PokemonItem) {
            with(itemView) {
                vPokemonName.text = item.name

                item.imageUrl?.let {
                    context?.run {
                        Glide.with(this)
                            .load(it)
                            .centerCrop()
                            .into(vPokemonIcon)
                    }
                }
            }

            itemView.setOnClickListener {
                item.url?.apply(data)
            }

        }
    }
}