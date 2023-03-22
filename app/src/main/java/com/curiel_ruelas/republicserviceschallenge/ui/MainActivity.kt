package com.curiel_ruelas.republicserviceschallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.curiel_ruelas.republicserviceschallenge.R
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import com.curiel_ruelas.republicserviceschallenge.ui.drivers.DriversFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition() {
                when (viewModel.isReady.value) {
                    is Resource.Success -> true
                    is Resource.Error -> {
                        Toast.makeText(
                            this@MainActivity,
                            (viewModel.isReady.value as Resource.Error<Boolean>).msg,
                            Toast.LENGTH_LONG
                        ).show()
                        true
                    }
                    is Resource.Loading -> false
                    else -> false
                }

            }

        }
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DriversFragment())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, DriversFragment())
            .commitNow()

    }
}