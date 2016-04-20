package vlillealert.jeremsamson.com.vlillealert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import java.util.List;

import vlillealert.jeremsamson.com.vlillealert.API.VLilleAlertService;
import vlillealert.jeremsamson.com.vlillealert.Bean.Station;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    public static final String BASE_URL = "http://ns378858.ip-5-196-69.eu/app.php";
    //public static final String BASE_URL = "http://ns378858.ip-5-196-69.eu/app.php/stations?_format=json";

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_STATION_ID = "key_station_id";
    public static final String KEY_STATION_ADRESS = "key_station_adress";
    public static final String KEY_STATION_BIKES = "key_station_bikes";
    public static final String KEY_STATION_ATTACHS = "key_station_attachs";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //List of type Stations this list will store type Station which is our data model
    private List<Station> Stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the listview
        mRecyclerView = (RecyclerView) findViewById(R.id.listViewStations);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Calling the method that will fetch data
        getStations();
    }

    private void getStations(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();

        //Creating an object of our api interface
        VLilleAlertService api = adapter.create(VLilleAlertService.class);

        //Defining the method
        api.getStations(new Callback<List<Station>>() {
            @Override
            public void success(List<Station> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                Stations = list;

                //Calling a method to show the list
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", "" + error.getBody().toString());
            }
        });
    }

    //Our method to show list
    private void showList(){
        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdaptater(Stations, getResources());
        mRecyclerView.setAdapter(mAdapter);
    }

    //This method will execute on listitem click
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, ShowStationDetail.class);

        //Getting the requested Station from the list
        Station Station = Stations.get(position);

        //Adding Station details to intent
        intent.putExtra(KEY_STATION_ID,Station.getStationid());
        intent.putExtra(KEY_STATION_ADRESS,Station.getAdress());
        intent.putExtra(KEY_STATION_BIKES,Station.getBikes());
        intent.putExtra(KEY_STATION_ATTACHS,Station.getAttachs());

        //Starting another activity to show Station details
        startActivity(intent);
    }
}
