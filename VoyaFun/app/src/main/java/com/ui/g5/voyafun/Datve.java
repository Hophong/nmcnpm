package com.ui.g5.voyafun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Datve extends AppCompatActivity {

    TextView lehoi, trochoi, thamquan,gioithieu;
    EditText edtNgaythamquan,edtslNguoilon,edtslTreem,edtTong,edtHoten,edtDienthoai;
    Button btnDatve;
    Dialog dialogConfirm;
    TextView tvAlert;
    ImageView trangchu;
    String username_admin="nmcnpm.voyafun@gmail.com";
    String password_admin="1597532846";
    String username_user="user.voyafun@gmail.com";
    String password_user="1597532846";
    int price_adult = 120;
    int price_child = 60;

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

        edtslNguoilon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                edtTong.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String adult = edtslNguoilon.getText().toString();
                String child = edtslTreem.getText().toString();
                if(!adult.isEmpty() && !child.isEmpty()) {
                    String price = String.valueOf(Integer.valueOf(edtslNguoilon.getText().toString()) * price_adult + Integer.valueOf(edtslTreem.getText().toString()) * price_child);
                    edtTong.setText(price + "000" + " VND");
                }
            }
        });

        edtslTreem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                edtTong.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String adult = edtslNguoilon.getText().toString();
                String child = edtslTreem.getText().toString();
                if(!adult.isEmpty() && !child.isEmpty()) {
                    String price = String.valueOf(Integer.valueOf(edtslNguoilon.getText().toString()) * price_adult + Integer.valueOf(edtslTreem.getText().toString()) * price_child);
                    edtTong.setText(price + "000" + " VND");
                }
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
        edtTong = (EditText)this.findViewById(R.id.edtTong);
        edtHoten = (EditText)this.findViewById(R.id.edtHoten);
        edtDienthoai = (EditText)this.findViewById(R.id.edtDienthoai);
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
        String name = edtHoten.getText().toString();
        String phone = edtDienthoai.getText().toString();
        String date = edtNgaythamquan.getText().toString();

        String contextAlert = "";
        if(slnl.equals("0")  && slte.equals("0")){
            contextAlert="Tổng số lượng vé phải khác 0!";
        }else if(name.isEmpty() || phone.isEmpty() || date.isEmpty() || slnl.isEmpty() || slte.isEmpty()){
            contextAlert="Không được để trống thông tin!";
        }

        if(!contextAlert.isEmpty()) {
            Dialog dialog = new Dialog(Datve.this);
            dialog.setTitle("Cảnh báo");
            dialog.setContentView(R.layout.datve_soluong);
            tvAlert = (TextView)dialog.findViewById(R.id.tvAlert);
            tvAlert.setText(contextAlert);
            dialog.show();

        }else{
            ShowDialog();
        }
    }

    private void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Datve.this);
        builder.setTitle("Suoitien.com");
        builder.setMessage("Bạn xác nhận muốn mua vé?");
        builder.setCancelable(false);
        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                String msg = Message();
                new ConfirmAsyncTask(Datve.this).execute(msg);
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


    class ConfirmAsyncTask extends AsyncTask<String,Void,Boolean>{

        ProgressDialog dialog;

        public ConfirmAsyncTask(Activity activity) {
            super();
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle("ĐẶT VÉ");
            dialog.setMessage("Đang xử lý ...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
            if(dialog.isShowing()){
                dialog.dismiss();
            }


            AlertDialog.Builder builder = new AlertDialog.Builder(Datve.this);
            builder.setTitle("Suoitien.com");
            builder.setMessage("Đặt vé thành công vui lòng kiểm tra mail.");
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
            new SendMail().execute(username_user, password_user, username_admin, "ĐẶT VÉ", strings[0]);
            new SendMail().execute(username_admin, password_admin, username_user, "ĐẶT VÉ THÀNH CÔNG", strings[0]);
            return true;
        }
    }

    public String Message(){
        String nguoidat = "Tên khách hàng: " + edtHoten.getText().toString();
        String slvenguoilon = "Vé người lớn: " + edtslNguoilon.getText().toString();
        String slvetreem = "Vé trẻ em: " + edtslTreem.getText().toString();
        String ngaythamquan = "Ngày tham quan: " + edtNgaythamquan.getText().toString();
        String tongcong = "Tổng cộng: " + edtTong.getText().toString();
        return nguoidat + System.getProperty("line.separator") + slvenguoilon +
                System.getProperty("line.separator") + slvetreem + System.getProperty("line.separator") +
                ngaythamquan + System.getProperty("line.separator") + tongcong;
    }

}
