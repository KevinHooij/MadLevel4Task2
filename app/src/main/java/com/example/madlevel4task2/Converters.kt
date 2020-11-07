package com.example.madlevel4task2

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    //Converts Integer to attacks
    @TypeConverter
    fun fromMove(value: Int?): Play.Attack? {
        return value?.let {
            when (it) {
                0 -> Play.Attack.ROCK
                1 -> Play.Attack.PAPER
                2 -> Play.Attack.SCISSORS
                else -> Play.Attack.ROCK
            }
        }
    }

    //Converts attacks back to Integers
    @TypeConverter
    fun attackToInt(attack: Play.Attack?): Int? {
        return attack?.let {
            when (it) {
                Play.Attack.ROCK -> 0
                Play.Attack.PAPER -> 1
                Play.Attack.SCISSORS -> 2
            }
        }
    }

    //Converts Int to Outcome
    @TypeConverter
    fun fromOutcome(value: Int?): Play.Outcome? {
        return value?.let {
            when (it) {
                0 -> Play.Outcome.WIN
                1 -> Play.Outcome.DRAW
                2 -> Play.Outcome.LOSE
                else -> Play.Outcome.LOSE
            }
        }
    }

    //Converts Outcome back to Int
    @TypeConverter
    fun outcomeToInt(outcome: Play.Outcome?): Int? {
        return outcome?.let {
            when (it) {
                Play.Outcome.WIN -> -1
                Play.Outcome.DRAW -> 0
                Play.Outcome.LOSE -> 1
            }
        }
    }
}
