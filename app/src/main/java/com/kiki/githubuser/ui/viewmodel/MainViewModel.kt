package com.kiki.githubuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.kiki.githubuser.api.retrofitClient
import com.kiki.githubuser.model.user
import com.kiki.githubuser.model.userResponse
import com.kiki.githubuser.theme.SettingPreferences
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val pref: SettingPreferences): ViewModel() {
    val listUser = MutableLiveData<ArrayList<user>>()

    fun setSearchUser(query: String){
        retrofitClient.apiInstance
            .getSearchUser(query)
            .enqueue(object : Callback<userResponse>{
                override fun onResponse(
                    call: Call<userResponse>,
                    response: Response<userResponse>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<userResponse>, t: Throwable) {
                    Log.d("Faillure" , t.message.toString())
                }

            })

    }

    fun getSearchUser():LiveData<ArrayList<user>>{
        return listUser
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}