package com.example.liarsdice

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.liarsdice.databinding.ActivityMain2Binding


val dice = Dice(6)
val diceRoll = arrayOfNulls<Int>(13)
val diceImage = arrayOfNulls<ImageView>(13)
val drawableResource = arrayOfNulls<Int>(13)
var diceArr = arrayOf<Any>()
class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
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

        binding.rollButton.setOnClickListener {
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
            toast.show()

            if (binding.inputDiceAmount.text.isNullOrBlank()) {
                binding.inputDiceAmount.setText("5")
                diceRemoveSort = removeDice() as List<Int>
            }else {
                diceRemoveSort = removeDice() as List<Int>
            }
            diceArr=rollDice()
        }
        binding.red.setOnClickListener {
            var diceAmount = binding.inputDiceAmount.text.toString().toInt()
            diceAmount -= takeOutDice(diceArr[1] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[2] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[4] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[5] as Array<Int>)
            if (diceAmount<0)
                diceAmount=0
            binding.inputDiceAmount.setText("$diceAmount")

        }
        binding.black.setOnClickListener {

            var diceAmount = binding.inputDiceAmount.text.toString().toInt()
            diceAmount -= takeOutDice(diceArr[0] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[3] as Array<Int>)
            if (diceAmount<0)
                diceAmount=0
            binding.inputDiceAmount.setText("$diceAmount")
        }
        binding.single.setOnClickListener {
            var diceAmount = binding.inputDiceAmount.text.toString().toInt()
            diceAmount -= takeOutDice(diceArr[1] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[3] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[5] as Array<Int>)
            if (diceAmount<0)
                diceAmount=0
            binding.inputDiceAmount.setText("$diceAmount")
        }
        binding.dbl.setOnClickListener {
            var diceAmount = binding.inputDiceAmount.text.toString().toInt()
            diceAmount -= takeOutDice(diceArr[0] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[2] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[4] as Array<Int>)
            if (diceAmount<0)
                diceAmount=0
            binding.inputDiceAmount.setText("$diceAmount")
        }
        binding.big.setOnClickListener {
            var diceAmount = binding.inputDiceAmount.text.toString().toInt()
            diceAmount -= takeOutDice(diceArr[0] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[1] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[2] as Array<Int>)
            if (diceAmount<0)
                diceAmount=0
            binding.inputDiceAmount.setText("$diceAmount")
        }
        binding.small.setOnClickListener {
            var diceAmount = binding.inputDiceAmount.text.toString().toInt()
            diceAmount -= takeOutDice(diceArr[3] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[4] as Array<Int>)
            diceAmount -= takeOutDice(diceArr[5] as Array<Int>)
            if (diceAmount<0)
                diceAmount=0
            binding.inputDiceAmount.setText("$diceAmount")
        }
        binding.Open.setOnClickListener {
            binding.cloche.setImageResource(R.drawable.empty)
        }
        binding.liars.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
    private fun takeOutDice(sortOf:Array<Int>): Int {
        val size = sortOf.size
        for (m in 0..size-1) {
            diceImage[sortOf[m]]!!.setImageResource(R.drawable.empty)
        }
        return size
    }
    fun removeDice(): List<Int?> {
        val diceCounter = (binding.inputDiceAmount.text.toString().toInt())
        val diceNotPicked = (0..12).shuffled().take(13-diceCounter)
        return diceNotPicked
    }
    private fun rollDice(): Array<Any> {
        var sortOf1= arrayOf<Int>()
        var sortOf2= arrayOf<Int>()
        var sortOf3= arrayOf<Int>()
        var sortOf4= arrayOf<Int>()
        var sortOf5= arrayOf<Int>()
        var sortOf6= arrayOf<Int>()

        for (i in 0..12) {
            diceRoll[i] = dice.roll()
        }

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
                    1 -> sortOf1 += m
                    2 -> sortOf2 += m
                    3 -> sortOf3 += m
                    4 -> sortOf4 += m
                    5 -> sortOf5 += m
                    else -> sortOf6 += m

                }
            }
        }
        binding.cloche.setImageResource(R.drawable.cloche)
        return arrayOf(sortOf1, sortOf2, sortOf3, sortOf4, sortOf5, sortOf6)

        /*for (n in 0..diceAmount-1) {
            diceImage[n]!!.contentDescription = diceRoll[n].toString()
        }*/
    }
}
