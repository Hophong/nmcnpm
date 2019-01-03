package com.ui.g5.voyafun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Thamquan extends AppCompatActivity {

    TextView gioithieu, lehoi, trochoi;
    ImageView trangchu,hambuger;
    ListView lvThamquan;
    ArrayList<Information> arrThamquan;
    customAdapter myAdapter;
    WebView webView;
    boolean signin=false;
    String user="",email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thamquan);
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
        Khoitaodulieu();

        myAdapter = new customAdapter(this, R.layout.custom_row, arrThamquan);
        lvThamquan.setAdapter(myAdapter);

        lvThamquan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int Id;
                Information diadiem = arrThamquan.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(Thamquan.this,hienthithongtin.class);
                intent.putExtra("username",user);
                intent.putExtra("email",email);
                intent.putExtra("signin",true);
                Id = diadiem.getId();
                bundle.putInt("ID",Id);
                bundle.putString("Key", "TQ");
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

        trochoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trochoi();
            }
        });

        lehoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lehoi();
            }
        });

        gioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gioithieu();
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
                            Intent intent = new Intent(Thamquan.this, SigninActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(Thamquan.this,ThongtinActivity.class);
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
                            Intent intent = new Intent(Thamquan.this, MainActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(Thamquan.this,ThongtinActivity.class);
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
        lehoi = (TextView) findViewById(R.id.txtLehoi);
        trochoi = (TextView) findViewById(R.id.txtTrochoi);
        lvThamquan = (ListView)findViewById(R.id.listThamquan);
        hambuger=(ImageView) findViewById(R.id.hambuger);
    }

    private  void Khoitaodulieu() {
        arrThamquan = new ArrayList<>();
        arrThamquan.add(new Information(1, R.drawable.tg, "Tượng đài Thánh Gióng"));
        arrThamquan.add(new Information(2, R.drawable.hbt, "Tượng Đài Hai Bà Trưng"));
        arrThamquan.add(new Information(3, R.drawable.thd, "Tượng Đài Trần Hưng Đạo"));
        arrThamquan.add(new Information(4, R.drawable.pbqa, "Đền Thờ Phật Bà Quan Âm"));
        arrThamquan.add(new Information(5, R.drawable.cun, "Cây Ước nguyện"));
        arrThamquan.add(new Information(6, R.drawable.tttn, "Tượng Phật Thiên Thủ Thiên Nhãn"));
        arrThamquan.add(new Information(7, R.drawable.lhtb, "Long Hoa Thiên Bảo"));
    }

    public void Trangchu() {
        Intent myHome = new Intent(this, MainActivity.class);
        myHome.putExtra("username",user);
        myHome.putExtra("email",email);
        myHome.putExtra("signin",true);
        startActivity(myHome);finish();
    }

    public void Gioithieu() {
        Intent intent = new Intent(this, Gioithieu.class);
        intent.putExtra("username",user);
        intent.putExtra("email",email);
        intent.putExtra("signin",true);
        startActivity(intent);finish();
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


}
