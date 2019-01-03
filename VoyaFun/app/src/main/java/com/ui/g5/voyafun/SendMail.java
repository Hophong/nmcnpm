package com.ui.g5.voyafun;

import android.os.AsyncTask;
import android.util.Log;

class SendMail extends AsyncTask<String,Void,Boolean> {

    String sub ;                                     // Tiêu đề
    Mail m ;                                         // Đối tượng Mail
    String[] toArr ;                                 // Mảng tên mail nhận ( Đoạn code dưới chỉ làm cho 1 mail nhận)
    boolean ret=true;                               // Xác nhận gửi thành công

    public SendMail() {

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Khởi tạo Mail người gửi
        //m = new Mail(username_admin, password_admin);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        ret = aBoolean;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected Boolean doInBackground(String... strings) {
        // Lấy mal gửi
        m = new Mail(strings[0],strings[1]);

        // Lấy tên mail người nhận
        toArr = new String[] {strings[2]};

        // Gửi từ đâu
        m.setFrom(strings[0]);
        //Gửi đến đâu
        m.setTo(toArr);

        // Lấy tiêu đề
        sub = strings[3] ;
        m.setSubject(sub);
        // Set nội dung
        m.setBody(strings[4]);
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
