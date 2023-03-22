package com.curiel_ruelas.republicserviceschallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curiel_ruelas.republicserviceschallenge.R
import com.curiel_ruelas.republicserviceschallenge.ui.drivers.DriversFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DriversFragment())
                .commitNow()
        }
    }


}