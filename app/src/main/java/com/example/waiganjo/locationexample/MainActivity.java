package com.example.waiganjo.locationexample;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView tv1,tv2,tv3;
   LocationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tv1= (TextView) findViewById(R.id.textView);
        tv2= (TextView) findViewById(R.id.textView2);
        tv3= (TextView) findViewById(R.id.textView3);
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void start(View v){
        try
        {
            LocationListener listener=new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lat= location.getLatitude();
                    double lon = location.getLongitude();
                    double speed =location.getSpeed();
                    tv1.setText("Longi"+lon);
                    tv2.setText("Lati"+lat);
                    tv3.setText("speed"+speed);
                    //do something with this values
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.d("DATA", "STATUS CHANGED " + provider);
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Log.d("DATA","PROVIDER ENABLED "+provider);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Log.d("DATA","PROVIDER DISABLED"+provider);
                }
            };
            String provider=LocationManager.GPS_PROVIDER;
            manager.requestLocationUpdates(provider,3000,1,listener);
        }catch (SecurityException e)
        {
            Log.e("DATA","Could Not Fetch The Location");
        }

    }
    public void start_service(View v){
 //manager.removeUpdates(listener);
        Intent service=new Intent(this,LocationService.class);
        startService(service);
    }
    public void check(View v){
     String data=Utility.readFromFile(this,"data.txt");
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}
