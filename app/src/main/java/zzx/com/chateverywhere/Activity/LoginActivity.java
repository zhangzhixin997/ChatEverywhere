package zzx.com.chateverywhere.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import zzx.com.chateverywhere.R;

/**
 * Created by zhangzhixin on 2017/7/20.
 */
//@ContentView(R.layout.loginlayout)
@ContentView(R.layout.loginlayout)
public class LoginActivity extends BaseActivity{
@ViewInject(R.id.login)
Button login;
@ViewInject(R.id.regist)
Button regist;
@ViewInject(R.id.username)
TextView username;
@ViewInject(R.id.password)
TextView password;
@Event({R.id.login,R.id.regist})
private void onClick(View v){
    switch (v.getId()){
        case R.id.login:
            Intent intent=new Intent(LoginActivity.this,MainPageActtivity.class);
            startActivity(intent);
            this.finish();
            break;
        case R.id.regist:
            Intent intent1=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent1);
            break;
    }
}
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
