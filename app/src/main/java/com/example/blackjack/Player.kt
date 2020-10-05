package com.example.blackjack

class Player {
    private var playerScore = 0
    private var hand = mutableListOf<String>()

    fun scoreTotal(card: Int): Int{
        playerScore = playerScore + card
        return playerScore
    }

    fun addCard(card: String){
        hand.add(card)
    }

}