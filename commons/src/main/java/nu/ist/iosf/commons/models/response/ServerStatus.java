package nu.ist.iosf.commons.models.response;

import java.util.Date;

public class ServerStatus {

    private String startupTime;
    private String serverTime;

    public ServerStatus() {
    }

    public ServerStatus(String startupTime) {
        this.startupTime = startupTime;
        this.serverTime = new Date().toString();
    }

    public ServerStatus(String startupTime, String serverTime) {
        this.startupTime = startupTime;
        this.serverTime = serverTime;
    }

    public String getStartupTime() {
        return startupTime;
    }

    public void setStartupTime(String startupTime) {
        this.startupTime = startupTime;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }
}
