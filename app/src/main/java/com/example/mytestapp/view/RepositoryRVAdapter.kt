package com.example.mytestapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.databinding.RvItemsRepositoriesBinding
import com.example.mytestapp.model.Repository

class RepositoryRVAdapter(private var items: ArrayList<Repository>, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<RepositoryRVAdapter.ViewHolder>()
{
    // =======================================
    //           Superclass Methods
    // =======================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemsRepositoriesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    // =======================================
    //                 Methods
    // =======================================

    fun replaceData(arrayList: ArrayList<Repository>)
    {
        items = arrayList
        notifyDataSetChanged()
    }

    // =======================================
    //           ViewHolder Class
    // =======================================

    class ViewHolder(private val binding: RvItemsRepositoriesBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(repo: Repository, listener: OnItemClickListener?)
        {
            binding.repository = repo
            if (listener != null)
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
            binding.executePendingBindings()
        }
    }

    // =======================================
    //               Interface
    // =======================================

    interface OnItemClickListener
    {
        fun onItemClick(position: Int)
    }
}