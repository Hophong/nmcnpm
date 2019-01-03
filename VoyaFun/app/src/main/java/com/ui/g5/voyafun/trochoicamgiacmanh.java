package com.ui.g5.voyafun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class trochoicamgiacmanh extends Activity{
    TextView gioithieu,  lehoi, thamquan;
    ImageView trangchu;
    ListView lvCamgiacmanh;
    ArrayList<Information> arrTrochoi;
    customAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trochoicamgiacmanh);

        Anhxa();
        Khoitaodanhsachtrochoi();
        myAdapter = new customAdapter(this,R.layout.custom_row,arrTrochoi);
        lvCamgiacmanh.setAdapter(myAdapter);

        lvCamgiacmanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int Id;
                Information diadiem = arrTrochoi.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(trochoicamgiacmanh.this,hienthithongtin.class);
                Id = diadiem.getId();
                bundle.putInt("ID",Id);
                bundle.putString("Key", "CGM");
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

    private void Anhxa() {
        trangchu = (ImageView) findViewById(R.id.home);
        gioithieu = (TextView) findViewById(R.id.txtGioithieu);
        lehoi       = (TextView)findViewById(R.id.txtLehoi);
        thamquan    = (TextView)findViewById(R.id.txtThamquan);
        lvCamgiacmanh  = (ListView)findViewById(R.id.listLehoi);

    }

    private void Khoitaodanhsachtrochoi() {
        arrTrochoi    = new ArrayList<>();
        arrTrochoi.add(new Information(1,R.drawable.tlst,"Tàu Lượn Siêu Tốc Mini"));
        arrTrochoi.add(new Information(2,R.drawable.svdh,"Xe Vượt Địa Hình"));
        arrTrochoi.add(new Information(3,R.drawable.tlht,"Tứ Linh hội tụ"));
        arrTrochoi.add(new Information(4,R.drawable.ddmh,"Đu dây mạo hiểm"));
        arrTrochoi.add(new Information(5,R.drawable.db,"Đĩa bay hành tinh lạ"));
        arrTrochoi.add(new Information(6,R.drawable.vxvt,"Vòng xoay vũ trụ"));
        arrTrochoi.add(new Information(7,R.drawable.tlst2,"Tàu lượn siêu tốc"));
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

