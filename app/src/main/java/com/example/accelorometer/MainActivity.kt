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


class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null

    var prevX: Float? = null
    var prevY: Float? = null
    var prevZ: Float? = null
    var diffX: Float? = null
    var diffY: Float? = null
    var diffZ: Float? = null
    override fun onAccuracyChanged(s: Sensor?, i: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }else if (event?.sensor?.type==Sensor.TYPE_GYROSCOPE){
            getGyro(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private fun getAccelerometer(event: SensorEvent) {
        // Movement
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]
//        val tvXAxis=findViewById<TextView>(R.id.tvXAxis)
//        val tvYAxis=findViewById<TextView>(R.id.tvYAxis)
//        val tvZAxis=findViewById<TextView>(R.id.tvZAxis)
        val currentXAxis=findViewById<TextView>(R.id.currentXAxis)
        val currentYAxis=findViewById<TextView>(R.id.currentYAxis)
        val currentZAxis=findViewById<TextView>(R.id.currentZAxis)
        currentXAxis.text=xVal.toString()
        currentYAxis.text=yVal.toString()
        currentZAxis.text=zVal.toString()
//        if (prevX == null) {
//            prevX = xVal
//            prevY = yVal
//            prevZ = zVal
//            diffX = 0F
//            diffY = 0F
//            diffZ = 0F
//        } else {
//            if (diffX!! < abs(xVal - prevX!!)) {
//                diffX = abs(xVal - prevX!!)
//                tvXAxis.text=diffX.toString()
//            }
//            if (diffY!! < abs(yVal - prevY!!)) {
//                diffY = abs(yVal - prevY!!)
//                tvYAxis.text=diffY.toString()
//            }
//            if (diffZ!! < abs(zVal - prevZ!!)) {
//                diffZ = abs(zVal - prevZ!!)
//                tvZAxis.text=diffZ.toString()
//            }
//        }
    }

    private fun getGyro(event: SensorEvent) {
        // Movement
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]
        val tvXAxis=findViewById<TextView>(R.id.tvXAxis)
        val tvYAxis=findViewById<TextView>(R.id.tvYAxis)
        val tvZAxis=findViewById<TextView>(R.id.tvZAxis)
        tvXAxis.text=xVal.toString()
        tvYAxis.text=yVal.toString()
        tvZAxis.text=zVal.toString()
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(
            this,
            sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
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