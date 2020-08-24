package com.example.simplemovieapp.fragments.moviedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.entities.localmodels.Cast
import com.example.simplemovieapp.R
import kotlinx.android.synthetic.main.item_top_cast.view.*

class TopCastAdapter(
    private var itemList: List<Cast>
) :
    RecyclerView.Adapter<TopCastAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_cast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: Cast) {
            with(itemView) {
                vMoveItemName.text = item.name?.run { this }
                vMoveItemCharacter.text = item.character?.run { this }
                item.profilePath?.run {
                    Glide.with(context)
                        .load(this)
                        .into(vMoveItemPhoto)
                }
            }
        }
    }

}
