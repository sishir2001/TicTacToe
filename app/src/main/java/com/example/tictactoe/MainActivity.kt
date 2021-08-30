package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
//import com.example.tictactoe.TicTacToe as TicTacToe1

class MainActivity : AppCompatActivity() {
    private val arr0 : MutableList<Int> = mutableListOf(0,0,0,0,0,0)
    private val arr1 : MutableList<Int> = mutableListOf(0,0,0,0,0,0)
    private var check = 0
    private var count = 0 // counting no of blocks are disabled
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
            arr0[item.i]++
            arr0[3+item.j]++
        }
        else{
            check = 0
            item.btn.text = getString(R.string.X)
            item.btn.isEnabled = false
            // TODO : add frequency to the ith and jth table
            arr1[item.i]++
            arr1[3+item.j]++
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
        var countOneO = 0
        var res : Int = -1
        var countOneX = 0
        for(a in arr0){
            if(a == 3){
                res = 0
                break
            }
            else if(a == 1)
                countOneO++
        }
        if(countOneO == 6){
            res = 0
        }
        else{
            for(b in arr1){
                if(b == 3){
                    res = 1
                    break
                }
                else if(b == 1)
                    countOneX++
            }
            if(countOneX == 6)
                res = 1
        }
        return res
    }
}