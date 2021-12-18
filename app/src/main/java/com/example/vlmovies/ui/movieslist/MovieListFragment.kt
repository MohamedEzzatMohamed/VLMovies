package com.example.vlmovies.ui.movieslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vlmovies.R
import com.example.vlmovies.databinding.FragmentMovieListBinding
import com.example.vlmovies.model.Movie
import com.example.vlmovies.ui.movieslist.adapter.MovieListAdapter

class MovieListFragment : Fragment(), MoviesListNavigator {

    private lateinit var binding : FragmentMovieListBinding

    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this)[MovieListViewModel::class.java]
    }
    private lateinit var moviesListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //val to bind the view with the data
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMoviesRecyclerView()

        viewModel.getMoviesList()

        viewModel.moviesList.observe(viewLifecycleOwner){   moviesList->
            retrieveMaterialList(moviesList)
        }
    }

    override fun onResume() {
        super.onResume()
        // change actionBar name with the application name
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

    }

    //set the main parameters for the recyclerView with the adapter
    private fun setMoviesRecyclerView() {
        binding.moviesListRecyclerView.layoutManager = LinearLayoutManager(context)
        moviesListAdapter = MovieListAdapter(arrayListOf(), this)
        binding.moviesListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.moviesListRecyclerView.context,
                (binding.moviesListRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.moviesListRecyclerView.adapter = moviesListAdapter
    }

    //get the material list from ViewModel
    private fun retrieveMaterialList(moviesListModel: ArrayList<Movie>) {
        moviesListAdapter.apply {
            addMovies(moviesListModel)
            notifyDataSetChanged()
        }
    }

    //item click to go for details fragment
    override fun showMovieDetails(movie: Movie) {
        val action = MovieListFragmentDirections
            .actionMovielistToMoivedetails(movie.id, movie.title)
        findNavController().navigate(action)
    }
}