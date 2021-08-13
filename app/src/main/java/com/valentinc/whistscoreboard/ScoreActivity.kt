package com.valentinc.whistscoreboard

import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val editLinearLayout = findViewById<LinearLayout>(R.id.headerLinearLayout)

        val numberOfPlayers = 4

        var players = 1
        while (players <= numberOfPlayers!!) {
            // Create EditText
            val textView = TextView(this)
            textView.text = "Player " + players
            textView.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            textView.setPadding(20, 20, 20, 20)

            // Add EditText to LinearLayout
            editLinearLayout?.addView(textView)

            players++
        }
    }
}