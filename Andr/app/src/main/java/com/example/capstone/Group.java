package com.example.capstone;

public class Group {
    public String groupCode;
    public String waitingOttCode;
    public String waitingCount;

    public Group(){}

    public Group(String groupCode, String waitingOttCode, String waitingCount){
        this.groupCode = groupCode;
        this.waitingOttCode = waitingOttCode;
        this.waitingCount = waitingCount;
    }
}
