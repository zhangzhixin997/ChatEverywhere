package zzx.com.chateverywhere;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zhangzhixin on 2017/7/18.
 */

public class Shanxing extends View {
    private RectF rectF;
    private Paint paint;
    private float height;
    private float width;
    private ArrayList<Sxdata>sxdatas;

    public Shanxing(Context context) {
        super(context);
        initPaint();
    }
    public void setdata(ArrayList<Sxdata> sxdatas){
        this.sxdatas=sxdatas;
        initdata();
        invalidate();
    }
    public Shanxing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Shanxing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public Shanxing(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }
    public void initPaint(){
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        rectF=new RectF();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
         height=h;
         width=w;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (sxdatas == null || sxdatas.size() == 0) return;
        canvas.translate(width/ 2, height / 2);             //将画布坐标原点移到中心位置
        float currentStartAngle = 0;                //起始角度
                   //设置将要用来画扇形的矩形的轮廓
        for (int i = 0; i < sxdatas.size(); i++) {
            Sxdata viewData = sxdatas.get(i);
            sxdatas.get(i).r = (float) (Math.min(width, height) / 2);     //饼状图半径(取宽高里最小的值)
            rectF.set(- (sxdatas.get(i).r)+10, -(sxdatas.get(i).r)+10,sxdatas.get(i).r-10,sxdatas.get(i).r-10);
            paint.setColor(viewData.color);
           // Log.e("The color","is "+viewData.color);
            //绘制扇形(通过绘制圆弧)
            canvas.drawArc(rectF, currentStartAngle, viewData.angle, true, paint);
            //绘制扇形上文字

            float textAngle = currentStartAngle + viewData.angle / 2;    //计算文字位置角度
            paint.setColor(Color.BLACK);
            float x = (float) (( sxdatas.get(i).r-10)/ 2 * Math.cos(textAngle * Math.PI / 180));    //计算文字位置坐标
            float y = (float) (( sxdatas.get(i).r-10)/ 2 * Math.sin(textAngle * Math.PI / 180));
            paint.setColor(Color.YELLOW);        //文字颜色
            canvas.drawText(viewData.name, x, y, paint);    //绘制文字
            sxdatas.get(i).qspercentage=currentStartAngle;
            currentStartAngle += viewData.angle;
          //  Log.e("the angle","is"+viewData.name+":"+ viewData.angle);//改变起始角度
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()== MotionEvent.ACTION_DOWN){
                double ss1;
                float x=event.getX();
                float y=event.getY();
                float nheight=y-height/2;
                float nwidth=x-width/2;
                if(nheight<0&&nwidth<0){
                    double ss=Math.atan(nheight/nwidth);
                    ss1=(ss*(180/Math.PI))+180;
                  //  Log.e("ss1",""+ss1);
                }
                else if(nheight>0&&nwidth<0){
                    double ss=Math.atan(nheight/nwidth);
                    ss1=ss*(180/Math.PI)+180;

                   // Log.e("ss2",""+ss1);
                }
                else if(nheight<0&&nwidth>0){
                    double ss=Math.atan(nheight/nwidth);
                    ss1=(90+ss*(180/Math.PI))+270;

                   // Log.e("ss3",""+ss1);
                }
                else{
                    double ss=Math.atan(nheight/nwidth);
                    ss1=ss*(180/Math.PI);
                   // Log.e("ss4","ss4"+ss1);
                }
                for(int i=0;i<sxdatas.size();i++){
                    Sxdata sxdata=sxdatas.get(i);
                    if(ss1>=sxdata.qspercentage&&ss1<=(sxdata.qspercentage+sxdata.angle)){
                        sxdatas.get(i).r=sxdatas.get(i).r+10;
                        if(sxdatas.get(i).qspercentage==0){
                            sxdatas.get(i).qspercentage=sxdatas.get(i).qspercentage+5;
                            sxdatas.get(i).angle=sxdatas.get(i).angle+5;
                            invalidate();
                        }
                        else{
                            sxdatas.get(i).qspercentage=sxdatas.get(i).qspercentage-5;
                            sxdatas.get(i).angle=sxdatas.get(i).angle+5;
                            invalidate();
                        }


                    }
                }


        }
        return super.onTouchEvent(event);
    }

    public void initdata(){
        if(sxdatas==null||sxdatas.size()==0){
            return ;
        }
        float sum = 0;
        for(int i=0;i<sxdatas.size();i++){
            sum=sum+sxdatas.get(i).value;
        }
        for(int i=0;i<sxdatas.size();i++){
            float percentage = sxdatas.get(i).value/sum;
            float angle = percentage * 360;
            sxdatas.get(i).percentage = percentage;
            sxdatas.get(i).angle = angle;
        }
    }
}
