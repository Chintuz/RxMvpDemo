
package mapp.demo.com.mapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("deviceudid")
    @Expose
    private String deviceudid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("lastloggedintime")
    @Expose
    private String lastloggedintime;
    @SerializedName("usertype")
    @Expose
    private String usertype;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceudid() {
        return deviceudid;
    }

    public void setDeviceudid(String deviceudid) {
        this.deviceudid = deviceudid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastloggedintime() {
        return lastloggedintime;
    }

    public void setLastloggedintime(String lastloggedintime) {
        this.lastloggedintime = lastloggedintime;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}
