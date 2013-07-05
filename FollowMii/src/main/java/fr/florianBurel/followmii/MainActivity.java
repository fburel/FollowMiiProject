package fr.florianBurel.followmii;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements LocationListener {

    final LocationListFragment listFragment = new LocationListFragment();
    private LocationManager locationManager;

    private Location lastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        // Affichage du 1er fragment (List)
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, listFragment).commit();


        listFragment.addCity(new City("Rouen"));
        /*
        listFragment.addCity(new City("Paris"));
        listFragment.addCity(new City("Lille"));
        listFragment.addCity(new City("Toulouse"));
        listFragment.addCity(new City("Budapest"));
        listFragment.addCity(new City("Barcelone"));
        listFragment.addCity(new City("Marseille"));
        listFragment.addCity(new City("Montpellier"));
        listFragment.addCity(new City("Oslow"));
        listFragment.addCity(new City("Palo alto"));
        listFragment.addCity(new City("Mountain View"));
        listFragment.addCity(new City("Cuppertino"));

*/

        Button ok = (Button) findViewById(R.id.addButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCurrentLocation();
            }


        });

        Button share = (Button) findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSharePanel();
            }
        });
    }



    private void openSharePanel()
    {

    }

    private void addCurrentLocation()
    {
        if(lastKnownLocation != null)
        {
            final City city = new City(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());

            listFragment.addCity(city);

            new AsyncTask<Void, Void, Void> (){

                @Override
                protected Void doInBackground(Void... voids) {
                    // Recuperer le nom du bled


                    Geocoder gc = new Geocoder(MainActivity.this);

                    Log.d("florianBurel", "gc - isPresent : " + (gc.isPresent() ? "YES" : "NO"));

                    try {
                        List<Address> addresses =  gc.getFromLocation(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 1);

                        Log.d("florianBurel", addresses.size() + " addresses found");

                        //city.setName(addresses.get(0).getLocality());

                    } catch (IOException e) {
                        city.setName("Unknown Location");
                    }




                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    listFragment.refresh();
                }

            }.execute();
        }
        else
        {

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("florianBurel", "location changed");
        lastKnownLocation = location;


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
