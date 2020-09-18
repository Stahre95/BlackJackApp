package com.example.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    val log ="!!!"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }


    //start of game




    //function for when player presses Hit
    fun playerHit(view : View){
        Log.d(log, "Player Hit")

        //Add a card to players Hand

    }



    //function for when player presses Pass
    fun playerPass(view : View){
        Log.d(log, "player Pass")
        
        buttonHit.isClickable = false
        buttonHit.visibility = View.GONE

        buttonPass.isClickable = false
        buttonPass.visibility = View.GONE


        //do stuff depending on Dealers Hand
    }




}