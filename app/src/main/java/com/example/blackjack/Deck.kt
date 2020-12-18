package com.example.blackjack
import android.content.Context
import android.widget.ImageView
import kotlin.random.Random


class Deck(private val deck: MutableList<Card>){


   init {
       for (i in 0..6 - 1) {
           initialize()
       }
   }


    private fun initialize(){
        //clubs
        deck.add(Card(0,"Two of Clubs"))
        deck.add(Card(0, "Three of Clubs"))
        deck.add(Card(0,"Four of Clubs"))
        deck.add(Card(0,"Five of Clubs"))
        deck.add(Card(0,"Six of Clubs"))
        deck.add(Card(0,"Seven of Clubs"))
        deck.add(Card(0,"Eight of Clubs"))
        deck.add(Card(0,"Nine of Clubs"))
        deck.add(Card(0,"Ten of Clubs"))
        deck.add(Card(0,"Jack of Clubs"))
        deck.add(Card(0,"Queen of Clubs"))
        deck.add(Card(0,"King of Clubs"))
        deck.add(Card(0,"Ace of Clubs"))
        //diamonds
        deck.add(Card(0,"Two of Diamonds"))
        deck.add(Card(0,"Three of Diamonds"))
        deck.add(Card(0,"Four of Diamonds"))
        deck.add(Card(0,"Five of Diamonds"))
        deck.add(Card(0,"Six of Diamonds"))
        deck.add(Card(0,"Seven of Diamonds"))
        deck.add(Card(0,"Eight of Diamonds"))
        deck.add(Card(0,"Nine of Diamonds"))
        deck.add(Card(0,"Ten of Diamonds"))
        deck.add(Card(0,"Jack of Diamonds"))
        deck.add(Card(0,"Queen of Diamonds"))
        deck.add(Card(0,"King of Diamonds"))
        deck.add(Card(0,"Ace of Diamons"))
        //spades
        deck.add(Card(0,"Two of Spades"))
        deck.add(Card(0,"Three of Spades"))
        deck.add(Card(0,"Four of Spades"))
        deck.add(Card(0,"Five of Spades"))
        deck.add(Card(0,"Six of Spades"))
        deck.add(Card(0,"Seven of Spades"))
        deck.add(Card(0,"Eight of Spades"))
        deck.add(Card(0,"Nine of Spades"))
        deck.add(Card(0,"Ten of Spades"))
        deck.add(Card(0,"Jack of Spades"))
        deck.add(Card(0,"Queen of Spades"))
        deck.add(Card(0,"King of Spades"))
        deck.add(Card(0,"Ace of Spades"))
        //hearts
        deck.add(Card(0,"Two of Hearts"))
        deck.add(Card(0,"Three of Hearts"))
        deck.add(Card(0,"Four of Hearts"))
        deck.add(Card(0,"Five of Hearts"))
        deck.add(Card(0,"Six of Hearts"))
        deck.add(Card(0,"Seven of Hearts"))
        deck.add(Card(0,"Eight of Hearts"))
        deck.add(Card(0,"Nine of Hearts"))
        deck.add(Card(0,"Ten of Hearts"))
        deck.add(Card(0,"Jack of Hearts"))
        deck.add(Card(0,"Queen of Hearts"))
        deck.add(Card(0,"King of Hearts"))
        deck.add(Card(0,"Ace of Hearts" ))
    }

    fun addCard(card: Card) {
        deck.add(card)
    }

    fun getNewCard() : String?{
        if (deck.size <= 0){
            return null
        }
        val rnd = (0 until deck.size).random()
        val name = deck[rnd].name

        return name

    }


}