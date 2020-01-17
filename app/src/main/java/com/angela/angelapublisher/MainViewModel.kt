package com.angela.angelapublisher

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class MainViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val db = FirebaseFirestore.getInstance()


    private var articles: MutableLiveData<List<Articles>> = MutableLiveData()
//
//
//
//    val query = db.collection("articles").orderBy("createdTime", Query.Direction.ASCENDING)
//
//
//
//    fun getArticles(): LiveData<List<Articles>> {
//
//        if (articles == null) {
//            articles = MutableLiveData<List<Articles>>()
//            fetchArticles()
//        }
//        return articles
//    }
//
//
//

}

fun fetchArticles() {
    FirebaseFirestore.getInstance().collection("articles")
        .get()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    Log.i("view model get articles", document.id + " => " + document.data)
                    val items = document.toObject(Articles::class.java)
                    Log.i("view model get itemmmmmmmmm", "$items")
                }
            }
            else {
                Log.i("get articles", "Error getting documents.", task.exception)
            }
        }
}
