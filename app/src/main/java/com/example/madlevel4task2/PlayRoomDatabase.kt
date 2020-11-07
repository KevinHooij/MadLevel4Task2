package com.example.madlevel4task2

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import kotlin.jvm.Volatile;

@Database(entities = [Play::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlayRoomDatabase : RoomDatabase() {

    abstract fun playDao(): PlayDao

    companion object {
        private const val DATABASE_NAME = "PLAY_DATABASE"

        @Volatile
        private var PlayRoomDatabaseInstance: PlayRoomDatabase? = null

        fun getDatabase(context: Context): PlayRoomDatabase? {
            if (PlayRoomDatabaseInstance == null) {
                synchronized(PlayRoomDatabase::class.java) {
                    if (PlayRoomDatabaseInstance == null) {
                        PlayRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,PlayRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return  PlayRoomDatabaseInstance
        }
    }

}



