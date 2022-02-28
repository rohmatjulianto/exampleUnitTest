package com.example.myunit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myunit.data.Post
import com.example.myunit.data.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {


    private val _user = MutableLiveData<ArrayList<User>>()
    val user : LiveData<ArrayList<User>> = _user

    private val _post = MutableLiveData<Post>()
    val post : LiveData<Post> = _post

    fun getUser(){
        viewModelScope.launch(dispatcher) {
            val res = repository.getUser()
            _user.value = res
        }
    }

    fun getPost(id: String){
        viewModelScope.launch(dispatcher) {
            val res = repository.getPost(id)
            _post.value = res
        }
    }
}