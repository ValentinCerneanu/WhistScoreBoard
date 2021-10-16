package com.valentinc.whistscoreboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentinc.whistscoreboard.services.DatabaseService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_score.*
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.RecyclerView
import com.valentinc.whistscoreboard.adapters.SavedGamesAdapter
import com.valentinc.whistscoreboard.models.GameWrapper

class MainActivity : AppCompatActivity() {

    private lateinit var savedGamesAdapter: SavedGamesAdapter

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

    override fun onResume() {
        super.onResume()
        getSavedGames()
    }

    fun getSavedGames() {
        var gameWrapper = mutableListOf<GameWrapper>()

        savedGamesAdapter = SavedGamesAdapter(this)
        savedGamesAdapter.setDataList(gameWrapper)

        var parentRecyclerView = findViewById<RecyclerView>(R.id.saved_games_list)
        parentRecyclerView.adapter = savedGamesAdapter
        parentRecyclerView.layoutManager = LinearLayoutManager(this);

        val dataService = DatabaseService().getInstance(applicationContext)

        lifecycleScope.launch {
            var games = dataService.gameDao().getAll()
            games.observe(this@MainActivity, Observer { games ->
                for(game in games){
                    var users = dataService.userDao().getUsersByGame(game.uid)

                    users.observe(this@MainActivity, Observer { users ->
                        if(users.size > 0) {
                            gameWrapper.add(GameWrapper(game, users))
                            savedGamesAdapter.notifyDataSetChanged()
                        }
                    })
                }
            })
        }
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