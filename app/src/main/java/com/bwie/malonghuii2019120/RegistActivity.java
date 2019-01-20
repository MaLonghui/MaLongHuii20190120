package com.bwie.malonghuii2019120;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends AppCompatActivity {

    @BindView(R.id.login_img)
    ImageView loginImg;
    @BindView(R.id.reg_edit_name)
    EditText regEditName;
    @BindView(R.id.reg_edit_pwd)
    EditText regEditPwd;
    @BindView(R.id.agin_pwd)
    EditText aginPwd;
    @BindView(R.id.btn_qq_login)
    Button btnQqLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_qq_login)
    public void onViewClicked() {
        String name = regEditName.getText().toString();
        String pwd = regEditPwd.getText().toString();
        String aginpwd = aginPwd.getText().toString();
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(aginpwd)){
            Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
        }else if (pwd.equals(aginpwd)){
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(RegistActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
