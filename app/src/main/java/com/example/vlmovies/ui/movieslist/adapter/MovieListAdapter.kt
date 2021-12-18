package com.example.vlmovies.ui.movieslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vlmovies.databinding.ItemListBinding
import com.example.vlmovies.model.Movie
import com.example.vlmovies.ui.movieslist.MoviesListNavigator

class MovieListAdapter (
    private var moviesList: ArrayList<Movie>,
    private var itemListener: MoviesListNavigator
    ) : RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    //bind the views from viewHolder with the data in arrayList
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = moviesList[position]
        holder.bind(itemListener, item)
    }

    //return num of array size
    override fun getItemCount() = moviesList.size

    class MoviesViewHolder(private val binding: ItemListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: MoviesListNavigator, item: Movie) {
            binding.movie = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MoviesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(layoutInflater, parent, false)

                return MoviesViewHolder(binding)
            }
        }
    }

    //function for handling any data repetition
    fun addMovies(moviesList: ArrayList<Movie>){
        this.moviesList.apply {
            clear()
            addAll(moviesList)
        }
    }
}