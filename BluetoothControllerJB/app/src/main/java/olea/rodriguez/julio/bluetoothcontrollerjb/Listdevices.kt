package olea.rodriguez.julio.bluetoothcontrollerjb

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView


class Listdevices : AppCompatActivity() {

    private var idList: ListView? = null
    private var myPairedDevices: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listdevices)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    }


    public override fun onResume() {
        super.onResume()

        myPairedDevices = ArrayAdapter(this@Listdevices, android.R.layout.simple_list_item_1)
        idList = findViewById(R.id.list_devices)
        idList?.adapter = myPairedDevices
        idList?.onItemClickListener = AdapterView.OnItemClickListener { _, v, _, _ ->
            val c = v as TextView
            val info = c.text.toString()
            val address = info.substring(info.length - 17)

            val i = Intent(this, Movimiento::class.java)
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

}
