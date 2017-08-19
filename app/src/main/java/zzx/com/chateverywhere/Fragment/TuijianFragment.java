package zzx.com.chateverywhere.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zzx.com.chateverywhere.R;

/**
 * Created by zhangzhixin on 2017/7/27.
 */

public class TuijianFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tuijianfragment,null);
    }
}
