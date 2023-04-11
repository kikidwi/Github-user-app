package com.kiki.githubuser.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface favoriteUserDao {
    @Insert
    fun addFavorite(favoriteUser: favoriteUser)

    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUser(): LiveData<List<favoriteUser>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    fun checkUser(id : Int) : Int

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    fun removeFavorite(id : Int) : Int

}