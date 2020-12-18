package com.example.blackjack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class Card(@PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name  = "cardName") var name: String)