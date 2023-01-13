package com.somename.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.somename.quizapp.databinding.ActivityFinishBinding

class finishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityFinishBinding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tv_name:TextView = binding.tvName
        val tv_score:TextView = binding.tvScore
        val btn_finish: Button = binding.btnFinish

        tv_name.text = intent.getStringExtra(Constants.USER_NAME)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTION, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tv_score.text = "Your score is $correctAnswers out of $totalQuestions"

        btn_finish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}