package com.catnip.loginpageexample.ui.homepage.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.catnip.loginpageexample.R
import com.catnip.loginpageexample.databinding.ActivityHomeBinding
import com.catnip.loginpageexample.databinding.FragmentSpotifyBinding
import com.catnip.loginpageexample.utils.Constants
import com.catnip.loginpageexample.utils.ProtectedMediaChromeClient

class SpotifyFragment : Fragment() {

    private lateinit var binding : FragmentSpotifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSpotifyBinding.inflate(layoutInflater)

        setupWebView(Constants.URL_WEBVIEW_SPOTIFY_HOMEPAGE)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(url : String) {
        binding.wvPageSpotify.settings.javaScriptEnabled = true
        binding.wvPageSpotify.settings.allowContentAccess = true
        binding.wvPageSpotify.settings.domStorageEnabled = true
        binding.wvPageSpotify.webChromeClient = ProtectedMediaChromeClient()
        binding.wvPageSpotify.loadUrl(url)
    }

}