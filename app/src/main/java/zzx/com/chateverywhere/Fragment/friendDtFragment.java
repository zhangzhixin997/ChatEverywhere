package zzx.com.chateverywhere.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import zzx.com.chateverywhere.Adapter.FriendDTadpater;
import zzx.com.chateverywhere.Bean.DTBean;
import zzx.com.chateverywhere.R;

/**
 * Created by zhangzhixin on 2017/7/27.
 */
public class friendDtFragment extends Fragment {
    private ArrayList<DTBean> datas=new ArrayList<DTBean>();
    @ViewInject(R.id.lv)
    ListView lv;
    private FriendDTadpater adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frienddtbj,null);
        x.view().inject(this,view);
        setadapter();
        return view;
    }
    public void setadapter(){
        adapter=new FriendDTadpater(getActivity(),datas);
        lv.setAdapter(adapter);
        setdata();
    }
    public void setdata(){
        for(int i=0;i<6;i++){
            datas.add(new DTBean("张三"+i,"UC震惊部！","你怕不怕","啊啊啊"));
            adapter.notifyDataSetChanged();
        }
    }
}
