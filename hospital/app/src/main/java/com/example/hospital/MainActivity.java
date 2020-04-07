package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    public final static String basepath="http://192.168.43.41:8080/hospital/";
    private Button btn_login;
    //private Button btn_forpas;
    private Button btn_register;
    //private TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login);
        //获取控件
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        //t1 = (TextView)findViewById(R.id.login_textview);
        //btn_forpas = (Button) findViewById(R.id.btn_forget_pwd);
        btn_login = (Button) findViewById(R.id.login);
        //点击注册
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到register activity
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        //登陆
        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //获得账号密码
                final String name=et_username.getText().toString();
                final String password=et_password.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getinfo(name,password);//交互
                    }
                }).start();

            }
        });
        //test text点击
//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"点击",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    public void getinfo(String name,String password){
        //获取网络上servlet路径
        String path=basepath+"doLogin";
        new getTask().execute(name,password,path);
    }
    class getTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params){
            //依次获取用户名，密码和路径
            String name=params[0].toString();
            String password=params[1].toString();
            String path=params[2].toString();
            try{
                //获取网络上get方式提交的整个路径
                URL url=new URL(path+"?name="+name+"&password="+password);
                //打开网络连接
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                //设置提交方式
                conn.setRequestMethod("GET");
                //设置网络超时时间
                conn.setConnectTimeout(5000);
                if(conn.getResponseCode()==200){
                    //用io流与web后台进行数据交互
                    InputStream is=conn.getInputStream();
                    //字节流转字符流
                    BufferedReader br=new BufferedReader(new InputStreamReader(is));
                    //读出每一行的数据
                    String str=br.readLine();
                    System.out.println(str);
                    //返回读出的每一行的数据
                    return str;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected  void onPostExecute(Object o){
            super.onPostExecute(o);
            //获取android与web数据交互获得的值
            String s=(String) o;
            //toast弹出
            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            if(s.equals("success")){
                //说明login成功
                Intent i = new Intent(MainActivity.this,Index.class);
                startActivity(i);
            }
        }
    }
}