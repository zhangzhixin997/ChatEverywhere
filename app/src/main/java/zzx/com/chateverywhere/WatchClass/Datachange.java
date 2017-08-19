package zzx.com.chateverywhere.WatchClass;

import java.util.Observable;

/**
 * Created by zhangzhixin on 2017/7/31.
 */

public class Datachange extends Observable {
    private int price;
    public void setpchange(int price){
        this.price=price;
        setChanged();
        notifyObservers();
    }
    public int getprice(){
        return price;
    }
}
