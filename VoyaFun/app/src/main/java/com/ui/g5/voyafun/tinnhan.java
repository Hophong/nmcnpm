package com.ui.g5.voyafun;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class tinnhan extends AppCompatActivity {

    String username_admin="nmcnpm.voyafun@gmail.com";
    String username_user="user.voyafun@gmail.com";
    String password_user="1597532846";

    EditText edtTinnhan,edtGioiThieu,edtSdt;
    Button btnGuitn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinnhan);

        Anhxa();

        btnGuitn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtGioiThieu.getText().toString().equals("") || edtSdt.getText().toString().equals("") ||
                        edtTinnhan.getText().toString().equals("")) {
                    Dialog dialog = new Dialog(tinnhan.this);
                    dialog.setTitle("Cảnh báo");
                    dialog.setContentView(R.layout.datve_soluong);
                    TextView tvAlert = (TextView) dialog.findViewById(R.id.tvAlert);
                    tvAlert.setText("Vui lòng điền thông tin.");
                    dialog.show();
                } else {
                    String msg = Message();
                    new TinnhanAsyncTask(tinnhan.this).execute(msg);
                }
            }
        });

    }

    void Anhxa(){
        edtTinnhan = (EditText)this.findViewById(R.id.edtTinNhan);
        btnGuitn = (Button)this.findViewById(R.id.btnGuitn);
        edtGioiThieu = (EditText)this.findViewById(R.id.edtGioithieu);
        edtSdt = (EditText)this.findViewById(R.id.edtSdt);
    }

    class TinnhanAsyncTask extends AsyncTask<String,Void,Boolean> {

        ProgressDialog dialog;

        public TinnhanAsyncTask(Activity activity) {
            super();
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle("TIN NHẮN");
            dialog.setMessage("Đang xử lý ...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
            if(dialog.isShowing()){
                dialog.dismiss();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(tinnhan.this);
            builder.setTitle("Suoitien.com");
            builder.setMessage("Xin chân thành cảm ơn bạn đã đóng góp ý kiến cho chúng tôi.\nChúc bạn có một chuyến tham quan vui vẻ.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            new SendMail().execute(username_user, password_user, username_admin, "TIN NHẮN", strings[0]);
            return true;
        }
    }

    public String Message(){
        String name = "Anh/Chị: " + edtGioiThieu.getText().toString();
        String phone = "SDT: " + edtSdt.getText().toString();
        String message = "Ý kiến: " + edtTinnhan.getText().toString();
        return name + System.getProperty("line.separator") + phone +
                System.getProperty("line.separator") + message ;
    }
}
