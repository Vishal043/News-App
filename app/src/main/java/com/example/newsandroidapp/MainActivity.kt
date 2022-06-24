package com.example.newsandroidapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity : AppCompatActivity(), NewsItemClicked {


   private lateinit var mAdapter: NewsListadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
          fetchData()
       mAdapter = NewsListadapter(this)
        recyclerview.adapter = mAdapter


    }

    private fun fetchData() {
        val url = "https://saurav.tech/NewsAPI/everything/cnn.json"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                  val newJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<MyData>()
                for (i in 0 until newJsonArray.length()){
                    val newsJsonObject = newJsonArray.getJSONObject(i)
                    val news = MyData(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")

                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

        }

     override fun onItemClicked(items: MyData) {

         val builder = CustomTabsIntent.Builder()
         val customTabsIntent = builder.build()
         customTabsIntent.launchUrl(this, Uri.parse(items.url))
    }
}


 



