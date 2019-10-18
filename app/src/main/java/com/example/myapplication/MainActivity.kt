package com.example.myapplication

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private val car = Car()

    fun showGearsList(view: View) {

        val items = arrayOf("Back", "Neutral", "First", "Second", "Third", "Four", "Fifth")
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Gears")
            setItems(items) {dialog, which -> setGear(which - 1)}

            setNegativeButton("Cancel", null)
            show()
        }
    }

    fun showSpeedEditField(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Set speed")
        val dialogLayout = inflater.inflate(R.layout.layout_edit_text, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setNegativeButton("Cancel", null)
        builder.setPositiveButton("Set") {dialogInterface, i -> setSpeed(editText.text.toString().toInt())}
        builder.show()
    }

    private fun setSpeed(speed: Int) {
        val resultSetSpeed = car.SetSpeed(speed)
        if (resultSetSpeed) {
            val speedText = findViewById<TextView>(R.id.speed_text)
            setDirection()
            speedText.text = getSpeedText(car.GetCurrentSpeed())
        } else {
            Toast.makeText(applicationContext, "Couldn't set speed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDirection() {
        val directionText = findViewById<TextView>(R.id.direction_text)
        directionText.text = getDirectionText(car.GetCurrentDirection())
    }

    private fun setGear(gear: Int) {
        val resultSetGear = car.SetGear(gear)
        if (resultSetGear) {
            val gearText = findViewById<TextView>(R.id.gear_text)
            gearText.text = getGearText(car.GetCurrentGear())
        } else {
            Toast.makeText(applicationContext, "Couldn't set gear", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getEngineStatus(engine: Boolean): String {
        return if (engine) {
            "Engine is on"
        } else {
            "Engine is off"
        }
    }

    private fun getGearText(gear: Int): String {
        return "Current gear: $gear"
    }

    private fun getSpeedText(speed: Int): String {
        return "Current speed: $speed"
    }

    private fun getDirectionText(direction: String): String {
        return "Direction: $direction"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val engineText = findViewById<TextView>(R.id.engine_text)

        val buttonEngine = findViewById<Button>(R.id.ToggleEngine)

        engineText.text = getEngineStatus(car.GetStatusEngine())
        setGear(0)
        setSpeed(0)
        setDirection()

        buttonEngine.text = "Turn on"
        buttonEngine.setTextColor(Color.GREEN)

        buttonEngine.setOnClickListener(View.OnClickListener {
            val engineStatus = car.GetStatusEngine()
            var message = getEngineStatus(engineStatus)
            if (engineStatus) {
                val result = car.TurnOffEngine()
                if (result) {
                    buttonEngine.text = "Turn on"
                    buttonEngine.setTextColor(Color.GREEN)
                    message = getEngineStatus(false)
                } else {
                    Toast.makeText(applicationContext, "Couldn't turn off engine", Toast.LENGTH_SHORT).show()
                }
            } else {
                val result = car.TurnOnEngine()
                if (result) {
                    buttonEngine.text = "Turn off"
                    buttonEngine.setTextColor(Color.RED)
                    message = getEngineStatus(true)
                } else {
                    Toast.makeText(applicationContext, "Couldn't turn on engine", Toast.LENGTH_SHORT).show()
                }
            }
            findViewById<TextView>(R.id.engine_text).text = message
        })
    }
}
