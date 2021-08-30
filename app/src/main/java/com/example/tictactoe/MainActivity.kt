package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
//import com.example.tictactoe.TicTacToe as TicTacToe1

class MainActivity : AppCompatActivity() {
    private val arr0 : MutableList<Int> = mutableListOf(0,0,0,0,0,0)
    private val arr1 : MutableList<Int> = mutableListOf(0,0,0,0,0,0)
    private var check = 0
    private var count = 0 // counting no of blocks are disabled
    private var diagO = 0
    private var rdiagO = 0
    private var rdiagX = 0
    private var diagX = 0

    lateinit var playerO : EditText
    lateinit var playerX : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       playerO = findViewById(R.id.EditTextPl0)
        playerX = findViewById(R.id.EditTextPl0)
        // initiliazing dataclass buttons
        val btns : List<TicTacToe> = listOf(
            TicTacToe(findViewById(R.id.button00),0,0),
            TicTacToe(findViewById(R.id.button01),0,1),
            TicTacToe(findViewById(R.id.button02),0,2),
            TicTacToe(findViewById(R.id.button10),1,0),
            TicTacToe(findViewById(R.id.button11),1,1),
            TicTacToe(findViewById(R.id.button12),1,2),
            TicTacToe(findViewById(R.id.button20),2,0),
            TicTacToe(findViewById(R.id.button21),2,1),
            TicTacToe(findViewById(R.id.button22),2,2)
        )
        startPlay(btns)
    }
    private fun startPlay(view:List<TicTacToe>){
       for(item in view){
           item.btn.setOnClickListener { createXO(item) }
       }
    }
    private fun createXO(item: TicTacToe){
        count ++
        if(check == 0){
            check = 1
            item.btn.text = getString(R.string.O)
            item.btn.isEnabled = false
            // TODO : add frequency to the ith and jth table
            if(item.i == item.j)
                diagO++
            else if(item.i+item.j == 2)
                rdiagO++
            else{
                arr0[item.i]++
                arr0[3+item.j]++
            }
        }
        else{
            check = 0
            item.btn.text = getString(R.string.X)
            item.btn.isEnabled = false
            // TODO : add frequency to the ith and jth table
            if(item.i == item.j)
                diagX++
            else if(item.i+item.j == 2)
                rdiagX++
            else{
                arr1[item.i]++
                arr1[3+item.j]++
            }
        }
        if(count == 9 && checkComplete() == -1)
            Toast.makeText(this,"Draw!!",Toast.LENGTH_SHORT).show()
        else if(checkComplete() == 0){
            Toast.makeText(this,"${playerO.text} Has Won",Toast.LENGTH_SHORT).show()
            // disabling all the buttons
        }
        else if(checkComplete() == 1)
            Toast.makeText(this,"${playerX.text} Has Won",Toast.LENGTH_SHORT).show()

    }
    fun checkComplete():Int{
        // TODO : write conditions to choose a winner
        // traverse through both the arrays to check whether an element is 3 or all the elements are 1
        var res : Int = -1
        if(diagO == 3 || rdiagO == 3)
            res = 0
        else if(diagX == 3|| rdiagX == 3)
            res = 1
        else {
            for (a in arr0) {
                if (a == 3) {
                    res = 0
                    break
                }
            }
            for (b in arr1) {
                if (b == 3) {
                    res = 1
                    break
                }
            }
        }
        Log.i("MainActivity","res = $res")
        return res
    }
}