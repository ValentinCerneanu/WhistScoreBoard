package com.valentinc.whistscoreboard

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.valentinc.whistscoreboard.models.Game
import com.valentinc.whistscoreboard.models.User
import com.valentinc.whistscoreboard.services.DatabaseService
import java.util.*


class AddPlayersActivity : AppCompatActivity() {

    private val editTextList = mutableListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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
            editText.setPadding(40, 40, 40, 40)
            editText.inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES

            // Add EditText to LinearLayout
            editLinearLayout?.addView(editText)
            editTextList.add(editText)

            players++
        }

        val startGameBtn =  findViewById<Button>(R.id.startGameBtn)

        startGameBtn.setOnClickListener{

            var invalidForm = false
            val editTextListIterator = editTextList.iterator()
            while (editTextListIterator.hasNext()) {
                val editText = editTextListIterator.next()
                if(editText.text.toString() == "")
                {
                    editText.error = "Can't be empty!"
                    editText.requestFocus()
                    invalidForm = true
                }
            }
            if(!invalidForm)
            {
                var gameId = saveData();

                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("game_id", gameId.toString())
                startActivity(intent)
                finish()
            }
        }
    }

    fun saveData(): UUID{
        val dataService = DatabaseService().getInstance(applicationContext)
        val users = mutableListOf<User>()

        var game = Game(Date())

        val editTextListIterator = editTextList.iterator()
        while (editTextListIterator.hasNext()) {
            users.add(User(editTextListIterator.next().text.toString(), game.uid))
        }
        val thread = Thread {
            dataService.gameDao().insert(game)
            dataService.userDao().createAll(users)
        }
        thread.start()
        return game.uid
    }
}