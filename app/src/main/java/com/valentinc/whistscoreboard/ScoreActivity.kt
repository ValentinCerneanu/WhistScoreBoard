package com.valentinc.whistscoreboard

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.valentinc.whistscoreboard.services.DatabaseService

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val headerLinearLayout = findViewById<LinearLayout>(R.id.headerLinearLayout)

        val numberOfPlayers = 4

        getPlayers()

        var players = 1
        while (players <= numberOfPlayers!!) {

            val textView = TextView(this)
            textView.text = "Player " + players
            textView.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            textView.setPadding(20, 20, 20, 20)

            headerLinearLayout?.addView(textView)

            players++
        }
    }

    fun getPlayers(){
        val thread = Thread {
            var dbService = DatabaseService()
            var db = dbService.getInstance(applicationContext)

            val userDao = db.userDao()
            val userList = userDao.getAll()
        }
        thread.start()
    }
}