package com.ui.g5.voyafun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class trochoicongtrinhmoi extends Activity{
    TextView gioithieu,  lehoi, thamquan;
    ImageView trangchu,hambuger;
    ListView lvCongtrinhmoi;
    ArrayList<Information> arrTrochoi;
    customAdapter myAdapter;

    boolean signin=false;
    String user="",email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trochoicamgiacmanh);
        Intent intent = getIntent();
        user=(String)  intent.getStringExtra("username");
        email=(String) intent.getStringExtra("email");
        signin=(Boolean) intent.getBooleanExtra("signin",false);
        if(user==null) signin=false;
        Anhxa();
        hambuger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenu();
            }
        });
        Khoitaodanhsachtrochoi();
        myAdapter = new customAdapter(this,R.layout.custom_row,arrTrochoi);
        lvCongtrinhmoi.setAdapter(myAdapter);

        lvCongtrinhmoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int Id;
                Information diadiem = arrTrochoi.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(trochoicongtrinhmoi.this,hienthithongtin.class);
                intent.putExtra("username",user);
                intent.putExtra("email",email);
                intent.putExtra("signin",true);
                Id = diadiem.getId();
                bundle.putInt("ID",Id);
                bundle.putString("Key", "CTM");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trangchu();
            }
        });

        gioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gioithieu();
            }
        });

        lehoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lehoi();
            }
        });

        thamquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thamquan();
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
                            Intent intent = new Intent(trochoicongtrinhmoi.this, SigninActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(trochoicongtrinhmoi.this,ThongtinActivity.class);
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
                            Intent intent = new Intent(trochoicongtrinhmoi.this, MainActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(trochoicongtrinhmoi.this,ThongtinActivity.class);
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
        trangchu = (ImageView) findViewById(R.id.home);
        gioithieu = (TextView) findViewById(R.id.txtGioithieu);
        lehoi       = (TextView)findViewById(R.id.txtLehoi);
        thamquan    = (TextView)findViewById(R.id.txtThamquan);
        lvCongtrinhmoi  = (ListView)findViewById(R.id.listLehoi);
        hambuger=(ImageView) findViewById(R.id.hambuger);
    }

    private void Khoitaodanhsachtrochoi() {
        arrTrochoi    = new ArrayList<>();
        arrTrochoi.add(new Information(1,R.drawable.btd,"Biển Tiên Đồng - Ngọc nữ"));

    }

    public void Trangchu() {
        Intent myHome = new Intent(this, MainActivity.class);
        startActivity(myHome);
    }

    public void Gioithieu() {
        Intent intent = new Intent(this, Gioithieu.class);
        startActivity(intent);
    }

    public void Lehoi() {
        Intent myFestival = new Intent(this, lehoi.class);
        startActivity(myFestival);
    }

    public void Thamquan() {
        Intent myVisit = new Intent(this, Thamquan.class);
        startActivity(myVisit);
    }
}

