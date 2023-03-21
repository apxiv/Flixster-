package com.google.flixster_20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.flixster_20.databinding.ActivityMainBinding
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val ARTICLE_SEARCH_URL =
    "https://api.themoviedb.org/3/person/popular?api_key=${BuildConfig.API_KEY}"

class MainActivity : AppCompatActivity() {
    private val actors = mutableListOf<Actor>()
    private lateinit var actorsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        actorsRecyclerView = findViewById(R.id.actors)
        val actorAdapter = ActorAdapter(this, actors)
        actorsRecyclerView.adapter = actorAdapter

        actorsRecyclerView.layoutManager = GridLayoutManager(this, 2)

        val client = AsyncHttpClient()
        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch actors: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched actors: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        Response.serializer(),
                        json.jsonObject.toString()
                    )

                    parsedJson.response?.let { list ->
                        actors.addAll(list)
                    }

                    actorAdapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

    }
}