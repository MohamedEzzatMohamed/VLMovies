package com.example.vlmovies.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.vlmovies.R
import com.example.vlmovies.databinding.FragmentMovieDetailsBinding
import com.example.vlmovies.databinding.FragmentMovieListBinding
import com.example.vlmovies.ui.movieslist.MovieListViewModel
import com.example.vlmovies.ui.movieslist.adapter.MovieListAdapter

class MovieDetailsFragment : Fragment() {

    private lateinit var binding : FragmentMovieDetailsBinding
    private val mArgs: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by lazy {
        ViewModelProvider(this)[MovieDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get movie id and get details
        viewModel.movieId = mArgs.extraMovieId
        viewModel.getMovieDetails()
        // set actionBar title with the movie title that selected
        (activity as AppCompatActivity).supportActionBar?.title = mArgs.extraMovieName

    }

}