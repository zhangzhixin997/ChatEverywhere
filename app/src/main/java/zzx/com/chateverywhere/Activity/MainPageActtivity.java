package zzx.com.chateverywhere.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import zzx.com.chateverywhere.Adapter.MainPageAdapter;
import zzx.com.chateverywhere.Fragment.ChatFragment;
import zzx.com.chateverywhere.Fragment.FriendFragment;
import zzx.com.chateverywhere.Fragment.IndexFragment;
import zzx.com.chateverywhere.Fragment.MineFragment;
import zzx.com.chateverywhere.R;

/**
 * Created by zhangzhixin on 2017/7/20.
 */
@ContentView(R.layout.mainpage)
public class MainPageActtivity extends BaseActivity {
    private ArrayList<Fragment>fragments=new ArrayList<Fragment>();
    @ViewInject(R.id.pager)
    ViewPager pager;
    @ViewInject(R.id.bottombar)
    View bottom;
    @ViewInject(R.id.index)
    LinearLayout index;
    @ViewInject(R.id.chat)
    LinearLayout chat;
    @ViewInject(R.id.friend)
    LinearLayout friend;
    @ViewInject(R.id.mine)
    LinearLayout mine;
    private MainPageAdapter adapter;
    private IndexFragment indexFragment;
    private ChatFragment chatFragment;
    private FriendFragment friendFragment;
    private MineFragment mineFragment;
    private Handler handler=new Handler();
    @Event({R.id.index,R.id.chat,R.id.mine,R.id.friend})
    private void onClick(View v){
           switch (v.getId()){
               case R.id.index:
                   pager.setCurrentItem(0);
                   break;
               case R.id.chat:
                   pager.setCurrentItem(1);
                   break;
               case R.id.friend:
                   pager.setCurrentItem(2);
                   break;
               case R.id.mine:
                   pager.setCurrentItem(3);
                   break;
           }
    }
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initflash();
        setadpter();

    }
    public void initflash(){
          indexFragment= new IndexFragment();
          chatFragment=new ChatFragment();
          friendFragment=new FriendFragment();
          mineFragment=new MineFragment();
          fragments.add(indexFragment);
          fragments.add(chatFragment);
          fragments.add(friendFragment);
          fragments.add(mineFragment);
    }
    public void setadpter()
    {
        adapter=new MainPageAdapter(getSupportFragmentManager(),this,fragments);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
               // pager.setCurrentItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        pager.setCurrentItem(0);
    }

}
