package com.valentinc.whistscoreboard

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import com.valentinc.whistscoreboard.adapters.AnswerDialogAdapter
import com.valentinc.whistscoreboard.models.Answer
import com.valentinc.whistscoreboard.models.User
import kotlinx.android.synthetic.main.dialog_bet.*

class AnswerDialogClass
    (var c: Activity, var roundNumber: Int, var playerName: User) : Dialog(c) {

    var d: Dialog? = null

    private lateinit var  answerDialogAdapter: AnswerDialogAdapter
    private var answers = mutableListOf<Answer>()
    var onItemClick: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_bet)

        val predictionResultString = c.getString(R.string.prediction_result).format(playerName.name)

        prediction_textView.text = predictionResultString

        answers.add(Answer(c.getString(R.string.no), false))
        answers.add(Answer(c.getString(R.string.yes), true))

        betRecyclerView.layoutManager = GridLayoutManager(c, 2)
        answerDialogAdapter = AnswerDialogAdapter(c)
        betRecyclerView.adapter = answerDialogAdapter

        answerDialogAdapter.onItemClick = {
                position->
            onItemClick?.invoke(position)
            dismiss()
        }

        answerDialogAdapter.setDataList(answers)
    }
}