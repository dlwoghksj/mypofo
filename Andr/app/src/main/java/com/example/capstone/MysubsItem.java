package com.example.capstone;

public class MysubsItem {
    private int iv_subs_icon; //OTT Image
    private String tv_subs_name; //OTT Name
    private String tv_subs_date; //OTT StartDate - EndDate
    private boolean waiting;

    private String groupCode;
    private String ottCode;
    private String subscription ;
    private String userPaymentDate ;
    private String ottID;
    private String ottPW;
    private String createDate;

    private String waitingOttCode;
    private String waitingUserPaymentDate;
    private String new_first;
    private String  waitingCount;

    public int getIv_subs_icon() {
        return iv_subs_icon;
    }
    public String getTv_subs_name() {
        return tv_subs_name;
    }
    public String getTv_subs_date() {
        return tv_subs_date;
    }
    public boolean getWaiting() { return waiting; }
    public String getGroupCode(){ return groupCode; }
    public String getOttCode(){ return ottCode; }
    public String getSubscription(){ return subscription; }
    public String getUserPaymentDate() { return userPaymentDate; }
    public String getOttID(){ return ottID; }
    public String getOttPW(){ return ottPW; }
    public String getCreateDate(){ return createDate;}

    public String getWaitingOttCode(){ return waitingOttCode; }
    public String getWaitingUserPaymentDate(){ return waitingUserPaymentDate; }
    public String getWaitingCount(){return waitingCount;}
    public String getNew_first(){return new_first;}

    public MysubsItem(int iv_subs_icon, String tv_subs_name, String tv_subs_date, boolean waiting
            , String groupCode, String ottCode, String subscription, String userPaymentDate,
                      String ottID, String ottPW, String createDate,
                      String waitingOttCode, String waitingUserPaymentDate,
                      String new_first, String waitingCount) {
        this.iv_subs_icon = iv_subs_icon;
        this.tv_subs_name = tv_subs_name;
        this.tv_subs_date = tv_subs_date;
        this.waiting = waiting;

        this.groupCode = groupCode;
        this.ottCode = ottCode;
        this.subscription = subscription;
        this.userPaymentDate = userPaymentDate;
        this.ottID = ottID;
        this.ottPW = ottPW;
        this.createDate = createDate;

        this.waitingOttCode = waitingOttCode;
        this.waitingUserPaymentDate = waitingUserPaymentDate;
        this.new_first = new_first;
        this.waitingCount = waitingCount;
    }


}
