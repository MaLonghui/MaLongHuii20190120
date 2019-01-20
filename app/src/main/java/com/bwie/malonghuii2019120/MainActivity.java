package com.bwie.malonghuii2019120;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login_img)
    ImageView loginImg;
    @BindView(R.id.login_edit_name)
    EditText loginEditName;
    @BindView(R.id.login_edit_pwd)
    EditText loginEditPwd;
    @BindView(R.id.rem_pwd)
    CheckBox remPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    @BindView(R.id.btn_qq_login)
    Button btnQqLogin;
    //A.定义装平台的容器
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE};
    private UMShareAPI mUMShareAPI;
    private boolean isauth;
    private int flag = 0;
    private String pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //A.三方平台,添加到遍历的集合中
        initPlatforms();
        //A.获取UM的对象
        mUMShareAPI = UMShareAPI.get(MainActivity.this);
        //A.获取是否授权
        isauth = UMShareAPI.get(this).isAuthorize(this, platforms.get(0).mPlatform);

        SharedPreferences preferences = getSharedPreferences("text", MODE_PRIVATE);
        if (preferences!=null){
            String name = preferences.getString("name", "");
            pwd = preferences.getString("pwd", "");
            flag = preferences.getInt("flag", 0);
            loginEditName.setText(name);

        }

        if (flag==1){
            remPwd.setChecked(true);
            loginEditPwd.setText(pwd);
        }



    }

    private void initPlatforms() {

        //A.集合清空
        platforms.clear();
        //A.通过for循环,把数组数据添加到集合中
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    //A.
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调，可以用来处理等待框，或相关的文字提示
        }

        @Override//授权成功时回调
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //获取用户授权后的信息
            Set<String> strings = data.keySet();
            data.get("profile_image_url");
            String temp="";
            for(String key: strings ){
                temp =temp +key +" :" +data.get(key) +"\n";
            }
            Intent intent = new Intent(MainActivity.this,ShowActivity.class);
            startActivity(intent);
            finish();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @OnClick({R.id.btn_login, R.id.btn_regist, R.id.btn_qq_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = loginEditName.getText().toString();
                String pwd = loginEditPwd.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){

                    Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences preferences = getSharedPreferences("text", MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putString("name",name);
                    edit.putString("pwd",pwd);

                    if (remPwd.isChecked()){
                        flag=1;
                        edit.putInt("flag",flag);
                        edit.putString("pwd",pwd);

                    }else{
                        flag=0;
                        edit.putInt("flag",flag);
                    }
                    edit.commit();
                    Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                    startActivity(intent);
                    finish();

                }

                break;
            case R.id.btn_regist:
                Intent intent = new Intent(MainActivity.this,RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_qq_login:

                if (isauth){
                    Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    mUMShareAPI.deleteOauth(MainActivity.this, platforms.get(0).mPlatform,authListener);
                }else{
                    mUMShareAPI.doOauthVerify(MainActivity.this, platforms.get(0).mPlatform,authListener);
                }
                mUMShareAPI.getPlatformInfo(MainActivity.this, platforms.get(0).mPlatform,authListener);



                break;
        }
    }
}
