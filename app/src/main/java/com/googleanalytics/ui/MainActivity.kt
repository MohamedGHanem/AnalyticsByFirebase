package com.googleanalytics.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.googleanalytics.DataProviderManager
import com.googleanalytics.DataProviderManager.CLOTHES
import com.googleanalytics.DataProviderManager.ELECTRONIC
import com.googleanalytics.DataProviderManager.FOOD
import com.googleanalytics.R
import com.googleanalytics.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var sAnalytics: GoogleAnalytics? = null
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    var startTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics
//        firebaseAnalytics.setUserId("123123")

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "screenName")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        }

        // start count time.
        startTime = System.currentTimeMillis()


        sAnalytics = GoogleAnalytics.getInstance(this);
        binding.btnFood.setOnClickListener {

            startActivityWithIntent(FOOD)

        }

        binding.btnClothes.setOnClickListener {
            startActivityWithIntent(CLOTHES)


        }

        binding.btnElectronic.setOnClickListener {
            startActivityWithIntent(ELECTRONIC)

        }

    }


    private fun startActivityWithIntent(txt: String) {
        intent = Intent(this, ProductsActivity::class.java)
        intent.putExtra(DataProviderManager.LAYOUT_KEY, txt)
        startActivity(intent)
    }


    override fun onStop() {
        super.onStop()

        DataProviderManager.spentTime(this, "MainActivity", startTime)

    }

    override fun onRestart() {
        super.onRestart()
        startTime = System.currentTimeMillis()

    }


}
