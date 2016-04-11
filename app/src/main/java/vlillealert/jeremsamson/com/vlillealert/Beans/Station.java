package vlillealert.jeremsamson.com.vlillealert.Beans;

/**
 * Created by jerem on 11/04/16.
 */
public class Station {
    private int id;
    private String adress;
    private int status;
    private int bikes;
    private int attachs;
    private int paiement;
    private String lastupd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBikes() {
        return bikes;
    }

    public void setBikes(int bikes) {
        this.bikes = bikes;
    }

    public int getAttachs() {
        return attachs;
    }

    public void setAttachs(int attachs) {
        this.attachs = attachs;
    }

    public int getPaiement() {
        return paiement;
    }

    public void setPaiement(int paiement) {
        this.paiement = paiement;
    }

    public String getLastupd() {
        return lastupd;
    }

    public void setLastupd(String lastupd) {
        this.lastupd = lastupd;
    }

    public String toString() {
        return adress;
    }
}
