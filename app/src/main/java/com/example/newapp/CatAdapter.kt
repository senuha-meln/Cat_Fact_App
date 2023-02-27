package com.example.newapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.*

class CatAdapter(private val catList: List<CatFact>, private val listener: Listener) :
    RecyclerView.Adapter<CatAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        val constraint: ConstraintLayout

        init {
            textView = view.findViewById(R.id.textView)
            imageView = view.findViewById(R.id.imageView)
            constraint = view.findViewById(R.id.constraintLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = catList[position]
        holder.textView.text = fact.title
        holder.constraint.setOnClickListener() {
            listener.onClick(fact)
        }
        holder.imageView.visibility = View.VISIBLE
        holder.textView.visibility = View.VISIBLE
        Glide.with(holder.imageView.context).load(fact.url).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return catList.size
    }
}

