package com.example.openmarket

import com.example.openmarket.data.Comment
import kotlinx.android.synthetic.main.comments_lists.view.*

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CommentItemAdapter(private var comment_items: Array<Comment>)
    :RecyclerView.Adapter<CommentItemAdapter.CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemAdapter.CommentViewHolder {
        var recyclerview=LayoutInflater.from(parent.context).inflate(R.layout.comments_lists,parent,false)

        return CommentViewHolder(recyclerview)
    }

    override fun getItemCount(): Int {
        return comment_items.size
    }

    override fun onBindViewHolder(holder: CommentItemAdapter.CommentViewHolder, position: Int) {
        var comment=comment_items[position]
        holder.commentBody.text=comment.commentdata
        holder.commentUsername.text=comment.userName
        holder.commentDate.text=comment.dateOfComment
    }
    fun addComment(comments: Array<Comment>){
        comment_items=comments
        notifyDataSetChanged()
    }

    inner  class  CommentViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        var commentBody=itemview.comment_body
        var commentDate=itemview.comment_date
        var commentUsername=itemview.comment_username
    }
}

