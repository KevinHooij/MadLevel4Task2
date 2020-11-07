package com.example.madlevel4task2

import androidx.room.TypeConverter
import java.util.*
import com.example.madlevel4task2.Play.Attack
import com.example.madlevel4task2.Play.Outcome

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
                0 -> Attack.ROCK
                1 -> Attack.PAPER
                2 -> Attack.SCISSORS
                else -> Attack.ROCK
            }
        }
    }

    //Converts attacks back to Integers
    @TypeConverter
    fun attackToInt(attack: Play.Attack?): Int? {
        return attack?.let {
            when (it) {
                Attack.ROCK -> 0
                Attack.PAPER -> 1
                Attack.SCISSORS -> 2
            }
        }
    }

    //Converts Int to Outcome
    @TypeConverter
    fun fromOutcome(value: Int?): Outcome? {
        return value?.let {
            when (it) {
                0 -> Outcome.WIN
                1 -> Outcome.DRAW
                2 -> Outcome.LOSE
                else -> Outcome.LOSE
            }
        }
    }

    //Converts Outcome back to Int
    @TypeConverter
    fun outcomeToInt(outcome: Outcome?): Int? {
        return outcome?.let {
            when (it) {
                Outcome.WIN -> -1
                Outcome.DRAW -> 0
                Outcome.LOSE -> 1
            }
        }
    }
}
