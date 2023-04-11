package com.kiki.githubuser.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [favoriteUser::class],
    version = 1
)
abstract class userDatabase : RoomDatabase(){
    companion object{
        var INSTANCE : userDatabase? = null

        fun getDatabase(context: Context): userDatabase?{
            if (INSTANCE == null){
                synchronized(userDatabase::class){
                    INSTANCE= Room.databaseBuilder(context.applicationContext, userDatabase::class.java, "user_database").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun favoriteUserDao() : favoriteUserDao
}