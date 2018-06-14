package com.example.sumitbobade.bluetoothdemo;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {


    public static final String TAG="MainActivity";
    private static final int REQUEST_BLUETOOTH =1 ;
    BluetoothAdapter bluetoothAdapter;
    Button bswitch;
    ListView listView;
    Set<BluetoothDevice> pairedDevices;
    IntentFilter filter;

    ArrayAdapter<String>discoverDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bswitch=findViewById(R.id.bswitch);
        listView=findViewById(R.id.listview);

        discoverDevice=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(discoverDevice);

        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        bswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bluetoothAdapter==null)
                {
                    Log.i(TAG,"Device doesnt support bluetooth");
                    return;
                }
                if(!bluetoothAdapter.isEnabled())
                {
                    Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent,REQUEST_BLUETOOTH);
                    Log.i(TAG,"Enable blutooth");
                }

                if(bluetoothAdapter.isEnabled())
                {
                  bluetoothAdapter.disable();
                    Log.i(TAG,"Disabled bluetooth");
                }


            }
        });

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        public void onReceive(Context context, Intent intent)
        {

            //String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction()))
            {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
               // Log.i(TAG,"device name"+deviceName);
                String deviceHardwareAddress = device.getAddress(); // MAC address
               // Log.i(TAG,"hardware address"+deviceHardwareAddress);
                discoverDevice.add("Device name : "+deviceName+"\n"+deviceHardwareAddress);
                discoverDevice.notifyDataSetChanged();
            }

        }
    };




    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }



    @Override
    protected void onStart()
    {
        super.onStart();


    }

    public void getPairedDevice(View view)
    {
        pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                Log.i(TAG,"device name"+deviceName);
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.i(TAG,"hardware address"+deviceHardwareAddress);


            }
        }


    }

    public void getDevices(View view)
    {  boolean isDiscover=bluetoothAdapter.startDiscovery();
        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

           Log.i(TAG,"Dicsovry start "+isDiscover);
    }

    public void stopDescovery(View view)
    {
        boolean isDiscover=bluetoothAdapter.cancelDiscovery();
        Log.i(TAG,"Didcpvery stop "+isDiscover);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {

        if(requestCode==REQUEST_BLUETOOTH && resultCode==RESULT_OK)
        {

        }
    }
}
