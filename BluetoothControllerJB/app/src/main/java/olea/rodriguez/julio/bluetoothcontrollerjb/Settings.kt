package olea.rodriguez.julio.bluetoothcontrollerjb

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast



class Settings : AppCompatActivity() {

    private var status: TextView? = null
    private var statusi: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    public override fun onResume() {
        super.onResume()
        status = findViewById(R.id.txt_stgs_lkd)
        statusi = findViewById(R.id.iv_stgs_lkd)
        statusi?.setImageResource(R.drawable.btinp)
        if (intent.getStringExtra("device_address") != null){
            val s: String = "Linked to " + intent.getStringExtra("device_address")
            status?.text = s
            statusi?.setImageResource(R.drawable.ic_bluetooth_searching_black_24dp)
        }
    }


    fun bluetooth(v: View) {
        val bluetooth = BluetoothAdapter.getDefaultAdapter()
        if (bluetooth == null) {
            Toast.makeText(applicationContext, "The device has no bluetooth", Toast.LENGTH_LONG).show()
            finish()
        } else {
            if (bluetooth.isEnabled) {
                val i = Intent(this, Listdevices::class.java)
                i.putExtra("device_address",intent.getStringExtra("device_address"))
                startActivity(i)
            } else {
                val prender = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(prender)
            }
        }
    }

    fun movimientoa(v: View){
        val i = Intent(this, MovimientoA::class.java)
        i.putExtra("device_address", intent.getStringExtra("device_address"))
        startActivity(i)
    }

    fun movimiento(v: View){
        val i = Intent(this, Movimiento::class.java)
        i.putExtra("device_address", intent.getStringExtra("device_address"))
        startActivity(i)
    }

    /*fun informacion(v: View){
        val i = Intent(this, Informacion::class.java)
        i.putExtra("device_address", intent.getStringExtra("device_address"))
        startActivity(i)
    }
*/
}
