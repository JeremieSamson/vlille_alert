package vlillealert.jeremsamson.com.vlillealert;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vlillealert.jeremsamson.com.vlillealert.Bean.Station;

public class RecyclerViewAdaptater  extends RecyclerView.Adapter<RecyclerViewAdaptater.StationViewHolder> {

    private List<Station> stations;
    private Resources resources;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdaptater(List<Station> stations, android.content.res.Resources resources) {
        this.stations = stations;
        this.resources = resources;
    }

    // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(StationViewHolder holder, int position) {
        Station station = stations.get(position);

        holder.stationAdress.setText(station.getAdress());

        holder.stationBikes.setText(resources.getString(R.string.bikes) + ' ' + station.getBikes());
        holder.stationAttachs.setText(resources.getString(R.string.attachs) + ' ' + station.getAttachs());
        holder.stationPaiement.setText(resources.getString(R.string.paiment) + ' ' + station.getPaiement());


        holder.stationLastupd.setText(resources.getString(R.string.lastupd) + ' ' + station.getLastupd());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return stations.size();
    }

    public StationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_station, viewGroup, false);

        return new StationViewHolder(itemView);
    }

    public static class StationViewHolder extends RecyclerView.ViewHolder{

        //Defining views
        private TextView stationAdress;
        private TextView stationBikes;
        private TextView stationAttachs;
        private TextView stationPaiement;
        private TextView stationLastupd;


        public StationViewHolder(View v) {
            super(v);

            //Initializing Views
            stationAdress = (TextView) v.findViewById(R.id.adress);
            stationBikes = (TextView) v.findViewById(R.id.bikes);
            stationAttachs = (TextView) v.findViewById(R.id.attachs);
            stationPaiement = (TextView) v.findViewById(R.id.paiement);
            stationLastupd = (TextView) v.findViewById(R.id.lastupd);
        }
    }
}