package com.example.openmarket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.openmarket.data.Comment
import com.example.openmarket.databinding.CommentsListsBinding
import com.example.openmarket.viewmodel.CommentViewModel
import kotlinx.android.synthetic.main.comments_lists.view.*
import kotlinx.android.synthetic.main.fragment_product_detail.view.*

class CommentItemAdapter(private var comment_items: List<Comment>,private val activity: MainActivity,
                         var commentViewModel: CommentViewModel,var comentBoxView: View) :
    RecyclerView.Adapter<CommentItemAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding =
            DataBindingUtil.inflate<CommentsListsBinding>(inflater, R.layout.comments_lists, parent, false)
        var recyclerview = binding.root
        return CommentViewHolder(recyclerview, binding)
    }

    override fun getItemCount(): Int {
        return comment_items.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var comment = comment_items[position]
        holder.binding.comment = comment
        holder.binding.commentViewModel=commentViewModel
        var username =
            activity.getSharedPreferences("user_login", Context.MODE_PRIVATE).getString("username", "unknown")
        if ("@$username" ==comment.userName){
            holder.itemview.del_cmnt_btn.visibility=View.VISIBLE
            //holder.itemview.edit_cmnt_btn.visibility=View.VISIBLE
        }
    }

    fun addComment(comments: List<Comment>) {
        comment_items = comments
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(var itemview: View, binding: CommentsListsBinding) : RecyclerView.ViewHolder(itemview) {
        var binding = binding
    }
}

