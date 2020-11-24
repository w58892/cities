package pl.edu.wsiz.w58892w58891;

import androidx.appcompat.widget.SearchView;
import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import static java.lang.Math.round;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    int width,height;
    private GoogleMap mMap;
    private RelativeLayout movableArea;
    ListView listView,listViewMap;

    String fragment = "map";
    SearchView searchView;
    ListViewAdapter adapter;
    MapListViewAdapter adapterMap;
    String[][] data = new String[13][1000];
    ArrayList<City> arrayList = new ArrayList<City>(), arrayListMap = new ArrayList<City>();

    String safety;
    float color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String languageToLoad = "pl_PL";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;



        try{
            String JSON = getIntent().getStringExtra("data");

            JSONArray JA = new JSONArray(JSON);
            for(int i=0;i<JA.length();i++){
                JSONObject JO=(JSONObject) JA.get(i);
                data[0][i] = JO.getString("city");
                data[1][i] = JO.getString("criminal");
                data[2][i] = JO.getString("economic");
                data[3][i] = JO.getString("road");
                data[4][i] = JO.getString("beating");
                data[5][i] = JO.getString("thefts");
                data[6][i] = JO.getString("criminal_detectability");
                data[7][i] = JO.getString("economic_detectability");
                data[8][i] = JO.getString("road_detectability");
                data[9][i] = JO.getString("beating_detectability");
                data[10][i] = JO.getString("thefts_detectability");
                data[11][i] = JO.getString("v");
                data[12][i] = JO.getString("vl");

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        listView = findViewById(R.id.listView);
        listViewMap = findViewById(R.id.lista);


        for (int i =0; i<data[0].length; i++){
            if(data[0][i] == null)
                break;

            City city = new City(data[0][i], data[1][i],data[2][i],data[3][i],data[4][i],data[5][i],data[6][i],data[7][i],data[8][i],data[9][i],data[10][i],data[11][i],data[12][i]);
            //bind all strings in an array
            arrayList.add(city);
            arrayListMap.add(city);
        }

        //pass results to listViewAdapter class
        adapter = new ListViewAdapter(this, arrayList);
        adapterMap = new MapListViewAdapter(this, arrayListMap);

        //bind the adapter to the listview
        listView.setAdapter(adapter);
        listViewMap.setAdapter(adapterMap);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MapsActivity.this,CityDescriptionActivity.class);
                intent.putExtra("cityName", adapter.modellist.get(position).getCityName());
                intent.putExtra("criminal", adapter.modellist.get(position).getCriminal());
                intent.putExtra("economic", adapter.modellist.get(position).getEconomic());
                intent.putExtra("road", adapter.modellist.get(position).getRoad());
                intent.putExtra("beating", adapter.modellist.get(position).getBeating());
                intent.putExtra("thefts", adapter.modellist.get(position).getThefts());
                intent.putExtra("criminalDetectability", adapter.modellist.get(position).getCriminalDetectability());
                intent.putExtra("economicDetectability", adapter.modellist.get(position).getEconomicDetectability());
                intent.putExtra("roadDetectability", adapter.modellist.get(position).getRoadDetectability());
                intent.putExtra("beatingDetectability", adapter.modellist.get(position).getBeatingDetectability());
                intent.putExtra("theftsDetectability", adapter.modellist.get(position).getTheftsDetectability());
                startActivity(intent);
            }
        });

        adapterMap.filter("");
        movableArea = findViewById(R.id.movableArea);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);


        MenuItem listButton = menu.findItem(R.id.listButton);
    listButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem  item) {

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) movableArea.getLayoutParams();

                if(fragment == "map") {
                    params.setMargins(width, 0, 0, 0);
                    fragment = "list";

                }else {
                    params.setMargins(0, 0, 0, 0);
                    fragment = "map";

                }

                movableArea.setLayoutParams(params);
                movableArea.requestLayout();
                return false;
            }
        });


        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                    if (TextUtils.isEmpty(s)) {
                        adapter.filter("");
                        adapterMap.filter("");

                        listView.clearTextFilter();
                        listViewMap.clearTextFilter();

                    } else {
                        adapter.filter(s);
                        adapterMap.filter(s);
                    }
                return true;
            }
        });

        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        for(int i=0;i<data[0].length;i++) {
            if(data[0][i]==null){
                break;
            }else {
                double crimes = Double.valueOf(data[1][i]) + Double.valueOf(data[3][i]) + Double.valueOf(data[4][i]) + Double.valueOf(data[5][i]);
                if(crimes < 30){
                    safety = "bezpiecznie";
                    color = 120;
                }else if(crimes < 40){
                    safety = "umiarkowanie";
                    color = 60;
                }else{
                    safety = "niebezpiecznie";
                    color = 30;
                }

                LatLng city = new LatLng(Float.parseFloat(data[11][i]), Float.parseFloat(data[12][i]));
                mMap.addMarker(new MarkerOptions()
                        .position(city)
                        .title(data[0][i])
                        .snippet(safety+"  |  "+round(crimes)+" przestępstw / 1000 mieszkańców")
                        .icon(BitmapDescriptorFactory.defaultMarker(color)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(city));

            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.0695221, 19.252482), 5.7f));
        }

        listViewMap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double v =  Double.valueOf(adapterMap.modellist.get(position).getV());
                double vl =  Double.valueOf(adapterMap.modellist.get(position).getVl());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(v, vl), 12f));
                searchView.setIconified(true);
                searchView.clearFocus();
            }
        });
    }

}