package olea.rodriguez.julio.bluetoothcontrollerjb

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.view.View
import android.widget.*
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class MovimientoA : AppCompatActivity(), SensorEventListener{

    private var btSocket: BluetoothSocket? = null
    private var btConnection: ConnectedThread? = null
    private var accSensor: Sensor? = null
    private var sensorM: SensorManager? = null
    private var xmod: TextView? = null
    private var ymod: TextView? = null
    private var zmod: TextView? = null
    private var btnaccfw: Button? = null
    private var btnaccbw: Button? = null
    private var btnaccrg: Button? = null
    private var btnacclf: Button? = null
    private var status: TextView? = null
    private var statusi: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movimientoa)
        sensorM = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accSensor = sensorM?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorM?.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL)

        xmod = findViewById(R.id.movimientoa_mod_x)
        ymod = findViewById(R.id.movimientoa_mod_y)
        zmod = findViewById(R.id.movimientoa_mod_z)
        btnaccfw = findViewById(R.id.btn_acc_forward)
        btnaccrg = findViewById(R.id.btn_acc_right)
        btnacclf = findViewById(R.id.btn_acc_left)
        btnaccbw = findViewById(R.id.btn_acc_reverse)
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
                }
                else {
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
                    runOnUiThread { s.isEnabled = true }
                }
            }, 2000)
        }
    }

    public override fun onResume() {
        super.onResume()
        Toast.makeText(baseContext, "Face screen UP to the stop car", Toast.LENGTH_LONG).show()
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

    override fun onSensorChanged(e: SensorEvent){
        xmod?.text = e.values[0].toString()
        ymod?.text = e.values[1].toString()
        zmod?.text = e.values[2].toString()
        val y = e.values[1].toDouble()
        val x = e.values[0].toDouble()
        if (btSocket != null && btSocket!!.isConnected) {
            if ((y > 3) && (x < 3) && (x > -3)) {
                btConnection!!.write("S")
                btConnection!!.write("R")
                btnaccfw?.visibility = View.INVISIBLE
                btnacclf?.visibility = View.INVISIBLE
                btnaccbw?.visibility = View.INVISIBLE
                btnaccrg?.visibility = View.VISIBLE
            }
            if ((y < -3) && (x < 3) && (x > -3)) {
                btConnection!!.write("S")
                btConnection!!.write("L")
                btnaccfw?.visibility = View.INVISIBLE
                btnacclf?.visibility = View.VISIBLE
                btnaccbw?.visibility = View.INVISIBLE
                btnaccrg?.visibility = View.INVISIBLE
            }
            if ((x > 3) && (y < 3) && (y > -3)) {
                btConnection!!.write("S")
                btConnection!!.write("B")
                btnaccfw?.visibility = View.INVISIBLE
                btnacclf?.visibility = View.INVISIBLE
                btnaccbw?.visibility = View.VISIBLE
                btnaccrg?.visibility = View.INVISIBLE
            }
            if ((x < -3) && (y < 3) && (y > -3)) {
                btConnection!!.write("S")
                btConnection!!.write("F")
                btnaccfw?.visibility = View.VISIBLE
                btnacclf?.visibility = View.INVISIBLE
                btnaccbw?.visibility = View.INVISIBLE
                btnaccrg?.visibility = View.INVISIBLE
            }
            if ((y > -3) && (y < 3) && (x < 3) && (x > -3)) {
                btConnection!!.write("S")
                btnaccfw?.visibility = View.INVISIBLE
                btnacclf?.visibility = View.INVISIBLE
                btnaccbw?.visibility = View.INVISIBLE
                btnaccrg?.visibility = View.INVISIBLE
            }
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


    fun settings (v: View){
        val i = Intent(this, Settings::class.java)
        i.putExtra("device_address",intent.getStringExtra("device_address"))
        startActivity(i)
    }

}
