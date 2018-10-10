package olea.rodriguez.julio.bluetoothcontrollerjb

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.content.Intent

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class Movimiento : AppCompatActivity() {

    private var btSocket: BluetoothSocket? = null
    private var myConexionBT: ConnectedThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movimiento)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val rg = findViewById<Button>(R.id.btn_right)
        val lf = findViewById<Button>(R.id.btn_left)
        val fw = findViewById<Button>(R.id.btn_forward)
        val rv = findViewById<Button>(R.id.btn_reverse)


        rg.setOnTouchListener { _, event ->
            if (btSocket != null && btSocket!!.isConnected) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    myConexionBT!!.write("R")
                } else if (event.action == MotionEvent.ACTION_UP) {
                    myConexionBT!!.write("S")
                }
            }
            false
        }
        lf.setOnTouchListener { _, event ->
            if (btSocket != null && btSocket!!.isConnected) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    myConexionBT!!.write("L")
                } else if (event.action == MotionEvent.ACTION_UP) {
                    myConexionBT!!.write("S")
                }
            }
            false
        }
        fw.setOnTouchListener { _, event ->
            if (btSocket != null && btSocket!!.isConnected) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    myConexionBT!!.write("F")
                } else if (event.action == MotionEvent.ACTION_UP) {
                    myConexionBT!!.write("S")
                }
            }
            false
        }
        rv.setOnTouchListener { _, event ->
            if (btSocket != null && btSocket!!.isConnected) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    myConexionBT!!.write("B")
                } else if (event.action == MotionEvent.ACTION_UP) {
                    myConexionBT!!.write("S")
                }
            }
            false
        }

    }

    fun settings(v: View) {
        val bluetooth = BluetoothAdapter.getDefaultAdapter()
        if (bluetooth == null) {
            Toast.makeText(applicationContext, "El dispositivo no cuenta con bluetooth, la aplicación se cerrará", Toast.LENGTH_LONG).show()
            finish()
        } else {
            if (bluetooth.isEnabled) {
                val i = Intent(this, Listdevices::class.java)
                startActivity(i)
            } else {
                val prender = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(prender)
            }
        }
    }


    public override fun onResume() {

        val btAdapter = BluetoothAdapter.getDefaultAdapter()
        super.onResume()

        val intent = intent
        address = intent.getStringExtra("device_address")
        if (address != null) {
            val device = btAdapter.getRemoteDevice(address)

            try {
                btSocket = device.createInsecureRfcommSocketToServiceRecord(device.uuids[0].uuid)
                btSocket!!.connect()
            } catch (e: IOException) {
                Toast.makeText(baseContext, "Unable to connect to device", Toast.LENGTH_LONG).show()
                try {
                    btSocket!!.close()
                } catch (c: IOException) {
                }

            }

            myConexionBT = ConnectedThread(btSocket!!)
            myConexionBT!!.start()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (btSocket != null) {
            try {
                btSocket!!.close()
            } catch (e2: IOException) {
            }

        }
    }

    private inner class ConnectedThread constructor(socket: BluetoothSocket) : Thread() {
        private val mmInStream: InputStream?
        private val mmOutStream: OutputStream?

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null
            try {
                tmpIn = socket.inputStream
                tmpOut = socket.outputStream
            } catch (e: IOException) {
            }

            mmInStream = tmpIn
            mmOutStream = tmpOut
        }


        fun write(input: String) {
            try {
                mmOutStream!!.write(input.toByteArray())
            } catch (e: IOException) {
                Toast.makeText(baseContext, "La Conexión fallo", Toast.LENGTH_LONG).show()
                finish()
            }

        }

    }

    companion object {
        private var address: String? = null
    }
}





