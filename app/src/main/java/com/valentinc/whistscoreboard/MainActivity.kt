package com.valentinc.whistscoreboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val button3 = findViewById<Button>(R.id.btn3players)
        val button4 = findViewById<Button>(R.id.btn4players)
        val button5 = findViewById<Button>(R.id.btn5players)
        val button6 = findViewById<Button>(R.id.btn6players)

        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
    }

    val listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.btn3players -> {
                val intent = Intent(this, AddPlayersActivity::class.java)
                intent.putExtra("number_of_players", 3)
                startActivity(intent)
            }
            R.id.btn4players -> {
                val intent = Intent(this, AddPlayersActivity::class.java)
                intent.putExtra("number_of_players", 4)
                startActivity(intent)
            }
            R.id.btn5players -> {
                val intent = Intent(this, AddPlayersActivity::class.java)
                intent.putExtra("number_of_players", 5)
                startActivity(intent)
            }
            R.id.btn6players -> {
                val intent = Intent(this, AddPlayersActivity::class.java)
                intent.putExtra("number_of_players", 6)
                startActivity(intent)
            }
        }
    }
}