package com.ui.g5.voyafun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class lehoi extends Activity {

    TextView gioithieu,  trochoi, thamquan;
    ImageView trangchu,hambuger;
    ListView lvLehoi;
    ArrayList<Information>  arrLehoi;
    boolean signin=false;
    String user="",email="";
    customAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lehoi);
        Intent intent = getIntent();
        user=(String)  intent.getStringExtra("username");
        email=(String) intent.getStringExtra("email");
        signin=(Boolean) intent.getBooleanExtra("signin",false);
        if(user==null) signin=false;
        Anhxa(); hambuger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenu();
            }
        });
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
                intent.putExtra("username",user);
                intent.putExtra("email",email);
                intent.putExtra("signin",true);
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
                            Intent intent = new Intent(lehoi.this, SigninActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }

                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(lehoi.this,ThongtinActivity.class);
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
                            Intent intent = new Intent(lehoi.this, MainActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(lehoi.this,ThongtinActivity.class);
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
        trangchu    = (ImageView) findViewById(R.id.home);
        gioithieu   = (TextView)findViewById(R.id.txtGioithieu);
        trochoi     = (TextView)findViewById(R.id.txtTrochoi);
        thamquan    = (TextView)findViewById(R.id.txtThamquan);
        lvLehoi     = (ListView)findViewById(R.id.listLehoi);
        hambuger=(ImageView) findViewById(R.id.hambuger);

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

    public void Trochoi() {
        Intent game = new Intent(this, trochoi.class);
        game.putExtra("username",user);
        game.putExtra("email",email);
        game.putExtra("signin",true);
        startActivity(game);finish();
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
}
