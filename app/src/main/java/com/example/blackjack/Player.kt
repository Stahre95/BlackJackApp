package com.example.blackjack

class Player {
    var playerScore = 0
    var Hand = mutableListOf<String>()

    fun scoreTotal(card: Int): Int{
        playerScore = playerScore + card
        return playerScore
    }

    fun addCard(a: String){
        Hand.add(a)
    }
}