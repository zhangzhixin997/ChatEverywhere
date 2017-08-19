package zzx.com.chateverywhere.Fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import zzx.com.chateverywhere.R;

/**
 * Created by zhangzhixin on 2017/7/20.
 */

public class FriendFragment extends Fragment {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private BaiduMap mBaiduMap;
    public boolean isFirst=true;
    private RadarSearchManager Manager;
    private LatLng latLng;

    @Event({R.id.loadleida,R.id.location,R.id.openleida})
    private void onClick(View v){
         switch (v.getId()){
             case R.id.loadleida:
                 break;
             case R.id.location:
                 mLocationClient.start();
                 break;
             case R.id.openleida:
                 openleida();
                 break;
         }
    }
    private MapView mMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        View view=inflater.inflate(R.layout.friendfragment,container,false);
        x.view().inject(this,view);
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        initlocation();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mLocationClient.stop();
    }
    public void initlocation(){
        Manager = RadarSearchManager.getInstance();
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        mLocationClient.setLocOption(option);
        mBaiduMap=mMapView.getMap();
    }
    public class MyLocationListener implements BDLocationListener{


        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            double latitude=bdLocation.getLatitude();
            double longitude=bdLocation.getLongitude();
            Toast.makeText(getActivity(),"时间:"+bdLocation.getTime()+"经度:"+longitude+"纬度:"+latitude,Toast.LENGTH_SHORT).show();
            mBaiduMap.setMyLocationEnabled(true);
// 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(latitude)
                    .longitude(longitude).build();
// 设置定位数据
            mBaiduMap.setMyLocationData(locData);
// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true, null);
            mBaiduMap.setMyLocationConfiguration(config);
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(18).build()));
            if(isFirst) {
                latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(mapStatusUpdate);
                isFirst=false;
            }
// 当不需要定位图层时关闭定位图层
           // mBaiduMap.setMyLocationEnabled(false);
            loadleida(latLng);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
    public void openleida(){
        RadarNearbySearchOption option = new RadarNearbySearchOption().centerPt(latLng).pageNum(0).radius(2000);
//发起查询请求
        Manager.nearbyInfoRequest(option);
    }
    public void loadleida(LatLng latLng){
        Manager.addNearbyInfoListener(new RadarSearchListener() {
            @Override
            public void onGetNearbyInfoList(RadarNearbyResult radarNearbyResult, RadarSearchError radarSearchError) {
                if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
                    List<RadarNearbyInfo> infos = radarNearbyResult.infoList;
                    for(int i=infos.size()-1;i>=0;i--){
                       Log.e("","result is"+infos.get(i).comments);
                    }
//                    Toast.makeText(getActivity(), "查询周边成功", Toast.LENGTH_LONG)
//                            .show();
//                    //获取成功，处理数据
                } else {
                    //获取失败
                    Toast.makeText(getActivity(), "查询周边失败"+RadarSearchError.values().toString(), Toast.LENGTH_LONG)
                            .show();
                }
            }
            @Override
            public void onGetUploadState(RadarSearchError radarSearchError) {
                if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
                    //上传成功
                    Toast.makeText(getActivity(), "单次上传位置成功", Toast.LENGTH_LONG)
                            .show();
                } else {
                    //上传失败
                    Toast.makeText(getActivity(), "单次上传位置失败", Toast.LENGTH_LONG)
                            .show();
                }

            }
            @Override
            public void onGetClearInfoState(RadarSearchError radarSearchError) {

            }
        });
//周边雷达设置用户身份标识，id为空默认是设备标识
        Manager.setUserID("1333");
//上传位置
        RadarUploadInfo info = new RadarUploadInfo();
        info.comments ="千丝";
        info.pt = latLng;
        Manager.uploadInfoRequest(info);
//监听上传结果
   

    }
}
