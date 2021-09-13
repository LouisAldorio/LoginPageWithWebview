package com.catnip.loginpageexample.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.catnip.loginpageexample.R
import com.catnip.loginpageexample.data.preference.UserPreference
import com.catnip.loginpageexample.ui.homepage.HomeActivity
import com.catnip.loginpageexample.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private var timer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        setSplashTimer()
    }

    private fun setSplashTimer() {
        timer = object : CountDownTimer(3000,1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                if(UserPreference(this@SplashScreenActivity).isUserLoggedIn) {
                    val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)

                    // do not add current activity to stack after navigate to other activity
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }else {
                    val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)

                    // do not add current activity to stack after navigate to other activity
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }

        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.let {
            it.cancel()
            timer = null
        }
    }
}