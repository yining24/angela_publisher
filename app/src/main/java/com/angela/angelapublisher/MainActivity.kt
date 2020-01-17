package com.angela.angelapublisher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.angela.angelapublisher.databinding.ActivityMainBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*






class MainActivity : AppCompatActivity() {


    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
//        binding.viewModel = viewModel

        addData()

        FirebaseFirestore.getInstance()
            .collection("articles")
            .get()
            .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.i("view model get articles", document.id + " => " + document.data)
                            val items = document.toObject(Articles::class.java)

                            binding.mainContent.text = items.toString()
                        }
                    }
                    else {
                        Log.i("get articles", "Error getting documents.", task.exception)
                    }
                }


        setUpRecyclerView()
    }


    private fun setUpRecyclerView() {
        val query = db.collection("articles")
            .orderBy("author", Query.Direction.ASCENDING)

        val options = FirestoreRecyclerOptions.Builder<Articles>()
            .setQuery(query, Articles::class.java!!)
            .build()

        adapter = MainAdapter(options)

        val recyclerView = binding.articleRecyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.setAdapter(adapter)
    }



    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
            adapter.stopListening()
    }


}


fun addData() {
    val articles = FirebaseFirestore.getInstance().collection("articles")

    val document = articles.document()

    val data = hashMapOf(
        "author" to hashMapOf(
            "email" to "wayne@school.appworks.tw",
            "id" to "waynechen323",
            "name" to "AKA小安老師"
        ),
        "title" to "IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值\n" + "撐住女神氣場",
        "content" to "南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品\n" +
                "都有亮眼的成績，但俗話說人無完美、美玉微瑕，曾再跟工作人員的互動影片中坦言\n" +
                "自己品味很奇怪，近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」超復古穿搭\n" +
                "造型，卻意外美出新境界。",
        "createdTime" to Calendar.getInstance().timeInMillis,
        "id" to document.id,
        "tag" to "Beauty"
    )
    document.set(data)
}



