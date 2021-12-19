package com.example.vlmovies.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vlmovies.model.MovieDetails
import com.example.vlmovies.repository.MoviesRepository
import com.example.vlmovies.repository.MoviesRepositoryImplementer
import com.example.vlmovies.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel : ViewModel() {
    var movieId: Int = 0

    private val repository : MoviesRepository = MoviesRepositoryImplementer()

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val _failed = MutableLiveData<Boolean>()
    val failed: LiveData<Boolean>
        get() = _failed

    private var retry: Int = 0

    init {
        _movieDetails.value = MovieDetails()
        retry = 0
        _isSuccess.value = false
        _failed.value = false
    }

    fun getMovieData() {
        _isSuccess.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMoviesDetails(movieId, Constant.api_key)
            if(response!!.isSuccessful)
            {
                withContext(Dispatchers.Main) {
                    _isSuccess.value = true
                    _movieDetails.value = response.body()!!
                }
            } else {
                retry += 1
                if (retry < 3)
                    getMovieData()
                else
                    _failed.value = true
            }
        }
    }

    // function to retry loading data
    fun tryGetMovieDetails(){
        retry = 0
        _failed.value = false
        getMovieData()
    }
}