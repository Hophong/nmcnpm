package com.ui.g5.voyafun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class trochoi extends AppCompatActivity {

    TextView _gioithieu, _lehoi, _thamquan, _camgiacmanh, _khampha, _congtrinhmoi;
    ImageView _trangchu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trochoi);

        //anh x
        Anhxa();

        _trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trangchu();
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

    private void Anhxa() {
        _trangchu    = (ImageView) findViewById(R.id.home);
        _gioithieu   = (TextView)findViewById(R.id.txtGioithieu);
        _lehoi       = (TextView)findViewById(R.id.txtLehoi);
        _thamquan    = (TextView)findViewById(R.id.txtThamquan);
        _camgiacmanh = (TextView)findViewById(R.id.txtCamgiamanh);
        _khampha     = (TextView)findViewById(R.id.txtKhamphaphieuluu);
        _congtrinhmoi= (TextView)findViewById(R.id.txtCongtrinhmoi);
    }

    public void Trangchu() {
        Intent myHome = new Intent(this, MainActivity.class);
        startActivity(myHome);
    }

    public void Lehoi() {
        Intent myFestival = new Intent(this, lehoi.class);
        startActivity(myFestival);
    }

    public void Thamquan() {
        Intent myVisit = new Intent(this, Thamquan.class);
        startActivity(myVisit);
    }

    public void Camgiacmanh() {
        Intent intent = new Intent(this, trochoicamgiacmanh.class);
        startActivity(intent);
    }

    public void Khamphaphieuluu() {
        Intent intent = new Intent(this, trochoikhamphaphieuluu.class);
        startActivity(intent);
    }

    public void Congtrinhmoi() {
        Intent intent = new Intent(this, trochoicongtrinhmoi.class);
        startActivity(intent);
    }
}
