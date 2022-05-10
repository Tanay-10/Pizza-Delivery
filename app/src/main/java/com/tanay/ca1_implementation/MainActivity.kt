package com.tanay.ca1_implementation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("PIZZA !!!")
        setSupportActionBar(toolbar)

        val buttonClick = findViewById<Button>(R.id.proceed)
        buttonClick.setOnClickListener{
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

        displayToast()
    }

    fun displayToast() {
        val vg: ViewGroup?= findViewById(R.id.custom_toast)
        val inflater = layoutInflater
        val customToastLayout: View = inflater.inflate(R.layout.custom_toast,vg)
        val tv = customToastLayout.findViewById(R.id.toastText) as TextView
        val iv = customToastLayout.findViewById(R.id.toastIcon) as ImageView
        tv.text = "Welcome to MAMA MIA !!"
        iv.setImageResource(R.drawable.pizza)
        val toast = Toast(applicationContext)
//        toast.setGravity(Gravity.BOTTOM, 0, 200)
        toast.duration = Toast.LENGTH_SHORT
        toast.setView(customToastLayout)
        toast.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



}