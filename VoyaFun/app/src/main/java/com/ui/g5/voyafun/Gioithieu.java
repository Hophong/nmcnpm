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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Gioithieu extends AppCompatActivity {

    TextView lehoi, trochoi, thamquan, tinnhan;
    ImageView trangchu, dienthoai,hambuger;
    Button datve;
    WebView webView;
    ProgressBar bar;
    boolean signin=false;
    String user="",email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        webView.setWebViewClient(new Gioithieu.myWebclient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.suoitien.com/gioi-thieu/tong-quan");

        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trangchu();
            }
        });

        datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatveOnline();
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

        thamquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thamquan();
            }
        });

        dienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }

            private void ShowDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Gioithieu.this);
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
                Intent myBooking = new Intent(Gioithieu.this, tinnhan.class);
                startActivity(myBooking);
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
                            Intent intent = new Intent(Gioithieu.this, SigninActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(Gioithieu.this,ThongtinActivity.class);
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
                            Intent intent = new Intent(Gioithieu.this, MainActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(Gioithieu.this,ThongtinActivity.class);
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
        trangchu    = (ImageView)findViewById(R.id.home);
        lehoi       = (TextView)findViewById(R.id.txtLehoi);
        trochoi     = (TextView)findViewById(R.id.txtTrochoi);
        thamquan    = (TextView)findViewById(R.id.txtThamquan);
        datve       = (Button)findViewById(R.id.btnDatve);
        webView     = (WebView) findViewById(R.id.webViewHienThi);
        bar         =(ProgressBar) findViewById(R.id.progressBar2);
        tinnhan     =(TextView)findViewById(R.id.txtTinnhan);
        dienthoai   =(ImageView)findViewById(R.id.imgDienthoai);
        hambuger=(ImageView) findViewById(R.id.hambuger);
    }

    public void DatveOnline() {
        Intent myBooking = new Intent(this, Datve.class);
        startActivity(myBooking);
    }

    public void Trangchu() {
        Intent home = new Intent(this, MainActivity.class);

        home.putExtra("username",user);
        home.putExtra("email",email);
        home.putExtra("signin",true);
        startActivity(home);
        finish();
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
