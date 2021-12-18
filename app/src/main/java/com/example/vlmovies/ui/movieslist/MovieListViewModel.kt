package com.example.vlmovies.ui.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vlmovies.model.Movie
import com.example.vlmovies.network.RetrofitClass
import com.example.vlmovies.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieListViewModel : ViewModel() {

    private val _moviesList = MutableLiveData<ArrayList<Movie>>()
    val moviesList: LiveData<ArrayList<Movie>>
        get() = _moviesList

    val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    val _failed = MutableLiveData<Boolean>()
    val failed: LiveData<Boolean>
        get() = _failed

    // number of tries for sending request
    var retry: Int = 0

    init {
        retry = 0
        _isSuccess.value = false
        _failed.value = false
        _moviesList.value = ArrayList()
    }

    fun getMoviesList(){
        _isSuccess.value = false
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClass.apiInterface.getPopularMovies(Constant.api_key)
                }
                _isSuccess.value = true

                _moviesList.value = response.body()!!.moviesList!!

            } catch (error: Exception) {
                retry+=1
                if(retry<3)
                    getMoviesList()
                else
                    _failed.value = true
            }
        }
    }

    // function to retry loading data
    fun tryGetMoviesList(){
        retry = 0
        _failed.value = false
        getMoviesList()
    }


}