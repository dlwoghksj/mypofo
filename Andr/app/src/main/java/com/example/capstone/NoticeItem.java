package com.example.capstone;

public class NoticeItem {
    private int iv_notice_list_img;
    private String tv_notice_list_title;
    private String tv_notice_list_date;
    private String notice_info;


    public int getIv_notice_list_img() {
        return iv_notice_list_img;
    }

    public String getTv_notice_list_title() {
        return tv_notice_list_title;
    }

    public String getTv_notice_list_date() {
        return tv_notice_list_date;
    }

    public String getNotice_info() {
        return notice_info;
    }


    public NoticeItem(int iv_notice_list_img, String tv_notice_list_title, String tv_notice_list_date, String notice_info) {
        this.iv_notice_list_img = iv_notice_list_img;
        this.tv_notice_list_title = tv_notice_list_title;
        this.tv_notice_list_date = tv_notice_list_date;
        this.notice_info = notice_info;

    }
}