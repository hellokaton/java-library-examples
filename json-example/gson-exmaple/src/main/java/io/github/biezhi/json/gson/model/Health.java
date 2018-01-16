package io.github.biezhi.json.gson.model;

import com.google.gson.annotations.SerializedName;
import java.lang.management.ManagementFactory;

public class Health {

    public enum Status {
        @SerializedName("1") UP,
        @SerializedName("0") DOWN
    }

    private String hostname;
    private String ip;
    private long startTime;
    private long upTime;
    private Status status;

    public Health(String hostname, String ip, Status status) {
        this.hostname = hostname;
        this.ip = ip;
        this.startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        this.upTime = ManagementFactory.getRuntimeMXBean().getUptime();
        this.status = status;
    }

    @Override
    public String toString() {
        return "Health{" +
                "hostname='" + hostname + '\'' +
                ", ip='" + ip + '\'' +
                ", startTime=" + startTime +
                ", upTime=" + upTime +
                ", status=" + status +
                '}';
    }
}