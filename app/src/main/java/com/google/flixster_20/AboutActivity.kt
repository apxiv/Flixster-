package com.google.flixster_20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class AboutActivity : AppCompatActivity() {
    private lateinit var actorImageView: ImageView
    private lateinit var movieImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var knownForTextView: TextView
    private lateinit var overviewTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_actor)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "About Actor"
        actorImageView = findViewById(R.id.actor_image)
        movieImageView = findViewById(R.id.movie_image)
        nameTextView = findViewById(R.id.actor_name)
        knownForTextView = findViewById(R.id.movie_known)
        overviewTextView = findViewById(R.id.movie_overview)

        val actor = intent.getSerializableExtra(ACTOR_EXTRA) as Actor
        nameTextView.text = actor.name
        knownForTextView.text = actor.knownFor[0].title
        overviewTextView.text = actor.knownFor[0].overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${actor.profilePath}")
            .into(actorImageView)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${actor.knownFor[0].posterPath}")
            .into(movieImageView)
    }
}