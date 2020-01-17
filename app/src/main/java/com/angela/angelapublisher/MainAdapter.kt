package com.angela.angelapublisher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions



class MainAdapter(options: FirestoreRecyclerOptions<Articles>) :
    FirestoreRecyclerAdapter<Articles, MainAdapter.ItemHolder>(options) {

    override fun onBindViewHolder(holder: ItemHolder, position: Int, articles: Articles) {
//        holder.setArticle()
        holder.content.setText(articles.content)
        holder.author.setText(articles.author?.name)
        holder.tag.setText(articles.tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_home_article, parent, false)
        return ItemHolder(v)
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var content: TextView
        var author: TextView
        var tag: TextView

        init {
            content = itemView.findViewById(R.id.article_content)
            author = itemView.findViewById(R.id.article_author)
            tag = itemView.findViewById(R.id.article_tag)
        }


//        fun setArticle(articles: Articles) {
//            val author = itemView.findViewById<TextView>(R.id.article_author)
//            author.text = articles.author.name
//
//            val content = itemView.findViewById<TextView>(R.id.article_content)
//            content.text = articles.content
//
//        }
    }

}




//
//class MainAdapter (options: FirestoreRecyclerOptions<Articles>) : FirestoreRecyclerAdapter<Articles, MainAdapter.ArticlesViewHolder>(options) {
//
//
//    class ArticlesViewHolder(private var binding: ItemHomeArticleBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(articles: Articles) {
//            binding.articles = articles
//            binding.articleAuthor.text = articles.author.name
//            binding.articleContent.text = articles.content
//            binding.articleTag.text = articles.tag
//            binding.articleTitle.text = articles.title
//            binding.articleCreatedTime.text = articles.createdTime.toString()
//            // This is important, because it forces the data binding to execute immediately,
//            // which allows the RecyclerView to make the correct view size measurements
//            binding.executePendingBindings()
//
//        }
//    }
//
////    companion object DiffCallback : DiffUtil.ItemCallback<Articles>() {
////        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
////            return oldItem.id == newItem.id
////        }
////
////        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
////            return oldItem == newItem
////        }
////    }
//
//
////    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
////        val item = getItem(position)
////        holder.bind(item)
////    }
//
//    @Suppress("UNREACHABLE_CODE")
//    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int, item: Articles) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//
//        holder.bind(item)
//    }
//
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int): ArticlesViewHolder {
//        return ArticlesViewHolder(
//            ItemHomeArticleBinding.inflate(
//                LayoutInflater.from(
//                    parent.context
//                ), parent, false
//            )
//
//        )
//    }
//}
