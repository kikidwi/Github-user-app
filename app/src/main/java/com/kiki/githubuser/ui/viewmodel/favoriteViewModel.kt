package com.kiki.githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kiki.githubuser.local.favoriteUser
import com.kiki.githubuser.local.favoriteUserDao
import com.kiki.githubuser.local.userDatabase

class favoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: favoriteUserDao?
    private var userDb: userDatabase?

    init {
        userDb = userDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<favoriteUser>>? {
        return userDao?.getFavoriteUser()
    }
}