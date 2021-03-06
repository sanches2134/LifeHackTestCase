package com.alexjudin.lifehacktestcase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexjudin.lifehacktestcase.R
import com.alexjudin.lifehacktestcase.domain.entity.ResponseItem
import kotlinx.android.synthetic.main.item_preview.view.*

class CompanyAdapter : RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    inner class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<ResponseItem>() {
        override fun areItemsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseItem, newItem: ResponseItem): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: CompanyViewHolder,
        position: Int
    ) {
        val company = differ.currentList[position]
        holder.itemView.apply {
            // Glide.with(this).load(article.img).into(ivArticleImage)
            ivArticleImage.text = company.img
            tvTitle.text = company.name
            setOnClickListener {
                onItemClickListener?.let { it(company) }
            }
        }

    }

    private var onItemClickListener: ((ResponseItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}