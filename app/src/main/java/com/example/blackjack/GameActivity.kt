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
    var deckList = Deck()
    lateinit var currentCard : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        dealerCards = mutableListOf(dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5)
        playerCards = mutableListOf(playerCard1, playerCard2, playerCard3, playerCard4, playerCard5)


        //start of game
        //deals first 3 cards
        for (i in 0 until 3) {


            if (i % 2 == 0){
                //adds a card to player hand
                Log.d(log, "Added card to player")
                getCard()
                player.addCard(currentCard)
                val cardValue = findCardValue(currentCard)
                playerScore.text = "Score: " + player.scoreTotal(cardValue)
                playerCardID(currentCard)
                index++
            }
            else{
               //adds a card to dealers hand
                Log.d(log, "Added card to dealer")
                getCard()
                dealer.addCard(currentCard)
                val cardValue = findCardValue(currentCard)
                dealerScore.text = "score: " + dealer.scoreTotal(cardValue)
                dealerCardID(currentCard)
                dealerIndex++
            }

            if (player.scoreTotal(0) == 21){
                while (dealer.scoreTotal(0) < 17){
                    getCard()
                    dealer.addCard(currentCard)
                    val cardValue = findCardValue(currentCard)
                    dealerScore.text = "Score: " + dealer.scoreTotal(cardValue)
                    dealerCardID(currentCard)
                    dealerIndex++
                }
                declareWinner()
            }

        }

        //onClick function for when player presses Hit
        buttonHit.setOnClickListener{
          //adds a card to player hand
            Log.d(log, "Added card to player!")
            getCard()
            player.addCard(currentCard)
            val cardValue = findCardValue(currentCard)
            playerScore.text = "Score: " + player.scoreTotal(cardValue)
            playerCardID(currentCard)
            if (player.scoreTotal(0) > 21){
                declareWinner()
            }   else if (player.scoreTotal(0) == 21){
                while(dealer.scoreTotal(0) < 17){
                    //add cards to dealer
                    getCard()
                    dealer.addCard(currentCard)
                    val cardValue = findCardValue(currentCard)
                    dealerScore.text = "score: " + dealer.scoreTotal(cardValue)
                    dealerCardID(currentCard)
                    dealerIndex++
                }
                declareWinner()
            }
            index++
            /*//if player has 5 cards, move on to dealer
            if (index == 4){
                while(dealer.scoreTotal(0) < 17){
                    //adds a card to dealer
                    getCard()
                    dealer.addCard(currentCard)
                    val cardValue = findCardValue(currentCard)
                    dealerScore.text = "score: " + dealer.scoreTotal(cardValue)
                    dealerCardID(currentCard)
                    dealerIndex++
                }
                declareWinner()
            }*/
        }

        //onClick function for when player presses pass
        buttonPass.setOnClickListener{
            Log.d(log, "Player Passes")
            hideButtons()
            //Dealer "hits" if score is below 17 and is not at 5 cards
            while(dealer.scoreTotal(0) < 17){
                //adds a card to dealer
                getCard()
                dealer.addCard(currentCard)
                val cardValue = findCardValue(currentCard)
                dealerScore.text = "Score: " + dealer.scoreTotal(cardValue)
                dealerCardID(currentCard)
                dealerIndex++
            }
            declareWinner()



        }

        newGame.setOnClickListener{
            restartGame()
        }


    }
    //game is over, start new game? not working currently.
    /*fun gameOver(context: Context) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("$winner").setMessage("Do you want to start a new game?")
        dialogBuilder.setPositiveButton("Yes", { dialog, which ->
            restartGame()
        })
        dialogBuilder.setNegativeButton("Cancel", { dialog, which ->
            backToMain()
        })
    }*/


    //get card
    fun getCard(){
        currentCard = deckList.getNewCard()
    }
    //sets player cards
    fun playerCardID(a: String){
        when(a){
            //clubs
            "Two of Clubs" -> return playerCards[index].setImageResource(R.drawable.two_of_clubs)
            "Three of Clubs" -> return playerCards[index].setImageResource(R.drawable.three_of_clubs)
            "Four of Clubs" -> return playerCards[index].setImageResource(R.drawable.four_of_clubs)
            "Five of Clubs" -> return playerCards[index].setImageResource(R.drawable.five_of_clubs)
            "Six of Clubs" -> return playerCards[index].setImageResource(R.drawable.six_of_clubs)
            "Seven of Clubs" -> return playerCards[index].setImageResource(R.drawable.seven_of_clubs)
            "Eight of Clubs" -> return playerCards[index].setImageResource(R.drawable.eight_of_clubs)
            "Nine of Clubs" -> return playerCards[index].setImageResource(R.drawable.nine_of_clubs)
            "Ten of Clubs" -> return playerCards[index].setImageResource(R.drawable.ten_of_clubs)
            "Jack of Clubs" -> return playerCards[index].setImageResource(R.drawable.jack_of_clubs)
            "Queen of Clubs" -> return playerCards[index].setImageResource(R.drawable.queen_of_clubs)
            "King of Clubs" -> return playerCards[index].setImageResource(R.drawable.king_of_clubs)
            "Ace of Clubs" -> return playerCards[index].setImageResource(R.drawable.ace_of_clubs)
            //diamonds
            "Two of Diamonds" -> return playerCards[index].setImageResource(R.drawable.two_of_diamonds)
            "Three of Diamonds" -> return playerCards[index].setImageResource(R.drawable.three_of_diamonds)
            "Four of Diamonds" -> return playerCards[index].setImageResource(R.drawable.four_of_diamonds)
            "Five of Diamonds" -> return playerCards[index].setImageResource(R.drawable.five_of_diamonds)
            "Six of Diamonds" -> return playerCards[index].setImageResource(R.drawable.six_of_diamonds)
            "Seven of Diamonds" -> return playerCards[index].setImageResource(R.drawable.seven_of_diamonds)
            "Eight of Diamonds" -> return playerCards[index].setImageResource(R.drawable.eight_of_diamonds)
            "Nine of Diamonds" -> return playerCards[index].setImageResource(R.drawable.nine_of_diamonds)
            "Ten of Diamonds" -> return playerCards[index].setImageResource(R.drawable.ten_of_diamonds)
            "Jack of Diamonds" -> return playerCards[index].setImageResource(R.drawable.jack_of_diamonds2)
            "Queen of Diamonds" -> return playerCards[index].setImageResource(R.drawable.queen_of_diamonds)
            "King of Diamonds" -> return playerCards[index].setImageResource(R.drawable.king_of_diamonds)
            "Ace of Diamonds" -> return playerCards[index].setImageResource(R.drawable.ace_of_diamonds)
            //spades
            "Two of Spades" -> return playerCards[index].setImageResource(R.drawable.two_of_spades)
            "Three of Spades" -> return playerCards[index].setImageResource(R.drawable.three_of_spades)
            "Four of Spades" -> return playerCards[index].setImageResource(R.drawable.four_of_spades)
            "Five of Spades" -> return playerCards[index].setImageResource(R.drawable.five_of_spades)
            "Six of Spades" -> return playerCards[index].setImageResource(R.drawable.six_of_spades)
            "Seven of Spades" -> return playerCards[index].setImageResource(R.drawable.seven_of_spades)
            "Eight of Spades" -> return playerCards[index].setImageResource(R.drawable.eight_of_spades)
            "Nine of Spades" -> return playerCards[index].setImageResource(R.drawable.nine_of_spades)
            "Ten of Spades" -> return playerCards[index].setImageResource(R.drawable.ten_of_spades)
            "Jack of Spades" -> return playerCards[index].setImageResource(R.drawable.jack_of_spades)
            "Queen of Spades" -> return playerCards[index].setImageResource(R.drawable.queen_of_spades)
            "King of Spades" -> return playerCards[index].setImageResource(R.drawable.king_of_spades)
            "Ace of Spades" -> return playerCards[index].setImageResource(R.drawable.ace_of_spades)
            //hearts
            "Two of Hearts" -> return playerCards[index].setImageResource(R.drawable.two_of_hearts)
            "Three of Hearts" -> return playerCards[index].setImageResource(R.drawable.three_of_hearts)
            "Four of Hearts" -> return playerCards[index].setImageResource(R.drawable.four_of_hearts)
            "Five of Hearts" -> return playerCards[index].setImageResource(R.drawable.five_of_hearts)
            "Six of Hearts" -> return playerCards[index].setImageResource(R.drawable.six_of_hearts)
            "Seven of Hearts" -> return playerCards[index].setImageResource(R.drawable.seven_of_hearts)
            "Eight of Hearts" -> return playerCards[index].setImageResource(R.drawable.eight_of_hearts)
            "Nine of Hearts" -> return playerCards[index].setImageResource(R.drawable.nine_of_hearts)
            "Ten of Hearts" -> return playerCards[index].setImageResource(R.drawable.ten_of_hearts)
            "Jack of Hearts" -> return playerCards[index].setImageResource(R.drawable.jack_of_hearts)
            "Queen of Hearts" -> return playerCards[index].setImageResource(R.drawable.queen_of_hearts)
            "King of Hearts" -> return playerCards[index].setImageResource(R.drawable.king_of_hearts)
            "Ace of Hearts" -> return playerCards[index].setImageResource(R.drawable.ace_of_hearts)



        }

    } //sets player cards
    fun dealerCardID(a: String){
        when(a){
            //clubs
            "Two of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.two_of_clubs)
            "Three of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.three_of_clubs)
            "Four of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.four_of_clubs)
            "Five of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.five_of_clubs)
            "Six of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.six_of_clubs)
            "Seven of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.seven_of_clubs)
            "Eight of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.eight_of_clubs)
            "Nine of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.nine_of_clubs)
            "Ten of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ten_of_clubs)
            "Jack of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.jack_of_clubs)
            "Queen of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.queen_of_clubs)
            "King of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.king_of_clubs)
            "Ace of Clubs" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ace_of_clubs)
            //diamonds
            "Two of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.two_of_diamonds)
            "Three of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.three_of_diamonds)
            "Four of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.four_of_diamonds)
            "Five of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.five_of_diamonds)
            "Six of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.six_of_diamonds)
            "Seven of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.seven_of_diamonds)
            "Eight of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.eight_of_diamonds)
            "Nine of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.nine_of_diamonds)
            "Ten of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ten_of_diamonds)
            "Jack of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.jack_of_diamonds2)
            "Queen of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.queen_of_diamonds)
            "King of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.king_of_diamonds)
            "Ace of Diamonds" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ace_of_diamonds)
            //spades
            "Two of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.two_of_spades)
            "Three of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.three_of_spades)
            "Four of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.four_of_spades)
            "Five of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.five_of_spades)
            "Six of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.six_of_spades)
            "Seven of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.seven_of_spades)
            "Eight of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.eight_of_spades)
            "Nine of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.nine_of_spades)
            "Ten of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ten_of_spades)
            "Jack of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.jack_of_spades)
            "Queen of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.queen_of_spades)
            "King of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.king_of_spades)
            "Ace of Spades" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ace_of_spades)
            //hearts
            "Two of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.two_of_hearts)
            "Three of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.three_of_hearts)
            "Four of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.four_of_hearts)
            "Five of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.five_of_hearts)
            "Six of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.six_of_hearts)
            "Seven of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.seven_of_hearts)
            "Eight of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.eight_of_hearts)
            "Nine of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.nine_of_hearts)
            "Ten of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ten_of_hearts)
            "Jack of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.jack_of_hearts)
            "Queen of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.queen_of_hearts)
            "King of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.king_of_hearts)
            "Ace of Hearts" -> return dealerCards[dealerIndex].setImageResource(R.drawable.ace_of_hearts)



        }

    }

    //finds value of card
    fun findCardValue(a: String): Int{
        when(a){
            //clubs
            "Two of Clubs" -> return 2
            "Three of Clubs" -> return 3
            "Four of Clubs" -> return 4
            "Five of Clubs" -> return 5
            "Six of Clubs" -> return 6
            "Seven of Clubs" -> return 7
            "Eight of Clubs" -> return 8
            "Nine of Clubs" -> return 9
            "Ten of Clubs" -> return 10
            "Jack of Clubs" -> return 10
            "Queen of Clubs" -> return 10
            "King of Clubs" -> return 10
            "Ace of Clubs" -> return 11
            //diamonds
            "Two of Diamonds" -> return 2
            "Three of Diamonds" -> return 3
            "Four of Diamonds" -> return 4
            "Five of Diamonds" -> return 5
            "Six of Diamonds" -> return 6
            "Seven of Diamonds" -> return 7
            "Eight of Diamonds" -> return 8
            "Nine of Diamonds" -> return 9
            "Ten of Diamonds" -> return 10
            "Jack of Diamonds" -> return 10
            "Queen of Diamonds" -> return 10
            "King of Diamonds" -> return 10
            "Ace of Diamonds" -> return 11
            //spades
            "Two of Spades" -> return 2
            "Tree of Spades" -> return 3
            "Four of Spades" -> return 4
            "Five of Spades" -> return 5
            "Six of Spades" -> return 6
            "Seven of Spades" ->  return 7
            "Eight of Spades" -> return 8
            "Nine of Spades" -> return 9
            "Ten of Spades" -> return 10
            "Jack of Spades" -> return 10
            "Queen of Spades" -> return 10
            "King of Spades" -> return 10
            "Ace of Spades" -> return 11
            //hearts
            "Two of Hearts" -> return 2
            "Three of Hearts" -> return 3
            "Four of Hearts" -> return 4
            "Five of Hearts" -> return 5
            "Six of Hearts" -> return 6
            "Seven of Hearts" -> return 7
            "Eight of Hearts" -> return 8
            "Nine of Hearts" -> return 9
            "Ten of Hearts" -> return 10
            "Jack of Hearts" -> return 10
            "Queen of Hearts" -> return 10
            "King of Hearts" -> return 10
            "Ace of Hearts" -> return 11

        }


        return 0
    }
    //function to declare winner
   fun declareWinner(){
        if(player.scoreTotal(0) == 21 && dealer.scoreTotal(0) != 21) winner.text= "YOU WIN!"
        else if(dealer.scoreTotal(0) == player.scoreTotal(0)) winner.text = "PUSH!"
        else if(dealer.scoreTotal(0) == 21) winner.text ="DEALER WIN!"
        else if(player.scoreTotal(0) > 21) winner.text ="BUSTED! DEALER WIN!"
        else if(dealer.scoreTotal(0) > 21) winner.text = "DEALER BUSTED! YOU WIN!"
        else if(player.scoreTotal(0) < 21 && player.scoreTotal(0) > dealer.scoreTotal(0)) winner.text ="YOU WIN!"
        else if(dealer.scoreTotal(0) < 21 && dealer.scoreTotal(0) > player.scoreTotal(0)) winner.text ="DEALER WIN!"
        newGame()

    }

    //new game
    fun newGame(){
        newGame.visibility = View.VISIBLE
        newGame.isClickable = true
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


    fun hideButtons(){
        buttons.visibility = View.INVISIBLE
        buttons.isClickable = false
    }
}