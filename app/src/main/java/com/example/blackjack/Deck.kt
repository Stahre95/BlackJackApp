package com.example.blackjack
import android.content.Context
import android.widget.ImageView
import kotlin.random.Random


object Deck{

    fun cards(): String {
        var card = Random.nextInt(1,52)
        when(card){
            1 -> return "ace of hearts"
            2 -> return "two of hearts"
            3 -> return "three of hearts"
            4 -> return "four of hearts"
            5 -> return "five of hearts"
            6 -> return "six of hearts"
            7 -> return "seven of hearts"
            8 -> return "eight of hearts"
            9 -> return "nine of hearts"
            10 -> return "ten of hearts"
            11 -> return "jack of hearts"
            12 -> return "Queen of hearts"
            13 -> return "king of Hearts"
            14 -> return "ace of spades"
            15 -> return "two of spades"
            16 -> return "three of spades"
            17 -> return "four of spades"
            18 -> return "five of spades"
            19 -> return "six of spades"
            20 -> return "seven of spades"
            21 -> return "eight of spades"
            22 -> return "nine of spades"
            23 -> return "ten of spades"
            24 -> return "jack of spades"
            25 -> return "queen of spades"
            26 -> return "king of spades"
            27 -> return "ace of diamonds"
            28 -> return "two of diamonds"
            29 -> return "three of diamonds"
            30 -> return "four of diamonds"
            31 -> return "five of diamonds"
            32 -> return "six of diamonds"
            33 -> return "seven of diamonds"
            34 -> return "eight of diamonds"
            35 -> return "nine of diamonds"
            36 -> return "ten of diamonds"
            37 -> return "jack of diamonds"
            38 -> return "queen of diamonds"
            39 -> return "king of diamonds"
            40 -> return "ace of clubs"
            41 -> return "two of clubs"
            42 -> return "three of clubs"
            43 -> return "four of clubs"
            44 -> return "five of clubs"
            45 -> return "six of clubs"
            46 -> return "seven of clubs"
            47 -> return "eight of clubs"
            48 -> return "nine of clubs"
            49 -> return "ten of clubs"
            50 -> return "jack of clubs"
            51 -> return "queen of clubs"
            52 -> return "king of clubs"

        }

        return " "
    }
}