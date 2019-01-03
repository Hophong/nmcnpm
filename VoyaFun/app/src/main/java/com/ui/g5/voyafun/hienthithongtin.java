package com.ui.g5.voyafun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class hienthithongtin extends AppCompatActivity {

    TextView gioithieu, lehoi, trochoi, thamquan, tinnhan;
    ImageView trangchu, dienthoai, yeuthich,hambuger;
    WebView webView;
    ProgressBar bar;
    boolean signin=false;
    String user="",email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hienthithongtin);

        webView = (WebView) findViewById(R.id.webViewHienThi);
        bar=(ProgressBar) findViewById(R.id.progressBar2);

        webView.setWebViewClient(new hienthithongtin.myWebclient());
        webView.getSettings().setJavaScriptEnabled(true);

        Bundle bundle = getIntent().getExtras();
        int Id = bundle.getInt("ID");
        String key = bundle.getString("Key");

        // hiện thông tin
        HienThi(key, Id);
        Intent intent = getIntent();
        user=(String)  intent.getStringExtra("username");
        email=(String) intent.getStringExtra("email");
        signin=(Boolean) intent.getBooleanExtra("signin",false);
        if(user==null) signin=false;
        Anhxa();
        yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        hambuger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenu();
            }
        });
        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trangchu();
            }
        });

        lehoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lehoi();
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

        gioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gioithieu();
            }
        });

        dienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hienthithongtin.this,"Gọi điện", Toast.LENGTH_SHORT).show();
            }
        });

        dienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }

            private void ShowDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(hienthithongtin.this);
                builder.setTitle("Suoitien.com");
                builder.setMessage("Bạn có muốn liên hệ tổng đài viên không?");
                builder.setCancelable(false);
                builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String posted_by = "0355729988";

                        String uri = "tel:" + posted_by.trim();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));

                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        tinnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tinnhan();
            }

            public void Tinnhan() {
                Intent myBooking = new Intent(hienthithongtin.this, tinnhan.class);
                startActivity(myBooking);
            }
        });




    }

    // hiển thị thông tin
    public void HienThi(String key, int id) {
        if(key.equals("TQ") == true) {
            if(id == 1) {
                webView.loadUrl("http://www.suoitien.com/tham-quan/van-hoa-lich-su/tuong-dai-thanh-giong-1");
            }
            if(id ==2) {
                webView.loadUrl("http://www.suoitien.com/tham-quan/van-hoa-lich-su/tuong-dai-hai-ba-trung-1");
            }

            Toast.makeText(hienthithongtin.this,id +", " + key,Toast.LENGTH_SHORT).show();

        }

        else if(key.equals("LH") == true) {
            if(id == 1) {
                webView.loadUrl("http://www.suoitien.com/le-hoi/le-hoi-mua-xuan/tet-mau-tuat-2018-vui-tet-suoi-tien--phuc-tai-nhu-y-1");
            }
            if(id ==2) {
                webView.loadUrl("http://www.suoitien.com/le-hoi/le-hoi-mua-xuan/suoi-tien-uu-dai-dac-biet-nhan-ngay-quoc-te-phu-nu-08032018");
            }
            if(id == 3) {
                webView.loadUrl("http://www.suoitien.com/le-hoi/le-hoi-mua-xuan/gio-to-hung-vuong-tai-suoi-tien-noi-hoi-tu-ngan-nam-van-hien");
            }
            if(id ==4) {
                webView.loadUrl("http://www.suoitien.com/le-hoi/le-hoi-mua-xuan/xuan-dinh-dau-vui-tet-suoi-tien-chao-xuan-don-phuc");
            }

            Toast.makeText(hienthithongtin.this,id +", " + key,Toast.LENGTH_SHORT).show();

        }

        else if(key.equals("CGM") == true) {
            if(id == 1) {
                webView.loadUrl("http://www.suoitien.com/tro-choi/tro-choi-cam-giac-manh/tau-luon-sieu-toc-mini-1");
            }
            if(id ==2) {
                webView.loadUrl("http://www.suoitien.com/tro-choi/tro-choi-cam-giac-manh/xe-vuot-dia-hinh-1");
            }
            Toast.makeText(hienthithongtin.this,id +", " + key,Toast.LENGTH_SHORT).show();

        }

        else if(key.equals("KPPL") == true) {
            if(id == 1) {
                webView.loadUrl("http://www.suoitien.com/tro-choi/kham-pha-phieu-luu/dai-cung-phung-hoang-tien-1");
            }
            if(id ==2) {
                webView.loadUrl("http://www.suoitien.com/tro-choi/kham-pha-phieu-luu/dai-cung-lac-canh-tien-ngu-1");
            }

            Toast.makeText(hienthithongtin.this,id +", " + key,Toast.LENGTH_SHORT).show();

        }

        else if(key.equals("CTM") == true) {
            if(id == 1) {
                webView.loadUrl("http://www.suoitien.com/tro-choi/cac-cong-trinh-moi/bien-tien-dong-ngoc-nu");
            }
            Toast.makeText(hienthithongtin.this,id +", " + key,Toast.LENGTH_SHORT).show();


        }
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
                            Intent intent = new Intent(hienthithongtin.this, SigninActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }

                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(hienthithongtin.this,ThongtinActivity.class);
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
                            Intent intent = new Intent(hienthithongtin.this, MainActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(hienthithongtin.this,ThongtinActivity.class);
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
        gioithieu   = (TextView)findViewById(R.id.txtGioithieu);
        trangchu    = (ImageView) findViewById(R.id.home);
        trochoi     = (TextView)findViewById(R.id.txtTrochoi);
        lehoi     = (TextView)findViewById(R.id.txtLehoi);
        thamquan    = (TextView)findViewById(R.id.txtThamquan);
        tinnhan     =(TextView)findViewById(R.id.txtTinnhan);
        dienthoai   =findViewById(R.id.imgDienthoai);
        yeuthich    = (ImageView) findViewById(R.id.imgYeuthich);
        hambuger=(ImageView) findViewById(R.id.hambuger);
    }

    public void Gioithieu() {
        Intent intent = new Intent(this, Gioithieu.class);
        intent.putExtra("username",user);
        intent.putExtra("email",email);
        intent.putExtra("signin",true);
        startActivity(intent);
    }

    public void Trangchu() {
        Intent myHome = new Intent(this, MainActivity.class);
        myHome.putExtra("username",user);
        myHome.putExtra("email",email);
        myHome.putExtra("signin",true);
        startActivity(myHome);
    }

    public void Trochoi() {
        Intent game = new Intent(this, trochoi.class);
        game.putExtra("username",user);
        game.putExtra("email",email);
        game.putExtra("signin",true);
        startActivity(game);
    }

    public void Lehoi() {
        Intent myFestival = new Intent(this, lehoi.class);
        myFestival.putExtra("username",user);
        myFestival.putExtra("email",email);
        myFestival.putExtra("signin",true);
        startActivity(myFestival);
    }

    public void Thamquan() {
        Intent myVisit = new Intent(this, Thamquan.class);
        myVisit.putExtra("username",user);
        myVisit.putExtra("email",email);
        myVisit.putExtra("signin",true);
        startActivity(myVisit);
    }

    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
