package po;

import com.google.gson.annotations.SerializedName;

public class Request {

    private int request_id;

    @SerializedName("user_id")
    private  int user_id;

    @SerializedName("lvl")
    private int lvl;

    @SerializedName("txt")
    private String txt;

    @SerializedName("mylatitude")
    private String latitude;

    @SerializedName("mylongitude")
    private String longitude;

    private int status;

    @SerializedName("time")
    private long time;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getLatitude() { return latitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getLongitude() { return longitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
