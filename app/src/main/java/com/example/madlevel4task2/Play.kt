package com.example.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "play_table")
data class Play (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "your_attack")
    var yourAttack: Attack,

    @ColumnInfo(name = "enemy_attack")
    var enemyAttack: Attack,

    @ColumnInfo(name = "Outcome")
    var outcome: Outcome,

    @ColumnInfo(name = "date")
    var date: Date

) {
    enum class Attack {
        ROCK, PAPER, SCISSORS
    }

    enum class Outcome {
        LOSE, DRAW, WIN
    }


    }
