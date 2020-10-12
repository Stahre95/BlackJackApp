package com.example.blackjack
import android.content.Context
import android.widget.ImageView
import kotlin.random.Random


class Deck{
      private val deck = mutableListOf<Card>()

   init {
       for (i in 0..6 - 1) {
           initialize()
       }
   }


    private fun initialize(){
        //clubs
        deck.add(Card("Two of Clubs"))
        deck.add(Card("Three of Clubs"))
        deck.add(Card("Four of Clubs"))
        deck.add(Card("Five of Clubs"))
        deck.add(Card("Six of Clubs"))
        deck.add(Card("Seven of Clubs"))
        deck.add(Card("Eight of Clubs"))
        deck.add(Card("Nine of Clubs"))
        deck.add(Card("Ten of Clubs"))
        deck.add(Card("Jack of Clubs"))
        deck.add(Card("Queen of Clubs"))
        deck.add(Card("King of Clubs"))
        deck.add(Card("Ace of Clubs"))
        //diamonds
        deck.add(Card("Two of Diamonds"))
        deck.add(Card("Three of Diamonds"))
        deck.add(Card("Four of Diamonds"))
        deck.add(Card("Five of Diamonds"))
        deck.add(Card("Six of Diamonds"))
        deck.add(Card("Seven of Diamonds"))
        deck.add(Card("Eight of Diamonds"))
        deck.add(Card("Nine of Diamonds"))
        deck.add(Card("Ten of Diamonds"))
        deck.add(Card("Jack of Diamonds"))
        deck.add(Card("Queen of Diamonds"))
        deck.add(Card("King of Diamonds"))
        deck.add(Card("Ace of Diamons"))
        //spades
        deck.add(Card("Two of Spades"))
        deck.add(Card("Three of Spades"))
        deck.add(Card("Four of Spades"))
        deck.add(Card("Five of Spades"))
        deck.add(Card("Six of Spades"))
        deck.add(Card("Seven of Spades"))
        deck.add(Card("Eight of Spades"))
        deck.add(Card("Nine of Spades"))
        deck.add(Card("Ten of Spades"))
        deck.add(Card("Jack of Spades"))
        deck.add(Card("Queen of Spades"))
        deck.add(Card("King of Spades"))
        deck.add(Card("Ace of Spades"))
        //hearts
        deck.add(Card("Two of Hearts"))
        deck.add(Card("Three of Hearts"))
        deck.add(Card("Four of Hearts"))
        deck.add(Card("Five of Hearts"))
        deck.add(Card("Six of Hearts"))
        deck.add(Card("Seven of Hearts"))
        deck.add(Card("Eight of Hearts"))
        deck.add(Card("Nine of Hearts"))
        deck.add(Card("Ten of Hearts"))
        deck.add(Card("Jack of Hearts"))
        deck.add(Card("Queen of Hearts"))
        deck.add(Card("King of Hearts"))
        deck.add(Card("Ace of Hearts" ))
    }

    fun getNewCard() : String{
        if (deck.size == 0){
            for (i in 0..6 - 1){
                initialize()
            }
        }
        val rnd = (0 until deck.size).random()
        val name = deck[rnd].name
        deck.removeAt(rnd)

        return name

    }

}