package com.example.androidui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    val isLoginDone = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
//
//        loginBt.setOnClickListener {
//
//            Log.d("AuthActivity", "${(it as Button).text}Login Clicked..")
//        }

        //   emailTt.setText("venky") // inuilt name for username feild.
    }

    override fun onResume() {
        super.onResume()
        val nManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        nManager.cancel(1)
    }

    fun buttonClick(view: View) {
        Log.d("AuthActivity", "${(view as Button).text} Cliked") // cancel button
        finish()  // back to the  page.
    }

    fun loginClick(view: View) {
        Log.d("AuthActivity", "loginButton Clicked")

        val userid = emailTt.text.toString() // creating text feilds for user&password feilds
        val password = passWordTt.text.toString()

//        if (userid.length > 0 && password.length > 0){
//            Toast.makeText(this,
//                "You entered: $userid,$password ",
//                Toast.LENGTH_LONG).show()

        if (userid.isNotEmpty() && password.isNotEmpty()){

            if (password.length < 8){
                Toast.makeText(this, "InValid Password", Toast.LENGTH_LONG).show()
                passWordTt.error = "Password Should be a min 8 char long"
            }else if(getDigitCount(password) < 1) {
                passWordTt.error = "Password Should have one digit Atleast "
            }
            else
                Toast.makeText(this,"you Entered: $userid, $password",
                    Toast.LENGTH_LONG).show()

        }
        else{
            Toast.makeText(this,"pls Enter All Fields",
                Toast.LENGTH_LONG).show()
        }

    }
    fun getDigitCount(str: String): Int{  // to count the digits in password.
        var digitCount = 0
        str.forEach {
            if (it.isDigit()) digitCount++
        }
        return digitCount
    }

    override fun onBackPressed() {
        if(!isLoginDone)
            sendNotification()
        super.onBackPressed()
    }

    private fun sendNotification() {
        //1. get reference of Notification Manager
        val nManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //2. create notification
        val builder : Notification.Builder

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel("test", "AndroidUI", NotificationManager.IMPORTANCE_DEFAULT)

            nManager.createNotificationChannel(channel)

            builder = Notification.Builder(this, "test")
        }else {
            builder = Notification.Builder(this)
        }

        //icon,title, description, action
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setContentTitle("Authentication")
        builder.setContentText("Please complete sign-in")

        val tryIntent = Intent(this, MainActivity::class.java)
        val tryPi = PendingIntent.getActivity(this, 0, tryIntent, 0)

        val retryAction = Notification.Action.Builder(R.drawable.ic_launcher_foreground, "Retry", tryPi).build()
        builder.setActions(retryAction)

        val intent = Intent(this, AuthActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        builder.setContentIntent(pi)
        val myNotification = builder.build()
        myNotification.flags = Notification.FLAG_NO_CLEAR or Notification.FLAG_AUTO_CANCEL

        //show notification

        nManager.notify(1, myNotification)
    }

}