package com.ui.g5.voyafun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class trochoi extends AppCompatActivity {

    TextView _gioithieu, _lehoi, _thamquan, _camgiacmanh, _khampha, _congtrinhmoi;
    ImageView _trangchu,hambuger;
    boolean signin=false;
    String user="",email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trochoi);
        Intent intent = getIntent();
        user=(String)  intent.getStringExtra("username");
        email=(String) intent.getStringExtra("email");
        signin=(Boolean) intent.getBooleanExtra("signin",false);
        if(user==null) signin=false;
        //anh x
        Anhxa();
        hambuger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenu();
            }
        });
        _trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trangchu();
            }
        });
        _gioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gioithieu();
            }
        });
        _lehoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lehoi();
            }
        });

        _thamquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thamquan();
            }
        });

        _camgiacmanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camgiacmanh();
            }
        });

        _khampha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Khamphaphieuluu();
            }
        });

        _congtrinhmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Congtrinhmoi();
            }
        });
    }
    private void showmenu()
    {
        if(signin==false) {
            PopupMenu popupMenu = new PopupMenu(this, hambuger);
            popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.itemsignin: {
                            Intent intent = new Intent(trochoi.this, SigninActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(trochoi.this,ThongtinActivity.class);
                            intent.putExtra("username",user);
                            intent.putExtra("email",email);
                            intent.putExtra("signin",signin);
                            startActivity(intent);
                            break;
                        }

                    }
                    return false;
                }
            });
            popupMenu.show();
        }
        else
        {
            PopupMenu popupMenu = new PopupMenu(this, hambuger);
            popupMenu.getMenuInflater().inflate(R.menu.menu1, popupMenu.getMenu());
            popupMenu.getMenu().getItem(0).setTitle("Username:"+user);
            popupMenu.getMenu().getItem(1).setTitle("Email:"+email);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.itemsignout: {
                            signin=true;
                            Intent intent = new Intent(trochoi.this, MainActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(trochoi.this,ThongtinActivity.class);
                            intent.putExtra("username",user);
                            intent.putExtra("email",email);
                            intent.putExtra("signin",signin);
                            startActivity(intent);
                            break;
                        }
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    }
    private void Anhxa() {
        _trangchu    = (ImageView) findViewById(R.id.home);
        _gioithieu   = (TextView)findViewById(R.id.txtGioithieu);
        _lehoi       = (TextView)findViewById(R.id.txtLehoi);
        _thamquan    = (TextView)findViewById(R.id.txtThamquan);
        _camgiacmanh = (TextView)findViewById(R.id.txtCamgiamanh);
        _khampha     = (TextView)findViewById(R.id.txtKhamphaphieuluu);
        _congtrinhmoi= (TextView)findViewById(R.id.txtCongtrinhmoi);
        hambuger=(ImageView) findViewById(R.id.hambuger);
    }
    public void Gioithieu() {
        Intent intent = new Intent(this, Gioithieu.class);
        intent.putExtra("username",user);
        intent.putExtra("email",email);
        intent.putExtra("signin",true);
        startActivity(intent);finish();
    }
    public void Trangchu() {
        Intent myHome = new Intent(this, MainActivity.class);
        myHome.putExtra("username",user);
        myHome.putExtra("email",email);
        myHome.putExtra("signin",true);
        startActivity(myHome);finish();
    }

    public void Lehoi() {
        Intent myFestival = new Intent(this, lehoi.class);
        myFestival.putExtra("username",user);
        myFestival.putExtra("email",email);
        myFestival.putExtra("signin",true);
        startActivity(myFestival);finish();
    }

    public void Thamquan() {
        Intent myVisit = new Intent(this, Thamquan.class);
        myVisit.putExtra("username",user);
        myVisit.putExtra("email",email);
        myVisit.putExtra("signin",true);
        startActivity(myVisit);finish();
    }

    public void Camgiacmanh() {
        Intent intent = new Intent(this, trochoicamgiacmanh.class);
        intent.putExtra("username",user);
        intent.putExtra("email",email);
        intent.putExtra("signin",true);
        startActivity(intent);
    }

    public void Khamphaphieuluu() {
        Intent intent = new Intent(this, trochoikhamphaphieuluu.class);
        intent.putExtra("username",user);
        intent.putExtra("email",email);
        intent.putExtra("signin",true);
        startActivity(intent);
    }

    public void Congtrinhmoi() {
        Intent intent = new Intent(this, trochoicongtrinhmoi.class);
        intent.putExtra("username",user);
        intent.putExtra("email",email);
        intent.putExtra("signin",true);
        startActivity(intent);
    }
}
