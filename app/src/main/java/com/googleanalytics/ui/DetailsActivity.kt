package com.googleanalytics.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.googleanalytics.DataProviderManager
import com.googleanalytics.R
import com.googleanalytics.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val categoryName = intent.getStringExtra("categoryName")

        binding.tvTitle.text = categoryName

        // start count time.
        startTime = System.currentTimeMillis()


    }


    override fun onStop() {
        super.onStop()

        DataProviderManager.spentTime(this, "DetailsActivity", startTime)

    }
}