package com.valentinc.whistscoreboard

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.valentinc.whistscoreboard.models.User
import com.valentinc.whistscoreboard.services.DatabaseService


class AddPlayersActivity : AppCompatActivity() {

    private val editTextList = mutableListOf<EditText>()

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
            editTextList.add(editText)

            players++
        }

        val startGameBtn =  findViewById<Button>(R.id.startGameBtn)

        startGameBtn.setOnClickListener{
            saveData();

            val intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
    }

    fun saveData(){
        val thread = Thread {
            var dbService = DatabaseService()
            var db = dbService.getInstance(applicationContext)

            db.userDao().clearTable()

            val users = mutableListOf<User>()

            val editTextListIterator = editTextList.iterator()
            while (editTextListIterator.hasNext()) {
                users.add(User(editTextListIterator.next().text.toString()))
            }

            val userDao = db.userDao()
            userDao.createAll(users)
        }
        thread.start()
    }
}