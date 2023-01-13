package com.somename.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.somename.quizapp.databinding.ActivityMainBinding
import com.somename.quizapp.databinding.ActivityQuizQuestionBinding




class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question> ?= null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswer: Int = 0
    /*
    There is a simple logic beside creating this above three varaiable which is to increase the scope
    of those variable. Those three variables have global scope.
     */

    private var mUserName:String ?= null

    private lateinit var binding: ActivityMainBinding
    private var progressBar:ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion:TextView? = null
    private var tvImage: ImageView? = null

    private var tvOptionOne:TextView? = null
    private var tvOptionTwo:TextView? = null
    private var tvOptionThree:TextView? = null
    private var tvOptionFour:TextView? = null
    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityQuizQuestionBinding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = binding.progressBar
        tvProgress = binding.tvProgress
        tvQuestion = binding.tvQuestion
        tvImage = binding.ivImage
        tvOptionOne = binding.tvOptionOne
        tvOptionTwo = binding.tvOptionTwo
        tvOptionThree = binding.tvOptionThree
        tvOptionFour = binding.tvOptionFour
        btnSubmit = binding.btnSubmit

        /*
        setOnClickListener is setting the OnClickListener in this file i.e. QuizQuestionActivity
         */
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()
        // It has global scope. This questions can be accessible to anyone within this QuizQuestionActivity
        // class.

        setQuestion()
    }

    private fun setQuestion() {
        // val questionList = Constants.getQuestions()
        // The scope of this expression was just in the function
        /*
        Log.i("Questions size is ", "${questionList.size}")

        for (i in questionList) {
            Log.e("Questions", i.question)
        }
        */
        defaultOptionsView()
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        tvImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if(mCurrentPosition == mQuestionList!!.size){
            btnSubmit?.text = "FINISH"
        } else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        // This all has been done because tvOptionsOne is null.
        // It is same as
        /*
        options.add(0,tvOptionOne) but type mismatch will occur
        because options is of TextView and tvOptions is of TextView?
         */
        tvOptionOne?.let{
            options.add(0,it)
        }
        tvOptionTwo?.let{
            options.add(0,it)
        }
        tvOptionThree?.let{
            options.add(0,it)
        }
        tvOptionFour?.let{
            options.add(0,it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                tvOptionOne?.let { selectedOptionView(it,1) }
            }
            R.id.tv_option_two ->{
                tvOptionTwo?.let{selectedOptionView(it, 2)}
            }
            R.id.tv_option_three ->{
                tvOptionThree?.let{selectedOptionView(it, 3)}
            }
            R.id.tv_option_four ->{
                tvOptionFour?.let{selectedOptionView(it, 4)}
            }
            R.id.btn_submit -> {
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, finishActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTION, mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    } else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btnSubmit?.text = "Finish"
                    } else{
                        btnSubmit?.text = "Next"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}