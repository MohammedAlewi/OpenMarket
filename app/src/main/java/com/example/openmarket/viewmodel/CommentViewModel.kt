    package com.example.openmarket.viewmodel

    import android.app.Application
    import androidx.lifecycle.AndroidViewModel
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.Observer
    import androidx.lifecycle.viewModelScope
    import com.example.openmarket.MainActivity
    import com.example.openmarket.data.Comment
    import com.example.openmarket.data.OpenMarketDatabase
    import com.example.openmarket.repository.CommentRepository
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.async
    import kotlinx.coroutines.launch

    class CommentViewModel(application: Application) : AndroidViewModel(application) {
        var commentRepository: CommentRepository

    init {
        var comment_dao = OpenMarketDatabase.getInstance(application).getCommentDao()
        var product_cmnt_dao = OpenMarketDatabase.getInstance(application).getProductCommentDao()
        commentRepository = CommentRepository(comment_dao, product_cmnt_dao)
    }

    fun insertComment(comment: Comment, product_id: Long) = viewModelScope.launch(Dispatchers.IO) {
        commentRepository.insertComment(comment, product_id)
    }

    fun deleteComment(comment: Comment) = viewModelScope.launch(Dispatchers.IO) {
    //    var comment=commentRepository.getCommentById(id).value
//        comment.observe(commentRepository.activity, Observer {
//           comment->comment.let {  commentRepository.deleteComment(comment) }
//        })
        //commentRepository.deleteComment(comment as Comment)
        commentRepository.deleteComment(comment)
    }

    fun getCommentForProduct(product_id: Long)=viewModelScope.async(Dispatchers.IO){
         commentRepository.getCommentForProduct(product_id)
    }

    fun getCommentByUsername(username: String)=viewModelScope.async(Dispatchers.IO) {
         commentRepository.getCommentByUsername(username)
    }

    fun updateComment(comment: Comment) =
        viewModelScope.launch(Dispatchers.IO) { commentRepository.updateComment(comment) }

    fun getCommentById(id: Long)=viewModelScope.async(Dispatchers.IO){
         commentRepository.getCommentById(id)
    }

    fun setActivtiy(activity: MainActivity) {
        commentRepository.activity = activity
    }
}