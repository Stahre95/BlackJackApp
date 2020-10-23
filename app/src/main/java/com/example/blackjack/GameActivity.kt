package com.example.blackjack


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity() {
    //val log ="!!!"
    var dealerCards = mutableListOf<ImageView>()
    var playerCards = mutableListOf<ImageView>()
    var dealer = Player()
    var player = Player()
    var index = 0
    var dealerIndex = 0
    var deckList = Deck()
    var ace = 0
    lateinit var winner: String
    lateinit var currentCard: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()
        dealerCards = mutableListOf(dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5)
        playerCards = mutableListOf(playerCard1, playerCard2, playerCard3, playerCard4, playerCard5)

        //initilize()
        //playerBet()

        //Card phase
        //deals first 3 cards
        //hideBet()
        showDeal()
        Deal.setOnClickListener {
            showButtons()
            hideDeal()
            for (i in 0 until 3) {


                if (i % 2 == 0) {
                    //adds a card to player hand
                    playerHit()
                    index++
                } else {
                    //adds a card to dealers hand
                    dealerHit()
                    dealerIndex++
                }
                if (player.scoreTotal(0) == 21) {
                    declareWinner()
                }
            }


            //onClick function for when player presses Hit
            buttonHit.setOnClickListener {
                //adds a card to player hand
                playerHit()
                if (player.scoreTotal(0) > 21) {
                    declareWinner()
                } else if (player.scoreTotal(0) == 21) {
                    while (dealer.scoreTotal(0) < 17) {
                        dealerHit()
                        dealerIndex++
                    }
                    declareWinner()
                }
                //if player has 5 cards, move on to dealer
                if (index == 4) {
                    while (dealer.scoreTotal(0) < 17) {
                        //adds a card to dealer
                        dealerHit()
                        dealerIndex++
                    }
                    declareWinner()
                }
                index++

            }

            //onClick function for when player presses pass
            buttonPass.setOnClickListener {
                hideButtons()
                //Dealer "hits" if score is below 17 and is not at 5 cards
                while (dealer.scoreTotal(0) < 17) {
                    //adds a card to dealer
                    dealerHit()
                    dealerIndex++
                }
                declareWinner()
            }
        }
            //onClick function for when player presses Double
        buttonDouble.setOnClickListener{
            hideButtons()
            //Gives player 1 extra card
            for (i in 0..0){
                playerHit()
            }
            while (dealer.scoreTotal(0) < 17) {
                dealerHit()
                dealerIndex++
            }
            declareWinner()
        }
    }
    /*Work in progress


    fun initilize(){

        if (player.playerMoney == 0)
        player.playerMoney = 1000
        betTextView.text = "Current money: ${player.playerMoney.toString()}"
    }


    fun playerBet(){
        var betInput = findViewById<EditText>(R.id.bet)
        var bet = betInput.toString()
        var betAsNumber: Int = bet.toInt()



        buttonBet.setOnClickListener {
               //player.playerMoney = player.playerMoney - betAsNumber
               //betTextView.text = "Current bet: ${betInput.toString()}"
               showDeal()
               hideBet()
           else {
               val dialogBuilder = AlertDialog.Builder(this)
               dialogBuilder.setTitle("Wrong bet!").setMessage("Your bet needs to be greater than 5 and lower than 500")
               dialogBuilder.setPositiveButton("Try again!", {dialog, which ->
                   restartGame()
               })
               dialogBuilder.setNegativeButton("Return to menu", {dialog, which ->
                   backToMain()
               })

               val alert = dialogBuilder.create()
               alert.show()
           }
        }
    }*/
    //game is over, start new game?
    fun gameOver(context: Context) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("${winner}").setMessage("Do you want to start a new game?")
        dialogBuilder.setPositiveButton("New Game", { dialog, which ->
            restartGame()
        })
        dialogBuilder.setNegativeButton("Return to Menu", { dialog, which ->
            backToMain()
        })

        val alert = dialogBuilder.create()
        alert.show()
    }

    fun playerHit(){
        getCard()
        player.addCard(currentCard)
        val cardValue = findCardValue(currentCard)
        playerScore.text = "Score: " + player.scoreTotal(cardValue)
        playerCardID(currentCard)

        if (player.scoreTotal(0) > 21 && ace > 0){
            playerScore.text = "score: " + player.scoreTotal(0 - 10)
            ace--
        }
    }
    fun dealerHit(){
        getCard()
        dealer.addCard(currentCard)
        val cardValue = findCardValue(currentCard)
        dealerScore.text = "score: " + dealer.scoreTotal(cardValue)
        dealerCardID(currentCard)
    }


    //get card
    fun getCard(){
        currentCard = deckList.getNewCard()

        if (currentCard == "Ace of Spades" || currentCard == "Ace of Diamonds" || currentCard == "Ace of Hearts" || currentCard == "Ace of Clubs"){
            ace++
        }
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
            "Three of Spades" -> return 3
            "Four of Spades" -> return 4
            "Five of Spades" -> return 5
            "Six of Spades" -> return 6
            "Seven of Spades" -> return 7
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
        if (player.scoreTotal(0) == 21 && index == 1) winner = "YOU GOT BLACKJACK!"
        else if(player.scoreTotal(0) == 21 && dealer.scoreTotal(0) != 21) winner = "YOU WIN!"
        else if(dealer.scoreTotal(0) == player.scoreTotal(0)) winner  = "PUSH!"
        else if(dealer.scoreTotal(0) == 21) winner ="DEALER WIN!"
        else if(player.scoreTotal(0) > 21) winner ="BUSTED! DEALER WIN!"
        else if(dealer.scoreTotal(0) > 21) winner = "DEALER BUSTED! YOU WIN!"
        else if(player.scoreTotal(0) < 21 && player.scoreTotal(0) > dealer.scoreTotal(0)) winner ="YOU WIN!"
        else if(dealer.scoreTotal(0) < 21 && dealer.scoreTotal(0) > player.scoreTotal(0)) winner ="DEALER WIN!"
        gameOver(this)
        //newGame()

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


    fun showButtons() {
        buttons.visibility = View.VISIBLE
        buttons.isClickable = true
    }

    fun hideButtons(){
        buttons.visibility = View.INVISIBLE
        buttons.isClickable = false
    }
    /*fun hideBet(){
        betting.visibility = View.INVISIBLE
    }*/

    fun showDeal(){
        Deal.visibility = View.VISIBLE
        Deal.isClickable = true
    }

    fun hideDeal(){
        Deal.visibility = View.INVISIBLE
        Deal.isClickable = false
    }
}