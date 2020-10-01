package com.example.blackjack

class Player {
    var playerScore = 0
    var hand = mutableListOf<String>()

    fun scoreTotal(card: Int): Int{
        playerScore = playerScore + card
        return playerScore
    }

    fun addCard(card: String){
        hand.add(card)
    }

    fun removeCard(card: String){
        hand.removeAt(0)
    }
}