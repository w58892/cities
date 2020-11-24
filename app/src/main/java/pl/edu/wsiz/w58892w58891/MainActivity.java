package pl.edu.wsiz.w58892w58891;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        connectionCheck();

        Handler mHandler= new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                connectionCheck();

                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.post(runnable);

    }

    public void connectionCheck(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if ( connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected() )
        {
            getData();
        }

        else
        {
            Toast.makeText(MainActivity.this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://w58892.000webhostapp.com/api.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while (line != null) {
                        line = bufferedReader.readLine();
                        data = data + line;
                        Log.i("value", data);

                    }


                    JSONArray JA = new JSONArray(data);
                    for (int i = 0; i < JA.length(); i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        singleParsed = "City:" + JO.get("city") + "\n" +
                                "criminal:" + JO.get("criminal") + "\n" +
                                "economic:" + JO.get("economic") + "\n" +
                                "road:" + JO.get("road") + "\n" +
                                "beating:" + JO.get("beating") + "\n" +
                                "thefts:" + JO.get("thefts") + "\n" +
                                "criminal_detectability:" + JO.get("criminal_detectability") + "\n" +
                                "economic_detectability:" + JO.get("economic_detectability") + "\n" +
                                "road_detectability:" + JO.get("road_detectability") + "\n" +
                                "beating_detectability:" + JO.get("beating_detectability") + "\n" +
                                "thefts_detectability:" + JO.get("thefts_detectability") + "\n" +
                                "v:" + JO.get("v") + "\n" +
                                "vl:" + JO.get("vl") + "\n";

                        dataParsed = dataParsed + singleParsed + "\n";

                    }
                    openMap();

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    public void openMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
        System.exit(0);
    }
}