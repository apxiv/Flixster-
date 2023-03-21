package com.google.flixster_20

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val ACTOR_EXTRA = "ACTOR_EXTRA"

class ActorAdapter(private val context: Context, private val actors: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.actor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = actors[position]
        holder.bind(article)
    }

    override fun getItemCount() = actors.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.actor_image)
        private val titleTextView = itemView.findViewById<TextView>(R.id.actor_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val actor = actors[absoluteAdapterPosition]

            val intent = Intent(context, AboutActivity::class.java)
            intent.putExtra(ACTOR_EXTRA, actor)
            context.startActivity(intent)
        }

        fun bind(actor: Actor) {
            titleTextView.text = actor.name

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/${actor.profilePath}")
                .into(mediaImageView)
        }
    }
}