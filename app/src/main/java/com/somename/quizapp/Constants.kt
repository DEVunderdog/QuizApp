package com.somename.quizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTION:String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answer"
    // We have created a keys which would the medium to share information betweeen the activities.
    // This are actually keys and remember those string are just written to identify the keys they
    // are not the values.

    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        //1
        val que1 = Question(
            1,
            "what country does this flag belong to?",
            R.drawable.flag_afghan,
            "Afghanistan",
            "Austrailia",
            "India",
            "USA",
            1
        )
        //2
        val que2 = Question(
            2,
            "what country does this flag belong to?",
            R.drawable.ic_australia,
            "Argentina,",
            "Austrailia",
            "India",
            "USA",
            2
        )
        //3
        val que3 = Question(
            3,
            "what country does this flag belong to?",
            R.drawable.ic_belgium,
            "Argentina,",
            "Belgium",
            "Kuwait",
            "UAE",
            2
        )
        //4
        val que4 = Question(
            4,
            "what country does this flag belong to?",
            R.drawable.ic_brazil,
            "France",
            "Austrailia",
            "Brazil",
            "Croatia",
            3
        )
        //5
        val que5 = Question(
            5,
            "what country does this flag belong to?",
            R.drawable.ic_denmark,
            "India",
            "Denmark",
            "Brazil",
            "Croatia",
            2
        )
        //6
        val que6 = Question(
            6,
            "what country does this flag belong to?",
            R.drawable.ic_fiji,
            "Carribean",
            "Turkey",
            "Brazil",
            "Fiji",
            4
        )
        //7
        val que7 = Question(
            7,
            "what country does this flag belong to?",
            R.drawable.ic_germany,
            "France",
            "Ukraine",
            "Germany",
            "Croatia",
            3
        )
        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        questionsList.add(que6)
        questionsList.add(que7)

        return questionsList
    }
}