package fr.florianBurel.followmii;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by fl0 on 05/07/13.
 */
public class LocationListFragment extends Fragment{


    private ListView listView;
    private final ArrayList<City> cities = new ArrayList<City>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.location_list, null);

        listView = (ListView) v.findViewById(R.id.listView);

        refresh();

        return v;
    }

    public void refresh(){

        if(listView == null)
           return;

        listView.setAdapter(new ArrayAdapter<City>(getActivity(),
                android.R.layout.simple_list_item_1,cities));
    }

    public void addCity(City city)
    {
        this.cities.add(city);
        refresh();
    }
}
