package olea.rodriguez.julio.bluetoothcontrollerjb;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

public class Listdevices extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdevices);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    ListView IdLista;
    private ArrayAdapter myPairedDevices;

    @Override
    public void onResume()
    {
        super.onResume();

        myPairedDevices = new ArrayAdapter(this, R.layout.dispositivon);
        IdLista = findViewById(R.id.list_devices);
        IdLista.setAdapter(myPairedDevices);
        IdLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView av, View v, int ar, long arg) {

                TextView c = (TextView) v;
                String info = c.getText().toString();
                String address = info.substring(info.length() - 17);

                Intent i = new Intent(Listdevices.this, Movimiento.class);
                i.putExtra("device_address", address);
                startActivity(i);
            }
        });

        Set <BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();

        if (pairedDevices.size() > 0)
        {
            for (BluetoothDevice device : pairedDevices) {
                myPairedDevices.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

}
