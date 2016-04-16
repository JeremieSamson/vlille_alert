package vlillealert.jeremsamson.com.vlillealert.API;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

import vlillealert.jeremsamson.com.vlillealert.Bean.Station;


public interface VLilleAlertService {

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/stations")
    public void getStations(Callback<List<Station>> response);
}