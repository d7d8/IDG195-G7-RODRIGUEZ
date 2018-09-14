package olea.rodriguez.julio.bluetoothcontrollerjb;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothDevice;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.view.View.OnTouchListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;



public class Movimiento extends AppCompatActivity {

    private BluetoothSocket btSocket = null;
    private ConnectedThread MyConexionBT;
    private static String address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button rg = findViewById(R.id.btn_right);
        Button lf = findViewById(R.id.btn_left);
        Button fw = findViewById(R.id.btn_forward);
        Button rv = findViewById(R.id.btn_reverse);


        rg.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((btSocket != null) && (btSocket.isConnected())) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MyConexionBT.write("R");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    MyConexionBT.write("S");
                }
            }
                return false;
            }
        });
        lf.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if((btSocket!=null)&&(btSocket.isConnected())) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        MyConexionBT.write("L");
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        MyConexionBT.write("S");
                    }
                }
                return false;
            }
        });
        fw.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if((btSocket!=null)&&(btSocket.isConnected())) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        MyConexionBT.write("F");
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        MyConexionBT.write("S");
                    }
                }
                return false;
            }
        });
        rv.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if((btSocket!=null)&&(btSocket.isConnected())) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        MyConexionBT.write("B");
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        MyConexionBT.write("S");
                    }
                }
                return false;
            }
        });

    }

    public void turnon (View vista){
        BluetoothAdapter Bluetooth = BluetoothAdapter.getDefaultAdapter();
        if(Bluetooth == null)
        {
            Toast.makeText(getApplicationContext(), "El dispositivo no cuenta con bluetooth", Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            if (Bluetooth.isEnabled())
            {
                Intent i = new Intent(Movimiento.this, Listdevices.class);
                startActivity(i);
            }
            else
            {
                Intent prender = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(prender);
            }
        }
    }



    @Override
    public void onResume()
    {

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        super.onResume();

        Intent intent = getIntent();
        address = intent.getStringExtra("device_address");
        if(address != null) {
            BluetoothDevice device = btAdapter.getRemoteDevice(address);

            try {
                btSocket = device.createInsecureRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
                btSocket.connect();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "Unable to connect to device", Toast.LENGTH_LONG).show();
                try {
                    btSocket.close();
                }
                catch (IOException c){}
            }

            MyConexionBT = new ConnectedThread(btSocket);
            MyConexionBT.start();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(btSocket !=null) {
            try {
                btSocket.close();
            } catch (IOException e2) {
            }
        }
    }

    private class ConnectedThread extends Thread
    {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        private ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }
}





