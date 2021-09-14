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
import com.catnip.loginpageexample.databinding.FragmentYoutubeBinding
import com.catnip.loginpageexample.utils.Constants
import com.catnip.loginpageexample.utils.ProtectedMediaChromeClient

class YoutubeFragment : Fragment() {

    private lateinit var binding : FragmentYoutubeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentYoutubeBinding.inflate(layoutInflater)
        setupWebView(Constants.URL_WEBVIEW_YOUTUBE_HOMEPAGE)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(url : String) {
        binding.wvPageYoutube.settings.javaScriptEnabled = true
        binding.wvPageYoutube.settings.allowContentAccess = true
        binding.wvPageYoutube.settings.domStorageEnabled = true
        binding.wvPageYoutube.webChromeClient = ProtectedMediaChromeClient()
        binding.wvPageYoutube.loadUrl(url)
    }
}