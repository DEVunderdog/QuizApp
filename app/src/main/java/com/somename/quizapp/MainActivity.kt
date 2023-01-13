package com.somename.quizapp

// Create KBC Like Application mix with Image Quiz and Word Quiz

/*
Learnings for finish()
When calling finish() on an activity, the method onDestroy() is executed.
   This method can do things like:
-> Dismiss any dialogs the activity was managing.
-> Close any cursors the activity was managing.
-> Close any open search dialog

Calling finish() in onCreate(): onCreate() -> onDestroy()
Calling finish() in onStart() : onCreate() -> onStart() -> onStop() -> onDestroy()
Calling finish() in onResume(): onCreate() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestroy()

Also notice if you call finish() after an intent you can't go back to the previous activity with
the "back" button
*/

/*
tools in xml means that attribute is going to visible during work i.e. for just designer otherwise when you run the
application you aren't going to see that attribute.
 */

/*
fillViewport allows scrollView to extend it’s height equals to the full height of device screen’s
height in the cases when the child of scroll view has less height.
 */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.somename.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding:ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnStart:Button = binding.btnStart
        val etName:EditText = binding.etInput

        btnStart.setOnClickListener{
            if(etName.text.isEmpty()){
                Toast.makeText(this, "Pleae enter your name", Toast.LENGTH_LONG).show()
            } else{
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME,etName.text.toString())
                /*
                The Android Intent class is commonly used to move from one
                activity to another. The Intent class has a method name putExtra() that you can use
                to put extended data to the intent. The main use of putExtra() method is to send
                values you need in the next activity.

                So Instead of Constants.USER_NAME you can write "user_name" to identify the key. So
                this extra information is being stored on this key and when you need to retrieve
                you use this key.
                 */
                startActivity(intent)
                finish()

            }
        }


    }
}