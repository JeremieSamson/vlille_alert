package vlillealert.jeremsamson.com.vlillealert.Bean;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Station {

    private Integer id;
    private String adress;
    private Boolean status;
    private Integer bikes;
    private Integer attachs;
    private Boolean paiement;
    private String lastupd;
    private Integer stationid;
    private Double lat;
    private Double lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Station() {

    }
    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     *
     * @param adress
     * The adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     *
     * @return
     * The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The bikes
     */
    public Integer getBikes() {
        return bikes;
    }

    /**
     *
     * @param bikes
     * The bikes
     */
    public void setBikes(Integer bikes) {
        this.bikes = bikes;
    }

    /**
     *
     * @return
     * The attachs
     */
    public Integer getAttachs() {
        return attachs;
    }

    /**
     *
     * @param attachs
     * The attachs
     */
    public void setAttachs(Integer attachs) {
        this.attachs = attachs;
    }

    /**
     *
     * @return
     * The paiement
     */
    public Boolean getPaiement() {
        return paiement;
    }

    /**
     *
     * @param paiement
     * The paiement
     */
    public void setPaiement(Boolean paiement) {
        this.paiement = paiement;
    }

    /**
     *
     * @return
     * The lastupd
     */
    public String getLastupd() {
        Date date = null;
        String formattedDate = "";

        try {
            Log.d("info", lastupd);
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz").parse(lastupd);
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(date);
        } catch (ParseException e) {
            Log.d("failure", e.toString());
        }

        return lastupd;
    }

    /**
     *
     * @param lastupd
     * The lastupd
     */
    public void setLastupd(String lastupd) {
        this.lastupd = lastupd;
    }

    /**
     *
     * @return
     * The stationid
     */
    public Integer getStationid() {
        return stationid;
    }

    /**
     *
     * @param stationid
     * The stationid
     */
    public void setStationid(Integer stationid) {
        this.stationid = stationid;
    }

    /**
     *
     * @return
     * The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     *
     * @param lng
     * The lng
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}