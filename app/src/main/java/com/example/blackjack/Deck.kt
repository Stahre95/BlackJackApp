package com.example.blackjack

import android.content.Context
import java.lang.Exception

class Deck {

    //found this solution on the internet.
    fun deckIds(context: Context): MutableList<Int>{
        val res = mutableListOf<Int>()
        val fields = R.drawable::class.java.declaredFields


        for(i in 0 until fields.size){
            try {
                val resourceId = fields[i].getInt(R.drawable())
                val name = context.resources.getResourceEntryName((resourceId))

                if(name.matches(Regex("clubs|diamonds|spades|hearts|.*"))) {
                    res.add(resourceId)
                }
            }
            catch(e: Exception){
                continue
            }
        }
        return res
    }
}