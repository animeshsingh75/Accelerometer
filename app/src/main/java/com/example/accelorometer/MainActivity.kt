package com.example.accelorometer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    var maxX=0F
    var maxY=0F
    var maxZ=0F
    override fun onAccuracyChanged(s: Sensor?, i: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
//        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
//            getAccelerometer(event)
//        }
        if(event?.sensor?.type==Sensor.TYPE_GYROSCOPE){
            getGyroscope(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

//    private fun getAccelerometer(event: SensorEvent) {
//        // Movement
//        val xVal = event.values[0]
//        val yVal = event.values[1]
//        val zVal = event.values[2]
//        val currentXAxis = findViewById<TextView>(R.id.currentXAxis)
//        val currentYAxis = findViewById<TextView>(R.id.currentYAxis)
//        val currentZAxis = findViewById<TextView>(R.id.currentZAxis)
//        val tvXAxis = findViewById<TextView>(R.id.tvXAxis)
//        val tvYAxis = findViewById<TextView>(R.id.tvYAxis)
//        val tvZAxis = findViewById<TextView>(R.id.tvZAxis)
//        currentXAxis.text = xVal.toString()
//        currentYAxis.text = yVal.toString()
//        currentZAxis.text = zVal.toString()
//        val a = sqrt(xVal.pow(2) + yVal.pow(2) + zVal.pow(2))
//        val g = a / 9.8
//        tvXAxis.text = a.toString()
//        tvYAxis.text = g.toString()
//        if(g>max){
//            max=g
//        }
//        tvZAxis.text=max.toString()
//    }
    private fun getGyroscope(event: SensorEvent) {
        // Movement
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]
        val currentXAxis = findViewById<TextView>(R.id.currentXAxis)
        val currentYAxis = findViewById<TextView>(R.id.currentYAxis)
        val currentZAxis = findViewById<TextView>(R.id.currentZAxis)
        val tvXAxis = findViewById<TextView>(R.id.tvXAxis)
        val tvYAxis = findViewById<TextView>(R.id.tvYAxis)
        val tvZAxis = findViewById<TextView>(R.id.tvZAxis)
        currentXAxis.text = xVal.toString()
        currentYAxis.text = yVal.toString()
        currentZAxis.text = zVal.toString()
        if(abs(xVal)>maxX){
            maxX=abs(xVal)
            tvXAxis.text=maxX.toString()
        }
        if(abs(yVal)>maxY){
            maxY=abs(yVal)
            tvYAxis.text=maxY.toString()
        }
        if(abs(zVal)>maxZ){
            maxZ=abs(zVal)
            tvZAxis.text=maxZ.toString()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(
            this,
            sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }
}