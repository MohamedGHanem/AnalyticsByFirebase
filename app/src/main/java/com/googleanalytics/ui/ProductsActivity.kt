package com.googleanalytics.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.googleanalytics.DataProviderManager
import com.googleanalytics.R
import com.googleanalytics.adapter.ProductAdapter
import com.googleanalytics.databinding.ActivityProductsBinding
import com.googleanalytics.model.CategoriesName

class ProductsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductsBinding
    private lateinit var data: MutableList<CategoriesName>

    var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_products
        )

        val actionBar = supportActionBar
        data = mutableListOf()


        when (intent.getStringExtra(DataProviderManager.LAYOUT_KEY)) {
            DataProviderManager.FOOD -> {
                actionBar!!.title = DataProviderManager.FOOD

                startTime = System.currentTimeMillis()


                data.add(CategoriesName("Pasta"))
                data.add(CategoriesName("Fish"))
                data.add(CategoriesName("Meat"))

            }

            DataProviderManager.CLOTHES -> {
                actionBar!!.title = DataProviderManager.CLOTHES

                startTime = System.currentTimeMillis()


                data.add(CategoriesName("Jeens"))
                data.add(CategoriesName("T shirt"))
                data.add(CategoriesName("Switcher"))

            }

            DataProviderManager.ELECTRONIC -> {
                actionBar!!.title = DataProviderManager.ELECTRONIC

                startTime = System.currentTimeMillis()

                data.add(CategoriesName("Laptop"))
                data.add(CategoriesName("iphone"))
                data.add(CategoriesName("soumi redmi"))


            }
        }

        val productAdapter = ProductAdapter(this, data)
        binding.rvProducts.adapter = productAdapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.setHasFixedSize(true)

    }

    override fun onStop() {
        super.onStop()

        if (startTime.toInt() != 0) {
            DataProviderManager.spentTime(this, "ProductsActivity", startTime)
        }
    }

    override fun onRestart() {
        super.onRestart()
        startTime = System.currentTimeMillis()

    }

}