package com.example.gamebacklog.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game

import kotlinx.android.synthetic.main.add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import java.lang.StringBuilder
import java.text.SimpleDateFormat

class AddGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_game)
        setSupportActionBar(toolbar)

        initViews()
    }

    private fun initViews(){
        fab.setOnClickListener { onSaveClick() }

        // Used for creating a back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun validateDateFields(): Boolean {

        var dayOK = false
        var monthOK = false
        var yearOK = false
        var errormsg = ""

        if (ti_Day.text.toString().toInt() in 1..31){
            dayOK = true
        } else {
            errormsg += "Day is invalid. "
        }

        if (ti_Month.text.toString().toInt() in 1..12){
            monthOK = true
        } else {
            errormsg += "Month is invalid. "
        }

        if (ti_Year.text.toString().toInt() in 1950..2040 ){
            yearOK = true
        } else {
            errormsg += "Year is invalid. "
        }


        return if (dayOK && monthOK && yearOK) {
            true
        } else {
            Toast.makeText(this, errormsg, Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun onSaveClick() {
        if (validateDateFields()) {
            if (ti_Name.text.toString().isNotBlank()) {
                val game = Game(
                    ti_Name.text.toString(),
                    ti_Platform.text.toString(),
                    ti_Day.text.toString().toInt(),
                    ti_Month.text.toString().toInt(),
                    ti_Year.text.toString().toInt()
                )
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_GAME, game)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()

            } else {
                Toast.makeText(this, getString(R.string.msg_empty_game), Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_GAME = "EXTRA_GAME"
    }
}
