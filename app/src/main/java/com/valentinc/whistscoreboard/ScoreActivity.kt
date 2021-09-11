package com.valentinc.whistscoreboard

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.valentinc.whistscoreboard.adapters.HeaderAdapter
import com.valentinc.whistscoreboard.adapters.RoundNumberAdapter
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

    private lateinit var  roundNumberAdapter: RoundNumberAdapter
    private var roundNumberList = mutableListOf<Int>()

    private lateinit var playButton: ImageButton
    private lateinit var backButton: ImageButton

    private var isGameStarted: Boolean = false
    private var currentRound: Int = 0
    private var currentPlayer: Int = 0
    private var betIsDone: Boolean = false
    private var sumOfBets: Int = 0
    private var playersNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val players = getPlayers()

        players.observe(this, Observer { player ->
            playersNumber = player.size
            val playerIterator = player.listIterator()

            //Round Header
            headerRecyclerView.layoutManager = GridLayoutManager(applicationContext, playersNumber)
            headerAdapter = HeaderAdapter(applicationContext)
            headerRecyclerView.adapter = headerAdapter

            while (playerIterator.hasNext()) {
                userList.add(User(playerIterator.next().name.toString()))
            }
            headerAdapter.setDataList(userList)

            //Round Number
            round_number_RecyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
            roundNumberAdapter = RoundNumberAdapter(applicationContext)
            round_number_RecyclerView.adapter = roundNumberAdapter

            for (i in 1..playersNumber) {
                roundNumberList.add(1)
            }

            roundNumberList.add(2)
            roundNumberList.add(3)
            roundNumberList.add(4)
            roundNumberList.add(5)
            roundNumberList.add(6)
            roundNumberList.add(7)

            for (i in 1..playersNumber) {
                roundNumberList.add(8)
            }

            roundNumberList.add(7)
            roundNumberList.add(6)
            roundNumberList.add(5)
            roundNumberList.add(4)
            roundNumberList.add(3)
            roundNumberList.add(2)

            for (i in 1..playersNumber) {
                roundNumberList.add(1)
            }
            roundNumberAdapter.setDataList(roundNumberList)

            //Round Score
            roundRecyclerView.layoutManager = GridLayoutManager(applicationContext, playersNumber)
            roundScoreAdapter = RoundScoreAdapter(applicationContext)
            roundRecyclerView.adapter = roundScoreAdapter

            for (i in 1..roundNumberList.size) {
                for (j in 1..playersNumber) {
                    roundScoreList.add(RoundScore(0, 0))
                }
            }
            roundScoreAdapter.setDataList(roundScoreList)
        })

        playButton = findViewById<ImageButton>(R.id.playImageView)
        playButton.setOnClickListener {
            if (!isGameStarted){
                isGameStarted = true
                playButton.setImageResource(R.drawable.icon_arrow_next)
                backButton.visibility = View.VISIBLE
            }

            if(!betIsDone) {
                lateinit var betDialogClass: BetDialogClass
                if(currentPlayer == playersNumber - 1)
                {
                    betDialogClass =
                        BetDialogClass(this, roundNumberList[currentRound], userList[currentPlayer], sumOfBets)
                }
                else {
                    betDialogClass =
                        BetDialogClass(this, roundNumberList[currentRound], userList[currentPlayer])
                }
                betDialogClass.show()
                betDialogClass.onItemClick = { position ->
                    roundScoreList[playersNumber * currentRound + currentPlayer].bet =
                        position
                    sumOfBets += position
                    roundScoreAdapter.notifyDataSetChanged()

                    currentPlayer++
                    if (currentPlayer == playersNumber) {
                        currentPlayer = 0
                        sumOfBets = 0
                        betIsDone = true
                    }
                }
            } else {
                val scoreDialogClass = AnswerDialogClass(this, roundNumberList[currentRound], userList[currentPlayer])
                scoreDialogClass.show()
                scoreDialogClass.onItemClick = { position ->
                    if(position) {
                        if (currentRound > 0)
                            roundScoreList[playersNumber * currentRound + currentPlayer].score =
                                roundScoreList[playersNumber * (currentRound - 1) + currentPlayer].score + 5 + roundScoreList[playersNumber * currentRound + currentPlayer].bet
                        else
                            roundScoreList[playersNumber * currentRound + currentPlayer].score =
                                5 + roundScoreList[playersNumber * currentRound + currentPlayer].bet
                    }
                    else {
                        var actualBetDialogClass: BetDialogClass
                        actualBetDialogClass = BetDialogClass(this, roundNumberList[currentRound], userList[currentPlayer], sumOfBets, false)
                        actualBetDialogClass.show()
                        actualBetDialogClass.onItemClick = { position ->
                            if (currentRound > 0)
                                roundScoreList[playersNumber * currentRound + currentPlayer].score =
                                    roundScoreList[playersNumber * (currentRound - 1) + currentPlayer].score - kotlin.math.abs(
                                        position - roundScoreList[playersNumber * currentRound + currentPlayer].bet
                                    )
                            else
                                roundScoreList[playersNumber * currentRound + currentPlayer].score =
                                    - kotlin.math.abs(position - roundScoreList[playersNumber * currentRound + currentPlayer].bet)
                        }
                    }
                    userList[currentPlayer].score = roundScoreList[playersNumber * currentRound + currentPlayer].score
                    headerAdapter.notifyDataSetChanged()
                    finishRound()
                }
            }

        }

        backButton = findViewById<ImageButton>(R.id.backImageView)
        backButton.visibility = View.GONE
        backButton.setOnClickListener { view ->
            if ((!betIsDone  && currentPlayer != 0) || (currentPlayer == 0 && betIsDone)) {
                if (currentPlayer == 0) {
                    currentPlayer = playersNumber

                    sumOfBets = 0
                    for (i in 1..playersNumber) {
                        sumOfBets += roundScoreList[playersNumber * currentRound + currentPlayer - i].bet
                    }
                    betIsDone = false
                }
                currentPlayer--

                sumOfBets -= roundScoreList[playersNumber * currentRound + currentPlayer].bet
                roundScoreList[playersNumber * currentRound + currentPlayer].bet = 0
                roundScoreAdapter.notifyDataSetChanged()

                if(currentPlayer == 0 && currentRound == 0){
                    backButton.visibility = View.GONE
                    isGameStarted = false
                    playButton.setImageResource(R.drawable.icon_play)
                }
            } else {
                if (currentPlayer == 0) {
                    currentPlayer = playersNumber
                    betIsDone = true
                    currentRound--
                }
                currentPlayer--
                roundScoreList[playersNumber * currentRound + currentPlayer].score = 0
                roundScoreAdapter.notifyDataSetChanged()

                if(currentRound == 0)
                    userList[currentPlayer].score = 0
                else
                    userList[currentPlayer].score = roundScoreList[playersNumber * currentRound + currentPlayer - playersNumber].score
                headerAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun finishRound(){
        roundScoreAdapter.notifyDataSetChanged()

        currentPlayer++
        if (currentPlayer == playersNumber) {
            currentPlayer = 0
            currentRound++
            betIsDone = false
        }
    }

    fun getPlayers(): LiveData<List<User>> {
        var dbService = DatabaseService()
        var db = dbService.getInstance(applicationContext)

        val userDao = db.userDao()
        return userDao.getAll()
    }
}