package com.example.openmarket

import com.example.openmarket.data.Comment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.openmarket.databinding.CommentsListsBinding

class CommentItemAdapter(private var comment_items: List<Comment>)
    :RecyclerView.Adapter<CommentItemAdapter.CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemAdapter.CommentViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var binding=
        DataBindingUtil.inflate<CommentsListsBinding>(inflater, R.layout.comments_lists, parent, false)
        var recyclerview=binding.root
        return CommentViewHolder(recyclerview,binding)
    }

    override fun getItemCount(): Int {
        return comment_items.size
    }

    override fun onBindViewHolder(holder: CommentItemAdapter.CommentViewHolder, position: Int) {
        var comment=comment_items[position]
        holder.binding.comment=comment
    }
    fun addComment(comments: List<Comment>){
        comment_items=comments
        notifyDataSetChanged()
    }

    inner  class  CommentViewHolder(itemview:View,binding:CommentsListsBinding):RecyclerView.ViewHolder(itemview){
        var binding=binding
    }
}

