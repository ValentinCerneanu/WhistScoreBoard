package com.valentinc.whistscoreboard

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import com.valentinc.whistscoreboard.adapters.BetDialogAdapter
import com.valentinc.whistscoreboard.models.User
import kotlinx.android.synthetic.main.dialog_bet.*


class BetDialogClass
    (var c: Activity, var roundNumber: Int, var playerName: User) : Dialog(c) {

    var d: Dialog? = null

    private lateinit var  betDialogAdapter: BetDialogAdapter
    private var predictions = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_bet)

        val predictionString = c.getString(R.string.predict).format(playerName.name, roundNumber)

        prediction_textView.text = predictionString

        for (i in 0..roundNumber) {
            predictions.add(i)
        }

        var numberOfColumns = roundNumber + 1
        if(numberOfColumns >= 3)
            numberOfColumns = 3

        betRecyclerView.layoutManager = GridLayoutManager(c, numberOfColumns)
        betDialogAdapter = BetDialogAdapter(c)
        betRecyclerView.adapter = betDialogAdapter

        betDialogAdapter.setDataList(predictions)
    }
}