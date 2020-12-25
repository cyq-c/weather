package com.youziweather.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 用户注册
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences register_sp;
    private String mAccount;
    private String mPwd;
    private String mPwdCheck;
    private Button mSureButton;
    private Button mCancelButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StatusBarUtil.transparencyBar(this);
        //找到按钮
        mSureButton = (Button) findViewById(R.id.register_btn_sure);
        mCancelButton = (Button) findViewById(R.id.register_btn_cancel);
        mSureButton.setOnClickListener(this);      //注册界面两个按钮的监听事件
        mCancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn_sure:                       //确认按钮的监听事件
                onRegister();
                break;
            case R.id.register_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                Intent intent_Register_to_Login = new Intent(com.youziweather.android.RegisterActivity.this, LoginActivity.class);
                startActivity(intent_Register_to_Login);
                finish();
                break;
        }
    }

    /**
     * 注册
     */
    public void onRegister() {
        EditText edt_username = (EditText) findViewById(R.id.resetpwd_edit_name);
        EditText edt_password = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        EditText edt_password2 = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        mAccount = edt_username.getText().toString();
        mPwd = edt_password.getText().toString();
        mPwdCheck = edt_password2.getText().toString();
        if(mAccount.trim().equals("") || mPwd.trim().equals("") || mPwdCheck.trim().equals("")){
            Toast.makeText(com.youziweather.android.RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if (!(mPwd.trim().equals(mPwdCheck.trim()))) {
            Toast.makeText(com.youziweather.android.RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        //进入注册的Dialog
        Dialog dialog=new AlertDialog.Builder(com.youziweather.android.RegisterActivity.this)
                .setTitle("注册")
                .setMessage("你确定要注册吗？")
                .setPositiveButton("确定", new  DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //保存输入的信息     edit.commit();提交
                        register_sp=getSharedPreferences("userInfo",MODE_PRIVATE);
                        SharedPreferences.Editor edit=register_sp.edit();
                        edit.putString("username", mAccount);
                        edit.putString("password", mPwd);
                        edit.commit();
                        //提示成功注册
                        Toast.makeText(com.youziweather.android.RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(com.youziweather.android.RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                }).create();//创建一个dialog
        dialog.show();//显示对话框，否者不成功
    }
}
