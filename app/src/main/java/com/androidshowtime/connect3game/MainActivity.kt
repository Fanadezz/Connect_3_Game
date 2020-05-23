package com.androidshowtime.connect3game

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    //0:Yellow, 1:Red, 2: Empty
    private var activePlayer = 0
    private var gameActive = true
    private var gameState = mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private val winningPositions = arrayOf(
        arrayOf(0, 1, 2),
        arrayOf(3, 4, 5),
        arrayOf(6, 7, 8),
        arrayOf(0, 3, 6),
        arrayOf(1, 4, 7),
        arrayOf(2, 5, 8),
        arrayOf(0, 4, 8),
        arrayOf(2, 4, 6)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    fun onPlayAgain(view: View) {

        playAgainButton.visibility = View.INVISIBLE
        textView.visibility = View.INVISIBLE

        setImagesDrawablesToNull()
        activePlayer = 0
        gameActive = true
        gameState = mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    }

    fun dropIn(view: View) {


        val counter: ImageView = view as ImageView;

        val tappedCounter = counter.tag.toString().toInt()




        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer// gameState will will be 0 or 1
            counter.translationY = -1500f
            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow)

                activePlayer = 1
            } else {
                counter.setImageResource(R.drawable.red)
                activePlayer = 0

            }

            counter.animate().rotation(360f).translationYBy(1500f).duration = 300

            for (winPosition in winningPositions) {

                if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]]
                    == gameState[winPosition[2]] && gameState[winPosition[0]] != 2
                ) {


                    gameActive = false
                    var winner: String


                    if (activePlayer == 1) {

                        winner = "Yellow"
                    } else {

                        winner = "Red"
                    }
                    /*Toast.makeText(this, "$winner has won" +
                            "", Toast.LENGTH_SHORT).show()
    */
                    playAgainButton.visibility = View.VISIBLE
                    textView.visibility = View.VISIBLE
                    textView.text = "$winner has won"

                }

            }
        }
    }

    fun setImagesDrawablesToNull() {
        gridLayout.imageView1.setImageDrawable(null)
        gridLayout.imageView2.setImageDrawable(null)
        gridLayout.imageView3.setImageDrawable(null)
        gridLayout.imageView4.setImageDrawable(null)
        gridLayout.imageView5.setImageDrawable(null)
        gridLayout.imageView6.setImageDrawable(null)
        gridLayout.imageView7.setImageDrawable(null)
        gridLayout.imageView8.setImageDrawable(null)
        gridLayout.imageView9.setImageDrawable(null)
    }
}
