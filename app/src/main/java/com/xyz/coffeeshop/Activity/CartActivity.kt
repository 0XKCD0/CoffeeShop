package com.xyz.coffeeshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.xyz.coffeeshop.Adapter.CartAdapter
import com.xyz.coffeeshop.Helper.ChangeNumberItemsListener
import com.xyz.coffeeshop.R
import com.xyz.coffeeshop.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    lateinit var binding : ActivityCartBinding
    lateinit var management : ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        management = ManagmentCart(this)

        calculatorCart()
        setVariable()
        initCartList()


    }

    private fun initCartList() {
        with(binding){
            cartView.layoutManager = LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            cartView.adapter = CartAdapter(management.getListCart(), this@CartActivity, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    calculatorCart()
                }
            })
        }

    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculatorCart() {
        val percentTax = 0.02
        val delivery = 15.0
        tax = Math.round((management.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((management.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = Math.round(management.getTotalFee()*100)/100

        with(binding){
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }
}