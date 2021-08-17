package com.valentinc.whistscoreboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.valentinc.whistscoreboard.adapters.HeaderAdapter
import com.valentinc.whistscoreboard.adapters.RoundScoreAdapter
import com.valentinc.whistscoreboard.models.RoundScore
import com.valentinc.whistscoreboard.models.User
import com.valentinc.whistscoreboard.services.DatabaseService
import kotlinx.android.synthetic.main.activity_score.*


class ScoreActivity : AppCompatActivity() {

    private lateinit var  headerAdapter: HeaderAdapter
    private var userList = mutableListOf<User>()

    private lateinit var  roundScoreAdapter: RoundScoreAdapter
    private var roundScoreList = mutableListOf<RoundScore>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val players = getPlayers()

        var playersNumber = 0

        players.observe(this, Observer { player ->
            playersNumber = player.size
            val playerIterator = player.listIterator()
            headerRecyclerView.layoutManager = GridLayoutManager(applicationContext, playersNumber)
            headerAdapter = HeaderAdapter(applicationContext)
            headerRecyclerView.adapter = headerAdapter

            while (playerIterator.hasNext()) {
                userList.add(User(playerIterator.next().name.toString()))
            }
            headerAdapter.setDataList(userList)


            roundRecyclerView.layoutManager = GridLayoutManager(applicationContext, playersNumber)
            roundScoreAdapter = RoundScoreAdapter(applicationContext)
            roundRecyclerView.adapter = roundScoreAdapter

            //Mock up
            roundScoreList.add(RoundScore(0, 0))
            roundScoreList.add(RoundScore(0, 0))
            roundScoreList.add(RoundScore(0, 0))

            roundScoreList.add(RoundScore(0, 0))
            roundScoreList.add(RoundScore(0, 0))
            roundScoreList.add(RoundScore(0, 0))

            roundScoreAdapter.setDataList(roundScoreList)

        })
    }

    fun getPlayers(): LiveData<List<User>> {
        var dbService = DatabaseService()
        var db = dbService.getInstance(applicationContext)

        val userDao = db.userDao()
        return userDao.getAll()
    }
}