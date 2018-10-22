package olea.rodriguez.julio.bluetoothcontrollerjb

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    fun bluetooth(v: View) {
        val bluetooth = BluetoothAdapter.getDefaultAdapter()
        if (bluetooth == null) {
            Toast.makeText(applicationContext, "The device has no bluetooth", Toast.LENGTH_LONG).show()
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

}
