package com.ui.g5.voyafun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class lehoi extends Activity {

    TextView gioithieu,  trochoi, thamquan;
    ImageView trangchu;
    ListView lvLehoi;
    ArrayList<Information>  arrLehoi;
    customAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoi);

        Anhxa();
        Khoitaodanhsachlehoi();
        myAdapter = new customAdapter(this,R.layout.custom_row,arrLehoi);
        lvLehoi.setAdapter(myAdapter);

        lvLehoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int Id;
                Information diadiem = arrLehoi.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(lehoi.this,hienthithongtin.class);
                Id = diadiem.getId();
                bundle.putInt("ID",Id);
                bundle.putString("Key", "LH");
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

        trochoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trochoi();
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
        trangchu    = (ImageView) findViewById(R.id.home);
        gioithieu   = (TextView)findViewById(R.id.txtGioithieu);
        trochoi     = (TextView)findViewById(R.id.txtTrochoi);
        thamquan    = (TextView)findViewById(R.id.txtThamquan);
        lvLehoi     = (ListView)findViewById(R.id.listLehoi);

    }

    private void Khoitaodanhsachlehoi() {
        arrLehoi    = new ArrayList<>();
        arrLehoi.add(new Information(1,R.drawable.tetmt,"Tết Mậu Tuất 2018: Vui Tết Suối Tiên – Phúc Tài Như Ý"));
        arrLehoi.add(new Information(2,R.drawable.qtpn,"08/03/2018 – Suối Tiên Ưu Đãi Đặc Biệt Nhân Ngày Quốc Tế Phụ Nữ"));
        arrLehoi.add(new Information(3,R.drawable.gthv,"Giỗ Tổ Hùng Vương Tại Suối Tiên: NƠI HỘI TỤ NGÀN NĂM VĂN HIẾN"));
        arrLehoi.add(new Information(4,R.drawable.xuandd,"Xuân Đinh Dậu: Vui Tết Suối Tiên - Chào Xuân Đón Phúc"));
        arrLehoi.add(new Information(5,R.drawable.ltn,"Du Xuân Suối Tiên - Vui lễ tình nhân"));
        arrLehoi.add(new Information(6,R.drawable.dcv,"Lễ kỷ niệm 1050 năm thành lập nhà nước Đại Cồ Việt"));
        arrLehoi.add(new Information(7,R.drawable.psa,"Phóng sự ảnh: Khám phá thiên đường mùa Xuân tại Suối Tiên"));
    }

    public void Gioithieu() {
        Intent intent = new Intent(this, Gioithieu.class);
        startActivity(intent);
    }

    public void Trangchu() {
        Intent myHome = new Intent(this, MainActivity.class);
        startActivity(myHome);
    }

    public void Trochoi() {
        Intent game = new Intent(this, trochoi.class);
        startActivity(game);
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
