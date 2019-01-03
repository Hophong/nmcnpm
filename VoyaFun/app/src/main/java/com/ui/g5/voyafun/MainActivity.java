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

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class MainActivity extends AppCompatActivity {

    TextView gioithieu, lehoi, trochoi, thamquan, tinnhan;
    ImageView dienthoai, hambuger;
    Button datve;
    WebView webView;
    ProgressBar bar;
    boolean signin = false;
    String user = "", email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        user = (String) intent.getStringExtra("username");
        email = (String) intent.getStringExtra("email");

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn) {
            signin = (Boolean) intent.getBooleanExtra("signin", true);
        } else {
            signin = (Boolean) intent.getBooleanExtra("signin", false);
        }

        Anhxa();
        hambuger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenu();
            }
        });
        gioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gioithieu();
            }
        });

        datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signin==true)
                DatveOnline();
                else Toast.makeText(MainActivity.this, "Vui long dang nhap de thuc hien", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                if(signin==true)
                Tinnhan();
                else Toast.makeText(MainActivity.this, "Vui long dang nhap de thuc hien", Toast.LENGTH_SHORT).show();
            }

            public void Tinnhan() {
                Intent myBooking = new Intent(MainActivity.this, tinnhan.class);
                startActivity(myBooking);
            }
        });


        webView.setWebViewClient(new MainActivity.myWebclient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.suoitien.com/");

    }

    private void showmenu() {
        if (signin == false) {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, hambuger);
            popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.itemsignin: {
                            signin=true;
                            Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                            //intent.putExtra("signin",signin);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case R.id.iteminfor:
                        {
                            Intent intent=new Intent(MainActivity.this,ThongtinActivity.class);
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
        } else {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, hambuger);
            popupMenu.getMenuInflater().inflate(R.menu.menu1, popupMenu.getMenu());

            if (user == null) {
                popupMenu.getMenu().getItem(0).setTitle("Username");
            } else {
                popupMenu.getMenu().getItem(0).setTitle("Username: " + user);
            }

            if (email == null) {
                popupMenu.getMenu().getItem(1).setTitle("Email");
            } else {
                popupMenu.getMenu().getItem(1).setTitle("Email: " + email);
            }
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {


                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    }

    private void Anhxa() {
        gioithieu = (TextView) findViewById(R.id.txtGioithieu);
        lehoi = (TextView) findViewById(R.id.txtLehoi);
        trochoi = (TextView) findViewById(R.id.txtTrochoi);
        thamquan = (TextView) findViewById(R.id.txtThamquan);
        datve = (Button) findViewById(R.id.btnDatve);
        webView = (WebView) findViewById(R.id.webViewHienThi);
        bar = (ProgressBar) findViewById(R.id.progressBar2);
        tinnhan = (TextView) findViewById(R.id.txtTinnhan);
        dienthoai = (ImageView) findViewById(R.id.imgDienthoai);
        hambuger = (ImageView) findViewById(R.id.hambuger);
    }

    public void shareLocationFacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn) {

            Double latitude = 10.7624176;
            Double longitude = 106.6811968;

            ShareDialog shareDialog = new ShareDialog(this);
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://www.openstreetmap.org/#map=19/" + latitude + "/" + longitude))
                    .build();

            if (ShareDialog.canShow(ShareLinkContent.class)) {
                shareDialog.show(linkContent);
            }
        } else {
            Toast.makeText(getApplication(), "You must be login facebook!", Toast.LENGTH_LONG).show();
        }

    }

    private void Logout() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn) {
            LoginManager.getInstance().logOut();
        }
        Intent intent = getIntent();
        signin = (Boolean) intent.getBooleanExtra("signin", false);
        startActivity(intent);

        Toast.makeText(getApplication(), "Logout successfully", Toast.LENGTH_SHORT).show();
    }

    public void DatveOnline() {
        Intent myBooking = new Intent(this, Datve.class);
        myBooking.putExtra("username",user);
        myBooking.putExtra("email",email);
        myBooking.putExtra("signin",signin);
        startActivity(myBooking);
        finish();
    }

    public void Gioithieu() {
        Intent intent = new Intent(MainActivity.this, Gioithieu.class);

        intent.putExtra("username",user);
        intent.putExtra("email",email);
        intent.putExtra("signin",signin);
        startActivity(intent);
        finish();
    }

    public void Trochoi() {
        Intent game = new Intent(this, trochoi.class);
        game.putExtra("username",user);
        game.putExtra("email",email);
        game.putExtra("signin",signin);
        startActivity(game);
        finish();
    }

    public void Lehoi() {
        Intent myFestival = new Intent(this, lehoi.class);
        myFestival.putExtra("username",user);
        myFestival.putExtra("email",email);
        myFestival.putExtra("signin",signin);
        startActivity(myFestival);
        finish();
    }

    public void Thamquan() {
        Intent myVisit = new Intent(this, Thamquan.class);
        myVisit.putExtra("username",user);
        myVisit.putExtra("email",email);
        myVisit.putExtra("signin",signin);
        startActivity(myVisit);
        finish();
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
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
