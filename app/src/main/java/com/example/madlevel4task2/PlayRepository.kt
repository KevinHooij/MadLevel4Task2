package com.example.madlevel4task2

import android.content.Context

class PlayRepository(context: Context){

    private val playDao: PlayDao

    init{
        val database = PlayRoomDatabase.getDatabase(context)
        playDao = database!!.playDao()
    }

    suspend fun getAllPlays(): List<Play>{
        return playDao.getAllPlays()
    }

    suspend fun insertPlay(play: Play){
        playDao.insertPlay(play)
    }

    suspend fun deletePlay(play: Play){
        playDao.deletePlay(play)
    }

    suspend fun deleteAllProducts(){
        playDao.deleteAllPlays()
    }
}