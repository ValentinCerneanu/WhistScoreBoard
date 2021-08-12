package com.valentinc.whistscoreboard

import android.os.Bundle
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class AddPlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        val numberOfPlayers: Int?
        numberOfPlayers = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getInt("number_of_players")
        } else {
            savedInstanceState.getSerializable("number_of_players") as Int?
        }

        val editLinearLayout = findViewById<LinearLayout>(R.id.editTextLinearLayout)

        var players = 1
        while (players <= numberOfPlayers!!) {
            // Create EditText
            val editText = EditText(this)
            editText.setHint("Enter player " + players)
            editText.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            editText.setPadding(20, 20, 20, 20)

            // Add EditText to LinearLayout
            editLinearLayout?.addView(editText)

            players++
        }
    }
}