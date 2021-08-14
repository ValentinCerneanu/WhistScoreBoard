package com.valentinc.whistscoreboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.valentinc.whistscoreboard.adapters.HeaderAdapter
import com.valentinc.whistscoreboard.models.User
import com.valentinc.whistscoreboard.services.DatabaseService
import kotlinx.android.synthetic.main.activity_score.*


class ScoreActivity : AppCompatActivity() {

    private lateinit var  headerAdapter: HeaderAdapter
    private var dataList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val players = getPlayers()

        players.observe(this, Observer { player ->

            val playerIterator = player.listIterator()
            recyclerView.layoutManager = GridLayoutManager(applicationContext, player.size)
            headerAdapter = HeaderAdapter(applicationContext)
            recyclerView.adapter = headerAdapter

            while (playerIterator.hasNext()) {
                dataList.add(User(playerIterator.next().name.toString()))
            }
            headerAdapter.setDataList(dataList)
        })
    }

    fun getPlayers(): LiveData<List<User>> {
        var dbService = DatabaseService()
        var db = dbService.getInstance(applicationContext)

        val userDao = db.userDao()
        return userDao.getAll()
    }
}