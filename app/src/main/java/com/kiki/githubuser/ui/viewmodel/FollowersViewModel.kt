package com.kiki.githubuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kiki.githubuser.api.retrofitClient
import com.kiki.githubuser.model.user
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<user>>()

    fun setListFollowers(username : String){
        retrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<user>>{
                override fun onResponse(
                    call: Call<ArrayList<user>>,
                    response: Response<ArrayList<user>>
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<user>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getListFollowers() : LiveData<ArrayList<user>>{
        return listFollowers
    }
}