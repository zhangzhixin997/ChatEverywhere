package zzx.com.chateverywhere.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zzx.com.chateverywhere.Bean.DTBean;
import zzx.com.chateverywhere.R;

/**
 * Created by zhangzhixin on 2017/7/27.
 */

public class FriendDTadpater extends BaseAdapter {
    private CardView card;

    public FriendDTadpater(Context c, ArrayList<DTBean> data) {
        this.c = c;
        this.datas = data;
    }

    private Context c;
    private ArrayList<DTBean> datas;
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(c).inflate(R.layout.frienddt,null);
            viewHolder=new ViewHolder();
            card=(CardView)convertView.findViewById(R.id.card);
            card.setCardElevation(18);
            card.setRadius(18);
            card.setContentPadding(8,8,8,8);
            viewHolder.Bt=(TextView)convertView.findViewById(R.id.Bt);
            viewHolder.friendimg=(ImageView) convertView.findViewById(R.id.Dtimg);
            viewHolder.Desc=(TextView)convertView.findViewById(R.id.desc);
            viewHolder.friendNickname=(TextView)convertView.findViewById(R.id.friendnick);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.friendNickname.setText(datas.get(position).getFriendNick().toString());
        viewHolder.Bt.setText(datas.get(position).getBt().toString());
        viewHolder.Desc.setText(datas.get(position).getDesc().toString());
        return convertView;
    }
    public class ViewHolder{
        public TextView friendNickname;
        public TextView Bt;
        public TextView Desc;
        public ImageView friendimg;
    }
}
