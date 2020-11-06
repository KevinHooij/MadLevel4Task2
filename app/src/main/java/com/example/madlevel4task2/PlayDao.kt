package com.example.madlevel4task2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao

interface PlayDao{
    @Query("SELECT * FROM play_table")
    suspend fun getAllPlays(): List<Play>

    @Insert
    suspend fun insertPlay(play: Play)

    @Delete
    suspend fun deletePlay(play: Play)

    @Query("DELETE FROM play_table")
    suspend fun deleteAllPlays()
}