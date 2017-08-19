package zzx.com.chateverywhere.Bean;

/**
 * Created by zhangzhixin on 2017/7/27.
 */

public class DTBean {
    public DTBean(String friendNick, String bt, String desc, String dtimg) {
        this.friendNick = friendNick;
        Bt = bt;
        this.desc = desc;
        Dtimg = dtimg;
    }

    public String getFriendNick() {
        return friendNick;
    }

    public void setFriendNick(String friendNick) {
        this.friendNick = friendNick;
    }

    public String getBt() {
        return Bt;
    }

    public void setBt(String bt) {
        Bt = bt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDtimg() {
        return Dtimg;
    }

    public void setDtimg(String dtimg) {
        Dtimg = dtimg;
    }

    private String friendNick;
    private String Bt;
    private String desc;
    private String Dtimg;
}
