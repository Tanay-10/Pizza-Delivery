package com.tanay.ca1_implementation

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar

class OrderActivity : AppCompatActivity() {

    lateinit var rating_dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var progress_dialog: Dialog
        var toolbar: Toolbar = findViewById(R.id.toolbar1)
        toolbar.setTitle("ORDER")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val orderBtn = findViewById<Button>(R.id.place_order)
        orderBtn.setOnClickListener {
            progress_dialog = Dialog(this)
            progress_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            progress_dialog.setContentView(R.layout.progress_dialog)
            progress_dialog.setCanceledOnTouchOutside(false)
            progress_dialog.show()

            val countDownTimer = object : CountDownTimer(3600, 1000) {
                override fun onTick(p0: Long) {

                }

                override fun onFinish() {
                    progress_dialog.dismiss()
//                    Toast.makeText(this@OrderActivity, "Order Placed Successfully",Toast.LENGTH_SHORT).show()
                    displayToast(this)
                }
            }.start()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    fun checkRadioButton(view: View) {
        if (view is RadioButton) {
            val selected = view.isChecked
            if (selected) {
                val radioValue = findViewById<RadioButton>(view.id).text
                Toast.makeText(this, "Crust type selected as : $radioValue", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun checkBoxFunction(view: View) {
        if (view is CheckBox) {
            val selected = view.isChecked
            if (selected) {
                val checkValue = findViewById<CheckBox>(view.id).text
                Toast.makeText(this, "$checkValue topping selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun displayToast(view: CountDownTimer) {
        val vg: ViewGroup? = findViewById(R.id.custom_toast)
        val inflater = layoutInflater
        val customToastLayout: View = inflater.inflate(R.layout.custom_toast, vg)
        val tv = customToastLayout.findViewById(R.id.toastText) as TextView
        val iv = customToastLayout.findViewById(R.id.toastIcon) as ImageView
        tv.text = "Order Placed Successfully"
        val toast = Toast(applicationContext)
        iv.setImageResource(R.drawable.pizza)
//        toast.setGravity(Gravity.BOTTOM, 0, 200)
        toast.duration = Toast.LENGTH_LONG
        toast.setView(customToastLayout)
        toast.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.star) {
            val customLayout = layoutInflater.inflate(R.layout.rating_dialog, null)
            rating_dialog = Dialog(this)
            rating_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            rating_dialog.setContentView(customLayout)
            rating_dialog.setCanceledOnTouchOutside(true)
            rating_dialog.show()
            var window = rating_dialog.window
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            var submitBtn = customLayout.findViewById<Button>(R.id.submitButton)
            submitBtn?.setOnClickListener {
                Log.d("check this", "submit button click")
                rating_dialog.dismiss()
            val intent = Intent(this, MainActivity::class.java)
            finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}