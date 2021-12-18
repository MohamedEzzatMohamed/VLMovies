package com.example.vlmovies.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vlmovies.model.MovieDetails
import com.example.vlmovies.network.RetrofitClass
import com.example.vlmovies.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieDetailsViewModel : ViewModel() {
    var movieId: Int = 0

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    val _failed = MutableLiveData<Boolean>()
    val failed: LiveData<Boolean>
        get() = _failed

    var retry: Int = 0

    init {
        _movieDetails.value = MovieDetails()
        retry = 0
        _isSuccess.value = false
        _failed.value = false
    }

    fun getMovieData() {
        _isSuccess.value = false
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClass.apiInterface.getMovieDetails(movieId,Constant.api_key)
                }
                _isSuccess.value = true
                _movieDetails.value = response.body()!!

            } catch (error: Exception) {
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