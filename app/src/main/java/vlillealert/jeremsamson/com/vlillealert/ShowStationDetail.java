package vlillealert.jeremsamson.com.vlillealert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowStationDetail extends AppCompatActivity {

    //Defining views
    private TextView textViewStationId;
    private TextView textViewStationAdress;
    private TextView textViewStationBikes;
    private TextView textViewStationAttachs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_station);

        //Initializing Views
        textViewStationId = (TextView) findViewById(R.id.stationid);
        textViewStationAdress = (TextView) findViewById(R.id.adress);
        textViewStationBikes = (TextView) findViewById(R.id.bikes);
        textViewStationAttachs = (TextView) findViewById(R.id.attachs);

        //Getting intent
        Intent intent = getIntent();

        //Displaying values by fetching from intent
        textViewStationId.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_STATION_ID, 0)));
        textViewStationAdress.setText(intent.getStringExtra(MainActivity.KEY_STATION_ADRESS));
        textViewStationBikes.setText(intent.getStringExtra(MainActivity.KEY_STATION_BIKES));
        textViewStationAttachs.setText(String.valueOf(intent.getIntExtra(MainActivity.KEY_STATION_ATTACHS,0)));
    }
}
