package vlillealert.jeremsamson.com.vlillealert.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vlillealert.jeremsamson.com.vlillealert.Bean.Station;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "stationsdb";
    private static final String TABLE_STATION = "station";

    public static final String KEY_STATION_ID = "key_station_id";
    public static final String KEY_STATION_ADRESS = "key_station_adress";
    public static final String KEY_STATION_BIKES = "key_station_bikes";
    public static final String KEY_STATION_ATTACHS = "key_station_attachs";
    public static final String KEY_STATION_LASTUPDT = "key_station_lastupd";
    public static final String KEY_STATION_STATUS = "key_station_status";
    public static final String KEY_STATION_LAT = "key_station_lat";
    public static final String KEY_STATION_LNG = "key_station_lng";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_STATION + "("
                + KEY_STATION_ID + " INTEGER PRIMARY KEY,"
                + KEY_STATION_ADRESS + " TEXT,"
                + KEY_STATION_BIKES + " INT,"
                + KEY_STATION_ATTACHS + " INT,"
                + KEY_STATION_LASTUPDT + " TEXT,"
                + KEY_STATION_STATUS + " INT,"
                + KEY_STATION_LAT + " INT,"
                + KEY_STATION_LNG + " INT"
                + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATION);
        onCreate(db);
    }

    public void insert(Station station) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_STATION_ID, station.getStationid());
        values.put(KEY_STATION_ADRESS, station.getAdress());
        values.put(KEY_STATION_BIKES, station.getBikes());
        values.put(KEY_STATION_ATTACHS, station.getAttachs());
        values.put(KEY_STATION_LASTUPDT, station.getLastupd());
        values.put(KEY_STATION_STATUS, station.getStatus());
        values.put(KEY_STATION_LAT, station.getLat());
        values.put(KEY_STATION_LNG, station.getLng());

        db.insert(TABLE_STATION, null, values);
        db.close(); // Closing database connection
    }

    // Init database with stations loaded from API
    public void init(List<Station> stations) {
        for (int i=0; i<stations.size(); i++) {
            insert(stations.get(i));
        }
    }

    public Station getStation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STATION, new String[]{KEY_STATION_ID,
                        KEY_STATION_ADRESS, KEY_STATION_BIKES, KEY_STATION_ATTACHS, KEY_STATION_LASTUPDT, KEY_STATION_STATUS, KEY_STATION_LAT, KEY_STATION_LNG }, KEY_STATION_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        //Station station = new Station(Integer.parseInt(cursor.getString(0)),
        //        cursor.getString(1), cursor.getString(2));

        return null;
    }

    public List<Station> getAllStations() {
        List<Station> stationList = new ArrayList<Station>();

        String selectQuery = "SELECT * FROM " + TABLE_STATION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Station station = new Station();
                station.setId(Integer.parseInt(cursor.getString(0)));
                stationList.add(station);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return stationList;
    }

    public int getStationsCount() {
        int stations = 0;

        String countQuery = "SELECT * FROM " + TABLE_STATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        stations = cursor.getCount();
        cursor.close();

        return stations;
    }

    public boolean hasBeenInitialised() {
        return getStationsCount() > 0;
    }
}