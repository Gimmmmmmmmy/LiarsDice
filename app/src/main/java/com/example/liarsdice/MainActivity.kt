package com.example.liarsdice

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.liarsdice.databinding.ActivityMainBinding


var diceRemoveSort = emptyList<Int>()
var displayDice = arrayOf<Int?>(0,0,0,0,0,0)
var opened = true
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayAmount(displayDice)

        binding.rollButton.setOnClickListener {
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
            toast.show()

            if (binding.inputDiceAmount.text.isNullOrBlank()) {
                binding.inputDiceAmount.setText("5")
                diceRemoveSort = removeDice() as List<Int>
            }else {
                diceRemoveSort = removeDice() as List<Int>
            }
            diceRemoveSort = removeDice() as List<Int>

            displayDice= rollDice()
            displayAmount(displayDice)

        }
        binding.zai.setOnClickListener {
            displayAmount(displayDice)
        }
        binding.hideOrCover.setOnClickListener {
            if (opened) {
                val zeros = arrayOf<Int?>(0,0,0,0,0,0)
                displayAmount(zeros)
                binding.cloche.setImageResource(R.drawable.cloche)
                opened = false
            }else {
                displayAmount(displayDice)
                binding.cloche.setImageResource(R.drawable.empty)
                opened = true
            }
        }


    }


    fun removeDice(): List<Int?> {
        val diceAmount = (binding.inputDiceAmount.text.toString().toInt())
        val diceNotPicked = (0..12).shuffled().take(13-diceAmount)
        return diceNotPicked
    }

    private fun rollDice(): Array<Int?> {
        val dice = Dice(6)
        val diceRoll = arrayOfNulls<Int>(13)
        val diceImage = arrayOfNulls<ImageView>(13)
        val drawableResource = arrayOfNulls<Int>(13)
        var amountOf1=0
        var amountOf2=0
        var amountOf3=0
        var amountOf4=0
        var amountOf5=0
        var amountOf6=0
        for (i in 0..12) {
            diceRoll[i] = dice.roll()
        }

        diceImage[0]= findViewById(R.id.diceDisplay0)
        diceImage[1]= findViewById(R.id.diceDisplay1)
        diceImage[2]= findViewById(R.id.diceDisplay2)
        diceImage[3]= findViewById(R.id.diceDisplay3)
        diceImage[4]= findViewById(R.id.diceDisplay4)
        diceImage[5]= findViewById(R.id.diceDisplay5)
        diceImage[6]= findViewById(R.id.diceDisplay6)
        diceImage[7]= findViewById(R.id.diceDisplay7)
        diceImage[8]= findViewById(R.id.diceDisplay8)
        diceImage[9]= findViewById(R.id.diceDisplay9)
        diceImage[10]= findViewById(R.id.diceDisplay10)
        diceImage[11]= findViewById(R.id.diceDisplay11)
        diceImage[12]= findViewById(R.id.diceDisplay12)
        for (k in 0..12) {
            drawableResource[k] = when (diceRoll[k]) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        }
        for (m in 0..12) {
            if (diceRemoveSort.contains(m)){
                diceImage[m]!!.setImageResource(R.drawable.empty)
            }else{
                diceImage[m]!!.setImageResource(drawableResource[m]!!)
                when (diceRoll[m]) {
                    1 -> amountOf1++
                    2 -> amountOf2++
                    3 -> amountOf3++
                    4 -> amountOf4++
                    5 -> amountOf5++
                    else -> amountOf6++

                }
            }
        }
        return arrayOf<Int?>(amountOf1, amountOf2, amountOf3, amountOf4, amountOf5, amountOf6)

        /*for (n in 0..diceAmount-1) {
            diceImage[n]!!.contentDescription = diceRoll[n].toString()
        }*/
    }
    private fun displayAmount(numOf: Array<Int?>) {
        var numOfString1 = numOf[0].toString()
        var numOfString2 = (numOf[0]?.plus(numOf[1]!!)).toString()
        var numOfString3 = (numOf[0]?.plus(numOf[2]!!)).toString()
        var numOfString4 = (numOf[0]?.plus(numOf[3]!!)).toString()
        var numOfString5 = (numOf[0]?.plus(numOf[4]!!)).toString()
        var numOfString6 = (numOf[0]?.plus(numOf[5]!!)).toString()
        if (binding.zai.isChecked) {
            numOfString1 = numOf[0].toString()
            numOfString2 = numOf[1].toString()
            numOfString3 = numOf[2].toString()
            numOfString4 = numOf[3].toString()
            numOfString5 = numOf[4].toString()
            numOfString6 = numOf[5].toString()
        }
        binding.numOf1.text = "one: $numOfString1"
        binding.numOf2.text = "two: $numOfString2"
        binding.numOf3.text = "three: $numOfString3"
        binding.numOf4.text = "four: $numOfString4"
        binding.numOf5.text = "five: $numOfString5"
        binding.numOf6.text = "six: $numOfString6"

    }
}

class Dice(val numSides: Int){
    fun roll():Int {
        return (1..numSides).random()
    }
}
