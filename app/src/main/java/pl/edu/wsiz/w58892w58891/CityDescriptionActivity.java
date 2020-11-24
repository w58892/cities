package pl.edu.wsiz.w58892w58891;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CityDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_description);


        TextView cityName = (TextView) findViewById(R.id.cityName);
        TextView criminal = (TextView) findViewById(R.id.criminal);
        TextView economic = (TextView) findViewById(R.id.economic);
        TextView road = (TextView) findViewById(R.id.road);
        TextView beating = (TextView) findViewById(R.id.beating);
        TextView thefts = (TextView) findViewById(R.id.thefts);
        TextView criminalDetectability = (TextView) findViewById(R.id.criminalDetectability);
        TextView economicDetectability = (TextView) findViewById(R.id.economicDetectability);
        TextView roadDetectability = (TextView) findViewById(R.id.roadDetectability);
        TextView beatingDetectability = (TextView) findViewById(R.id.beatingDetectability);
        TextView theftsDetectability = (TextView) findViewById(R.id.theftsDetectability);

//        cityName.setText(getIntent().getStringExtra("cityName"));
        criminal.setText(getIntent().getStringExtra("criminal"));
        economic.setText(getIntent().getStringExtra("economic"));
        road.setText(getIntent().getStringExtra("road"));
        beating.setText(getIntent().getStringExtra("beating"));
        thefts.setText(getIntent().getStringExtra("thefts"));
        criminalDetectability.setText(getIntent().getStringExtra("criminalDetectability")+"%");
        economicDetectability.setText(getIntent().getStringExtra("economicDetectability")+"%");
        roadDetectability.setText(getIntent().getStringExtra("roadDetectability")+"%");
        beatingDetectability.setText(getIntent().getStringExtra("beatingDetectability")+"%");
        theftsDetectability.setText(getIntent().getStringExtra("theftsDetectability")+"%");

        getSupportActionBar().setTitle(getIntent().getStringExtra("cityName"));

    }
}