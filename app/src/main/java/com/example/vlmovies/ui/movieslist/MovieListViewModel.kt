package com.example.vlmovies.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vlmovies.model.Movie
import com.example.vlmovies.repository.MoviesRepository
import com.example.vlmovies.repository.MoviesRepositoryImplementer
import com.example.vlmovies.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel : ViewModel() {

    private val repository : MoviesRepository = MoviesRepositoryImplementer()
    
    private val _moviesList = MutableLiveData<ArrayList<Movie>>()
    val moviesList: LiveData<ArrayList<Movie>>
        get() = _moviesList

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val _failed = MutableLiveData<Boolean>()
    val failed: LiveData<Boolean>
        get() = _failed

    // number of tries for sending request
    private var retry: Int = 0

    init {
        retry = 0
        _isSuccess.value = false
        _failed.value = false
        _moviesList.value = ArrayList()
    }

    fun getMoviesList(){
        _isSuccess.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPopularMoviesList(Constant.api_key)
            if(response!!.isSuccessful)
            {
                withContext(Dispatchers.Main) {
                    _isSuccess.value = true
                    _moviesList.value = response.body()!!.moviesList!!
                }
            } else {
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