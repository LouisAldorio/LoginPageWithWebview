package com.catnip.loginpageexample.ui.homepage

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.catnip.loginpageexample.R
import com.catnip.loginpageexample.data.preference.UserPreference
import com.catnip.loginpageexample.databinding.ActivityHomeBinding
import com.catnip.loginpageexample.ui.homepage.fragment.SpotifyFragment
import com.catnip.loginpageexample.ui.homepage.fragment.YoutubeFragment
import com.catnip.loginpageexample.ui.login.LoginActivity
import com.catnip.loginpageexample.utils.Constants
import com.catnip.loginpageexample.utils.ProtectedMediaChromeClient
import com.shashank.sony.fancytoastlib.FancyToast

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var spotifyFragment = SpotifyFragment()
    private var youtubeFragment = YoutubeFragment()
    private var fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.text_actionbar_homepage)

        //supportFragmentManager always take the last added fragment to be the default displayed fragment
        fragmentManager.beginTransaction().apply {
            add(binding.flContainer.id, youtubeFragment)
            add(binding.flContainer.id, spotifyFragment)
        }.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_homepage, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_logout -> {
                FancyToast.makeText(
                    this,
                    getString(R.string.text_toast_logout_success),
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    true
                ).show();

                deleteSessionData()
                navigateToLogin()
            }
            R.id.menu_action_spotify -> {
                fragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.slide_left_in, R.anim.slide_right_out
                ).hide(youtubeFragment).show(spotifyFragment).commit()
            }
            R.id.menu_action_youtube -> {
                fragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.slide_right_in, R.anim.slide_left_out
                ).hide(spotifyFragment).show(youtubeFragment).commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)

        // do not add current activity to stack after navigate to other activity
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun deleteSessionData() {
        UserPreference(this).isUserLoggedIn = false
    }
}