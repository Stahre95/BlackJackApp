package com.example.blackjack


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    val log ="!!!"
    var dealerCards = mutableListOf<ImageView>()
    var playerCards = mutableListOf<ImageView>()
    var dealer = Player()
    var player = Player()
    var index = 0
    var dealerIndex = 0

    //lateinit var deckList: MutableList<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        dealerCards = mutableListOf(dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5)
        playerCards = mutableListOf(playerCard1, playerCard2, playerCard3, playerCard4, playerCard5)
        var card = deck.cards()

        //start of game
        //deals first 3 cards
        for (i in 0 until 3) {


            if (i % 2 == 0){
                //adds a card to player hand
                Log.d("!!!", "Added card to player")
                player.addCard(card)
                val cardValue = findCardValue(card)
                playerScore.text = "Score: " + player.scoreTotal(cardValue)
                playerCardID(card)
                index++
            }
            else{
               //adds a card to dealers hand
                Log.d("!!!", "Added card to dealer")
                dealer.addCard(card)
                val cardValue = findCardValue(card)
                dealerScore.text = "score: " + dealer.scoreTotal(cardValue)
                dealerCardID(card)
                dealerIndex++
            }

        }

        //onClick function for when player presses Hit
        buttonHit.setOnClickListener{
          //adds a card to player hand
            Log.d("!!!", "Added card to player!")
            if (player.scoreTotal(0) > 21){
                playerScore.text = "Busted!"
                gameOver()
            }
        }

        //onClick function for when player presses pass
        buttonPass.setOnClickListener{
            Log.d("!!!", "Player Passes")

            //Dealer "hits" if score is below 17
            while(dealer.scoreTotal(0) < 17){
              //adds a card to dealer

            }
            declareWinner()
            gameOver()

        }

    }
    //game is over, start new game? not working currently.
    fun gameOver() {
        finish()
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("$winner").setMessage("Do you want to start a new game?")
        dialogBuilder.setPositiveButton("Yes", { dialog, which ->
            restartGame()
        })
        dialogBuilder.setNegativeButton("Cancel", { dialog, which ->
            backToMain()
        })
    }

    //sets player cards
    fun playerCardID(a: String){
        when(a){
            //hearts
            "two of hearts" -> return playerCards[index].setImageResource(R.drawable.two_of_hearts)
            "three of hearts" -> return playerCards[index].setImageResource(R.drawable.three_of_hearts)
            "four of hearts" -> return playerCards[index].setImageResource(R.drawable.four_of_hearts)
            "five of hearts" -> return playerCards[index].setImageResource(R.drawable.five_of_hearts)
            "six of hearts" -> return playerCards[index].setImageResource(R.drawable.six_of_hearts)
            "seven of hearts" -> return playerCards[index].setImageResource(R.drawable.seven_of_hearts)
            "eight of hearts" -> return playerCards[index].setImageResource(R.drawable.eight_of_hearts)
            "nine of hearts" -> return playerCards[index].setImageResource(R.drawable.nine_of_hearts)
            "ten of hearts" -> return playerCards[index].setImageResource(R.drawable.ten_of_hearts)
            "jack of hearts" -> return playerCards[index].setImageResource(R.drawable.jack_of_hearts)
            "queen of hearts" -> return playerCards[index].setImageResource(R.drawable.queen_of_hearts)
            "king of hearts" -> return playerCards[index].setImageResource(R.drawable.king_of_hearts)
            "ace of hearts" -> return playerCards[index].setImageResource(R.drawable.ace_of_hearts)
            //spades
            "two of spades" -> return playerCards[index].setImageResource(R.drawable.two_of_spades)
            "three of spades" -> return playerCards[index].setImageResource(R.drawable.three_of_spades)
            "four of spades" -> return playerCards[index].setImageResource(R.drawable.four_of_spades)
            "five of spades" -> return playerCards[index].setImageResource(R.drawable.five_of_spades)
            "six of spades" -> return playerCards[index].setImageResource(R.drawable.six_of_spades)
            "seven of spades" -> return playerCards[index].setImageResource(R.drawable.seven_of_spades)
            "eight of spades" -> return playerCards[index].setImageResource(R.drawable.eight_of_spades)
            "nine of spades" -> return playerCards[index].setImageResource(R.drawable.nine_of_spades)
            "ten of spades" -> return playerCards[index].setImageResource(R.drawable.ten_of_spades)
            "jack of spades" -> return playerCards[index].setImageResource(R.drawable.jack_of_spades)
            "queen of spades" -> return playerCards[index].setImageResource(R.drawable.queen_of_spades)
            "king of spades" -> return playerCards[index].setImageResource(R.drawable.king_of_spades)
            "ace of spades" -> return playerCards[index].setImageResource(R.drawable.ace_of_spades)
            //diamonds
            "two of diamonds" -> return playerCards[index].setImageResource(R.drawable.two_of_diamonds)
            "three of diamonds" -> return playerCards[index].setImageResource(R.drawable.three_of_diamonds)
            "four of diamonds" -> return playerCards[index].setImageResource(R.drawable.four_of_diamonds)
            "five of diamonds" -> return playerCards[index].setImageResource(R.drawable.five_of_diamonds)
            "six of diamonds" -> return playerCards[index].setImageResource(R.drawable.six_of_diamonds)
            "seven of diamonds" -> return playerCards[index].setImageResource(R.drawable.seven_of_diamonds)
            "eight of diamonds" -> return playerCards[index].setImageResource(R.drawable.eight_of_diamonds)
            "nine of diamonds" -> return playerCards[index].setImageResource(R.drawable.nine_of_diamonds)
            "ten of diamonds" -> return playerCards[index].setImageResource(R.drawable.ten_of_diamonds)
            "jack of diamonds" -> return playerCards[index].setImageResource(R.drawable.jack_of_diamonds2)
            "queen of diamonds" -> return playerCards[index].setImageResource(R.drawable.queen_of_diamonds)
            "king of diamonds" -> return playerCards[index].setImageResource(R.drawable.king_of_diamonds)
            "ace of diamonds" -> return playerCards[index].setImageResource(R.drawable.ace_of_diamonds)
            //clubs
            "two of clubs" -> return playerCards[index].setImageResource(R.drawable.two_of_clubs)
            "three of clubs" -> return playerCards[index].setImageResource(R.drawable.three_of_clubs)
            "four of clubs" -> return playerCards[index].setImageResource(R.drawable.four_of_clubs)
            "five of clubs" -> return playerCards[index].setImageResource(R.drawable.five_of_clubs)
            "six of clubs" -> return playerCards[index].setImageResource(R.drawable.six_of_clubs)
            "seven of clubs" -> return playerCards[index].setImageResource(R.drawable.seven_of_clubs)
            "eight of clubs" -> return playerCards[index].setImageResource(R.drawable.eight_of_clubs)
            "nine of clubs" -> return playerCards[index].setImageResource(R.drawable.nine_of_clubs)
            "ten of clubs" -> return playerCards[index].setImageResource(R.drawable.ten_of_clubs)
            "jack of clubs" -> return playerCards[index].setImageResource(R.drawable.jack_of_clubs)
            "queen of clubs" -> return playerCards[index].setImageResource(R.drawable.queen_of_clubs)
            "king of clubs" -> return playerCards[index].setImageResource(R.drawable.king_of_clubs)
            "ace of clubs" -> return playerCards[index].setImageResource(R.drawable.ace_of_clubs)
        }

    } //sets player cards
    fun dealerCardID(a: String){
        when(a){
            //hearts
            "two of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.two_of_hearts)
            "three of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.three_of_hearts)
            "four of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.four_of_hearts)
            "five of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.five_of_hearts)
            "six of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.six_of_hearts)
            "seven of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.seven_of_hearts)
            "eight of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.eight_of_hearts)
            "nine of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.nine_of_hearts)
            "ten of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ten_of_hearts)
            "jack of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.jack_of_hearts)
            "queen of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.queen_of_hearts)
            "king of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.king_of_hearts)
            "ace of hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ace_of_hearts)
            //spades
            "two of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.two_of_spades)
            "three of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.three_of_spades)
            "four of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.four_of_spades)
            "five of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.five_of_spades)
            "six of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.six_of_spades)
            "seven of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.seven_of_spades)
            "eight of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.eight_of_spades)
            "nine of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.nine_of_spades)
            "ten of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ten_of_spades)
            "jack of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.jack_of_spades)
            "queen of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.queen_of_spades)
            "king of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.king_of_spades)
            "ace of spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ace_of_spades)
            //diamonds
            "two of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.two_of_diamonds)
            "three of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.three_of_diamonds)
            "four of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.four_of_diamonds)
            "five of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.five_of_diamonds)
            "six of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.six_of_diamonds)
            "seven of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.seven_of_diamonds)
            "eight of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.eight_of_diamonds)
            "nine of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.nine_of_diamonds)
            "ten of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ten_of_diamonds)
            "jack of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.jack_of_diamonds2)
            "queen of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.queen_of_diamonds)
            "king of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.king_of_diamonds)
            "ace of diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ace_of_diamonds)
            //clubs
            "two of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.two_of_clubs)
            "three of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.three_of_clubs)
            "four of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.four_of_clubs)
            "five of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.five_of_clubs)
            "six of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.six_of_clubs)
            "seven of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.seven_of_clubs)
            "eight of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.eight_of_clubs)
            "nine of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.nine_of_clubs)
            "ten of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ten_of_clubs)
            "jack of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.jack_of_clubs)
            "queen of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.queen_of_clubs)
            "king of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.king_of_clubs)
            "ace of clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ace_of_clubs)
        }

    }

    //finds value of card
    fun findCardValue(a: String): Int{

        when(a){
            //hearts
            "two of hearts" -> return 2
            "tree of hearts" -> return 3
            "four of hearts" -> return 4
            "five of hearts" -> return 5
            "six of hearts" -> return 6
            "seven of hearts" -> return 7
            "eight of hearts" -> return 8
            "nine of hearts" -> return 9
            "ten of hearts" -> return 10
            "jack of Hearts" -> return 10
            "queen of hearts" -> return 10
            "king of hearts" -> return 10
            "ace of hearts" -> return 11
            //spades
            "two of spades" -> return 2
            "tree of spades" -> return 3
            "four of spades" -> return 4
            "five of spades" -> return 5
            "six of spades*" -> return 7
            "eight of spades" -> return 8
            "nine of spades" -> return 9
            "ten of spades" -> return 10
            "jack of spades" -> return 10
            "queen of spades" -> return 10
            "king of spades" -> return 10
            "ace of spades" -> return 11
            //diamonds
            "two of diamonds" -> return 2
            "three of diamonds" -> return 3
            "four of diamonds" -> return 4
            "five of diamonds" -> return 5
            "six of diamonds" -> return 6
            "seven of diamonds" -> return 7
            "eight of diamonds" -> return 8
            "nine of diamonds" -> return 9
            "ten of diamonds" -> return 10
            "jack of diamonds" -> return 10
            "queen of diamonds" -> return 10
            "king of diamonds" -> return 10
            "ace of diamonds" -> return 11
            //clubs
            "two of clubs" -> return 2
            "three of clubs" -> return 3
            "four of clubs" -> return 4
            "five of clubs" -> return 5
            "six of clubs" -> return 7
            "eight of clubs" -> return 8
            "nine of clubs" -> return 9
            "ten of clubs" -> return 10
            "jack of clubs" -> return 10
            "queen of clubs" -> return 10
            "king of clubs" -> return 10
            "ace of clubs" -> return 11
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

    //back to main activity
    fun backToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}