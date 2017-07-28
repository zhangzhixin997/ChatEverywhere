package zzx.com.chateverywhere.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import zzx.com.chateverywhere.R;

/**
 * Created by zhangzhixin on 2017/7/20.
 */
public class IndexFragment extends Fragment {
    @ViewInject(R.id.friendDt)
    Button friendDt;
    @ViewInject(R.id.tuijian)
    Button tuijian;
    @Event({R.id.friendDt,R.id.tuijian})
    private void onClick(View v){
          switch (v.getId()){
              case R.id.friendDt:
                  friendDtFragment friendFragment=new friendDtFragment();
                  FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                  transaction.replace(R.id.body,friendFragment);
                  transaction.commit();
                  break;
              case R.id.tuijian:
                  TuijianFragment tuijian=new TuijianFragment();
                  FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                  transaction1.replace(R.id.body,tuijian);
                  transaction1.commit();
                  break;
          }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.indexfragment,container,false);
        x.view().inject(this,view);
        setdefault();
        return view;
    }
    public void setdefault(){
        friendDtFragment friendFragment=new friendDtFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.body,friendFragment);
        transaction.commit();
    }
}
