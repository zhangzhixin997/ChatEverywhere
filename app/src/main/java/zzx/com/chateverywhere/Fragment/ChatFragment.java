package zzx.com.chateverywhere.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.mapapi.radar.RadarSearchManager;

import java.util.Observable;

import zzx.com.chateverywhere.R;
import zzx.com.chateverywhere.WatchClass.Datachange;
import zzx.com.chateverywhere.WatchClass.Watch;

/**
 * Created by zhangzhixin on 2017/7/20.
 */

public class ChatFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.chatfragment,container,false);

        return view;
    }
}
