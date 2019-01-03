package com.ui.g5.voyafun;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Datve extends AppCompatActivity {

    TextView lehoi, trochoi, thamquan,gioithieu;
    EditText edtNgaythamquan,edtslNguoilon,edtslTreem;
    Button btnDatve;
    Dialog dialogConfirm;
    TextView tvAlert;
    ImageView trangchu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datve);
        // anh xa
        Anhxa();

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

        lehoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lehoi();
            }
        });

        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trangchu();
            }
        });

        thamquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thamquan();
            }
        });

        edtNgaythamquan.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                chooseDate();
                return true;
            }
        });

        btnDatve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });

    }

    private void Anhxa() {
        trangchu    = (ImageView) findViewById(R.id.home);
        lehoi       = (TextView)findViewById(R.id.txtLehoi);
        trochoi     = (TextView)findViewById(R.id.txtTrochoi);
        thamquan    = (TextView)findViewById(R.id.txtThamquan);
        gioithieu   = (TextView) findViewById(R.id.txtGioithieu);
        edtNgaythamquan = (EditText)this.findViewById(R.id.edtNgaythamquan);
        btnDatve = (Button)this.findViewById(R.id.btnDatve);
        edtslNguoilon = (EditText)this.findViewById(R.id.edtslNguoilon);
        edtslTreem = (EditText)this.findViewById(R.id.edtslTreem);
    }

    public void Trangchu() {
        Intent myHome = new Intent(this, MainActivity.class);
        startActivity(myHome);
    }
    public void Gioithieu() {
        Intent intent = new Intent(this, Gioithieu.class);
        startActivity(intent);
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

    private void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePicker =
                new DatePickerDialog(Datve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(final DatePicker view, final int year, final int month,
                                          final int dayOfMonth) {

                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        calendar.set(year, month, dayOfMonth);
                        String dateString = sdf.format(calendar.getTime());

                        edtNgaythamquan.setText(dateString); // set the date
                    }
                }, year, month, day); // set date picker to current date

        datePicker.show();

        datePicker.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(final DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    private void confirm(){
        String slnl=edtslNguoilon.getText().toString(), slte=edtslTreem.getText().toString();
        if(slnl.equals("") || slnl.equals("0")){
            slnl="0";
        }
        if(slte.equals("") || slte.equals("0")){
            slte="0";
        }
        String contextAlert = "";
        if(slnl.equals("0")  && slte.equals("0")){
            contextAlert="Tổng số lượng vé phải khác 0!";
        }else if(edtNgaythamquan.getText().toString().equals("")){
            contextAlert="Ngày tham quan không được để trống!";
        }

        if(!contextAlert.isEmpty()) {
            Dialog dialog = new Dialog(Datve.this);
            dialog.setTitle("Cảnh báo");
            dialog.setContentView(R.layout.datve_soluong);
            tvAlert = (TextView)dialog.findViewById(R.id.tvAlert);
            tvAlert.setText(contextAlert);
            dialog.show();

        }else{
            dialogConfirm = new Dialog(Datve.this);
            dialogConfirm.setTitle("Xác nhận mua vé");
            dialogConfirm.setContentView(R.layout.datve_xacnhan);
            dialogConfirm.show();
        }
    }

    public void XacnhanKhong(View view){
        if(dialogConfirm.isShowing()){
            dialogConfirm.dismiss();
        }
    }

    public void XacnhanCo(View view){
        if(dialogConfirm.isShowing()){
            dialogConfirm.dismiss();
        }
        new Sending_mail_automatically().execute("qphongqh@gmail.com","Demo sending email automatically","Succeeded");
    }

    class Sending_mail_automatically extends AsyncTask<String,Void,Boolean> {

        String sub ;                                     // Tiêu đề
        Mail m ;                                         // Đối tượng Mail
        String[] toArr ;                                 // Mảng tên mail nhận ( Đoạn code dưới chỉ làm cho 1 mail nhận)
        boolean ret=true;                               // Xác nhận gửi thành công

        public Sending_mail_automatically() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Khởi tạo Mail người gửi
            m = new Mail("faqofo@gmail.com", "03031303");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            String msg = "Đặt vé thành công! Vui lòng kiểm tra Mail";
            if(ret == false){
                msg = "Đặt vé thất bại";;
            }
            Toast.makeText(Datve.this, msg, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected Boolean doInBackground(String... strings) {

            // Lấy tên mail người nhận
            toArr = new String[] {strings[0]};

            // Gửi từ đâu
            m.setFrom("faqofo@gmail.com");
            //Gửi đến đâu
            m.setTo(toArr);

            // Lấy tiêu đề
            sub = strings[1] ;
            m.setSubject(sub);
            // Set nội dung
            m.setBody(strings[2]);
            try {

                if (m.send()) {
                    //successful
                } else {
                    ret = false;
                    //failure
                }
            } catch (Exception e) {

                Log.e("MailApp", "Could not send email", e);
            }
            return  ret;
        }
    }
}
