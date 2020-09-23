package com.example.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    val log ="!!!"
    var dealerCards = mutableListOf<ImageView>()
    var playerCards = mutableListOf<ImageView>()
    var dealer = Player()
    var player = Player()
    var deck = Deck()
    lateinit var deckList: MutableList<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        deckList = deck.deckIds(this)
        dealerCards = mutableListOf(dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5)
        playerCards = mutableListOf(playerCard1, playerCard2, playerCard3, playerCard4, playerCard5)

        var card = 0
        var cardId = 0
        var playerIndex = 0
        var dealerindex = 0
        var store = 0

        //start of game
        //deals first 4 cards
        for (i in 0 until 3) {
            card = Random.nextInt(deckList.size)
            cardId = deckList[card]
            val name = resources.getResourceEntryName(cardId)


            if (i % 2 == 0){
                player.addCard(name)
                val cardValue = findCardValue(name)
                playerScore.text = "Score: " + player.scoreTotal(cardValue)
                playerCards[playerIndex].setImageResource(cardId)
            }
            else{
                dealer.addCard(name)
                val cardValue = findCardValue(name)
                dealerScore.text = "Score: " + player.scoreTotal(cardValue)
                dealerCards[dealerindex].setImageResource(cardId)
            }
            deckList.remove(cardId)
        }

        //onClick function for when player presses Hit
        buttonHit.setOnClickListener{
            card = Random.nextInt(deckList.size)
            cardId = deckList[card]
            val name = resources.getResourceEntryName(cardId)
            deckList.remove(card)
            player.addCard(name)
            val cardValue = findCardValue(name)
            playerScore.text = "Score: " + player.scoreTotal(cardValue)
            playerIndex++
            if (player.scoreTotal(0) > 21){
                playerScore.text = "Busted!"
                showEndGameButtons()
            }
        }

        //onClick function for when player presses pass
        buttonPass.setOnClickListener{
            buttons.visibility = View.INVISIBLE

            //Dealer "hits" if score is below 17
            while(dealer.scoreTotal(0) < 17){
                card = Random.nextInt(deckList.size)
                cardId = deckList[card]
                val name = resources.getResourceEntryName(cardId)
                deckList.remove(cardId)
                dealer.addCard(name)
                val cardValue = findCardValue(name)
                dealer.scoreTotal(cardValue)
                dealerCards[dealerindex].setImageResource(cardId)
                dealerindex++
            }
            dealerCards[0].setImageResource(store)
            declareWinner()
            showEndGameButtons()
        }

        //starts new Game
        newGame.setOnClickListener{
            restartGame()
        }
    }
    //translate card name to a value
    fun findCardValue(card: String): Int{
        when(card){
            //clubs
            "two_of_clubs" -> 2
            "three_of_clubs" -> 3
            "four_of_clubs" -> 4
            "five_of_clubs" -> 5
            "six_of_clubs" -> 6
            "seven_of_clubs" -> 7
            "eight_of_clubs" -> 8
            "nine_of_clubs" -> 9
            "tem_of_clubs" -> 10
            "jack_of_clubs" -> 10
            "queen_of_clubs" -> 10
            "king_of_clubs" -> 10
            "Ace_of_clubs" -> 11
            //diamonds
            "two_of_diamonds" -> 2
            "three_of_diamonds" -> 3
            "four_of_diamonds" -> 4
            "five_of_diamonds" -> 5
            "six_of_diamonds" -> 6
            "seven_of_diamonds" -> 7
            "eight_of_diamonds" -> 8
            "nine_of_diamonds" -> 9
            "ten_of_diamonds" -> 10
            "jack_of_diamonds" -> 10
            "queen_of_diamonds" -> 10
            "king_of_diamonds" -> 10
            "Ace_of_diamonds" -> 11
            //spades
            "two_of_spades" -> 2
            "three_of_spades" -> 3
            "four_of_spades" -> 4
            "five_of_spades" -> 5
            "six_of_spades" -> 6
            "seven_of_spades" -> 7
            "eight_of_spades" -> 8
            "nine_of_spades" -> 9
            "ten_of_spades" -> 10
            "jack_of_spades" -> 10
            "queen_of_spades" -> 10
            "king_of_spades" -> 10
            "ace_of_spades" -> 11
            //hearts
            "two_of_hearts" -> 2
            "three_of_hearts" -> 3
            "four_of_hearts" -> 4
            "five_of_hearts" -> 5
            "six_of_hearts" -> 6
            "seven_of_hearts" -> 7
            "eight_of_hearts" -> 8
            "nine_of_hearts" -> 9
            "ten_of_hearts" -> 10
            "jack_of_hearts" -> 10
            "queen_of_hearts" -> 10
            "king_of_hearts" -> 10
            "ace_of_hearts" -> 11

        }
        return 0
    }

    //function to declare winner
    fun declareWinner(){
        if(player.scoreTotal(0) == 21) winner.text= "YOU WIN!"
        else if(dealer.scoreTotal(0) == 21) winner.text ="DEALER WIN!"
        else if(player.scoreTotal(0) < 21 && player.scoreTotal(0) > dealer.scoreTotal(0)) winner.text ="YOU WIN!"
        else if(dealer.scoreTotal(0) < 21 && dealer.scoreTotal(0) < player.scoreTotal(0)) winner.text ="DEALER WIN!"
        else if(dealer.scoreTotal(0) == 21 && player.scoreTotal(0) == 21) winner.text ="YOU WIN!"
        else if(dealer.scoreTotal(0) == player.scoreTotal(0)) winner.text = "PUSH!"
        else if(player.scoreTotal(0) > 21) winner.text ="DEALER WIN!"
        else if(dealer.scoreTotal(0) > 21) winner.text = "YOU WIN!"

    }


    //Restart game
    fun restartGame(){
        finish()
        startActivity(intent)
    }


    fun showEndGameButtons(){
        newGame.visibility = View.VISIBLE
        buttons.visibility = View.INVISIBLE

    }

}