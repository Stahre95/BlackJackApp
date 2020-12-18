package com.example.blackjack

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDao {

    @Insert
    fun insert(card: Card)

    @Delete
    fun delete(card: Card)

    @Query("SELECT * FROM card")
    fun getAll() : List<Card>

}