package olea.rodriguez.julio.bluetoothcontrollerjb

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class Listdevices : AppCompatActivity() {

    private var idList: ListView? = null
    private var myPairedDevices: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listdevices)

    }

    public override fun onResume() {
        super.onResume()
        val bluetooth = BluetoothAdapter.getDefaultAdapter()
        if (bluetooth == null) {
            Toast.makeText(applicationContext, "The device has no bluetooth", Toast.LENGTH_LONG).show()
            finish()
        } else {
            if (bluetooth.isEnabled) {
            } else {
                val prender = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(prender)

            }
        }

        myPairedDevices = ArrayAdapter(this@Listdevices, android.R.layout.simple_list_item_1)
        idList = findViewById(R.id.list_devices)
        idList?.adapter = myPairedDevices
        idList?.onItemClickListener = AdapterView.OnItemClickListener { _, v, _, _ ->
            val c = v as TextView
            val info = c.text.toString()
            val address = info.substring(info.length - 17)

            val i = Intent(this, Settings::class.java)
            i.putExtra("device_address", address)
            startActivity(i)
        }

        val pairedDevices = BluetoothAdapter.getDefaultAdapter().bondedDevices



        if (pairedDevices.size > 0) {
            for (device in pairedDevices) {
                myPairedDevices!!.add(device.name + "\n" + device.address)
            }
        }
    }

    fun settings (v: View){
        val i = Intent(this, Settings::class.java)
        i.putExtra("device_address",intent.getStringExtra("device_address"))
        startActivity(i)
    }

}
