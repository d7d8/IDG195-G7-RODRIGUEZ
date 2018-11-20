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
import android.widget.ImageView
import android.widget.TextView
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import android.widget.Switch
import java.util.*


class Movimiento : AppCompatActivity() {

    private var btSocket: BluetoothSocket? = null
    private var btConnection: ConnectedThread? = null
    private var status: TextView? = null
    private var statusi: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movimiento)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val rg = findViewById<Button>(R.id.btn_right)
        val lf = findViewById<Button>(R.id.btn_left)
        val fw = findViewById<Button>(R.id.btn_forward)
        val rv = findViewById<Button>(R.id.btn_reverse)
        val s = findViewById<Switch>(R.id.switchm)

        s.setOnCheckedChangeListener { _, _ ->
            s.isEnabled = false
            if (s.isChecked) {
                val btAdapter = BluetoothAdapter.getDefaultAdapter()
                val intent = intent
                status = findViewById(R.id.txt_stgs_lkd)
                statusi = findViewById(R.id.iv_stgs_lkd)
                statusi?.setImageResource(R.drawable.btinp)
                var st = "Not Connected"
                status?.text = st
                address = intent.getStringExtra("device_address")
                if (address != null) {
                    val device = btAdapter.getRemoteDevice(address)
                    try {
                        btSocket = device.createRfcommSocketToServiceRecord(device.uuids[0].uuid)
                        btSocket!!.connect()
                        if (intent.getStringExtra("device_address") != null) {
                            st = "Connected"
                            status?.text = st
                            statusi?.setImageResource(R.drawable.ic_bluetooth_searching_black_24dp)
                        }
                    } catch (e: IOException) {
                        Toast.makeText(baseContext, "Unable to connect to device", Toast.LENGTH_LONG).show()
                        try {
                            btSocket!!.close()

                        } catch (c: IOException) {
                        }

                    }

                    btConnection = ConnectedThread(btSocket!!)
                    btConnection!!.start()

                } else {
                    st = "Not linked"
                    status?.text = st
                }
            } else {
                if (btSocket != null && btSocket!!.isConnected) {
                    btConnection!!.write("S")
                }
                if (btSocket != null) {
                    try {
                        btSocket!!.close()
                        status = findViewById(R.id.txt_stgs_lkd)
                        statusi = findViewById(R.id.iv_stgs_lkd)
                        statusi?.setImageResource(R.drawable.btinp)
                        val st = "Not Connected"
                        status?.text = st
                    } catch (e2: IOException) {
                    }

                }
            }
            val buttonTimer = Timer()
            buttonTimer.schedule(object : TimerTask() {
                override fun run() {
                    runOnUiThread { s.setEnabled(true) }
                }
            }, 2000)
        }
        rg.setOnTouchListener { _, event ->
            if (btSocket != null && btSocket!!.isConnected) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    btConnection!!.write("R")
                    lf.setEnabled(false)
                    fw.setEnabled(false)
                    rv.setEnabled(false)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    btConnection!!.write("S")
                    lf.setEnabled(true)
                    fw.setEnabled(true)
                    rv.setEnabled(true)
                }
            }
            false
        }
        lf.setOnTouchListener { _, event ->
            if (btSocket != null && btSocket!!.isConnected) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    btConnection!!.write("L")
                    rg.setEnabled(false)
                    fw.setEnabled(false)
                    rv.setEnabled(false)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    btConnection!!.write("S")
                    rg.setEnabled(true)
                    fw.setEnabled(true)
                    rv.setEnabled(true)
                }
            }
            false
        }
        fw.setOnTouchListener { _, event ->
            if (btSocket != null && btSocket!!.isConnected) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    btConnection!!.write("F")
                    lf.setEnabled(false)
                    rg.setEnabled(false)
                    rv.setEnabled(false)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    btConnection!!.write("S")
                    lf.setEnabled(true)
                    rg.setEnabled(true)
                    rv.setEnabled(true)
                }
            }
            false
        }
        rv.setOnTouchListener { _, event ->
            if (btSocket != null && btSocket!!.isConnected) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    btConnection!!.write("B")
                    lf.setEnabled(false)
                    rg.setEnabled(false)
                    fw.setEnabled(false)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    btConnection!!.write("S")
                    lf.setEnabled(true)
                    rg.setEnabled(true)
                    fw.setEnabled(true)
                }
            }
            false
        }

    }

    fun settings(v: View) {
        /*val i = Intent(this, Settings::class.java)
        i.putExtra("device_address", intent.getStringExtra("device_address"))
        startActivity(i)*/
        this.finish()
    }

    public override fun onResume() {

        super.onResume()
    }

    public override fun onPause() {
        super.onPause()
        if (btSocket != null && btSocket!!.isConnected) {
            btConnection!!.write("S")
        }
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
                Toast.makeText(baseContext, "The connection failed", Toast.LENGTH_LONG).show()
                finish()
            }

        }

    }

    companion object {
        private var address: String? = null
    }
}





