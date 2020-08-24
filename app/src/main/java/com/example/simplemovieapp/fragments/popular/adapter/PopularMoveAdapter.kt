package com.example.simplemovieapp.fragments.popular.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.entities.localmodels.PopularMoveItem
import com.example.simplemovieapp.R
import kotlinx.android.synthetic.main.item_popular_move.view.*

class PopularMoveAdapter(
    private var itemList: List<PopularMoveItem>,
    var specialty: (detailMoveId: Int) -> Unit,
) :
    RecyclerView.Adapter<PopularMoveAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_move, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateList(list: List<PopularMoveItem>?) {
        list?.let {
            itemList = it
            notifyDataSetChanged()
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: PopularMoveItem) {
            with(itemView) {
                vPopularItemName.text = item.title
                vPopularItemDate.text = item.releaseDate
                item.scoreCount?.let { rate ->
                    vRateCount.text = rate.toString()
                    pgRateCount.progress = rate
                }

                item.posterPath?.run {
                    Glide.with(context)
                        .load(this)
                        .into(vPopularItemPhoto)
                }

                itemView.setOnClickListener {
                    item.id?.apply(specialty)
                }

            }
        }
    }
}