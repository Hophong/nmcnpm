package com.ui.g5.voyafun;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    EditText edtUsernam_reg,edtEmail_reg,edtPassword_reg,edtPhone_reg;
    Button btnregiter;
    ArrayList<User> arrayUser=new ArrayList<>();
    String url_insertdata="https://nqphu1998.000webhostapp.com/insertdata.php";
    String url_getdata = "https://nqphu1998.000webhostapp.com/getdata.php";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String phonePatten="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("BUNDLE");
//        if(bundle!=null)
//        {
//            arrayUser=(ArrayList) bundle.getCharSequenceArrayList("User");
//        }
        Anhxa();
        btnregiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadJson(url_getdata);
                String user = edtUsernam_reg.getText().toString().trim();
                String email = edtEmail_reg.getText().toString().trim();
                String pass = edtPassword_reg.getText().toString().trim();
                String phone = edtPhone_reg.getText().toString().trim();

                if (user.isEmpty() || email.isEmpty() || pass.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "nhap lai", Toast.LENGTH_SHORT).show();
                }
                else if(email.matches(emailPattern)!=true) {
                    Toast.makeText(SignupActivity.this, "invalid email", Toast.LENGTH_SHORT).show();
                }
                else if (phone.matches(phonePatten)!=true)
                {
                    Toast.makeText(SignupActivity.this, "invalid phone", Toast.LENGTH_SHORT).show();
                }
                else if(checkUserName_Email(user,email,arrayUser)!=true)
                {
                    Toast.makeText(SignupActivity.this, "username or email da ton tai", Toast.LENGTH_SHORT).show();
                }
                else {
                    Insert(url_insertdata);
                    Intent intent=new Intent(SignupActivity.this,SigninActivity.class);
                    //finish();
                    //Intent intent2=new Intent(SignupActivity.this,MaincreenActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private  boolean checkUserName_Email(String UserName,String Email,ArrayList<User> users)
    {
        for (int i=0;i<users.size();i++)
        {
            if(users.get(i).getUserName().equals(UserName)||users.get(i).getEmail().equals(Email)) return false;
        }
        return true;
    }
    private void Anhxa() {
        btnregiter      =   (Button) findViewById(R.id.btnregiter);
        edtUsernam_reg  =   (EditText) findViewById(R.id.edtUsername_reg);
        edtEmail_reg    =   (EditText) findViewById(R.id.edtEmail_reg);
        edtPassword_reg =   (EditText) findViewById((R.id.edtPassword_reg));
        edtPhone_reg    =   (EditText) findViewById((R.id.edtphone_reg));
    }
    public void ReadJson(String url)
    {
        RequestQueue requestqueue=Volley.newRequestQueue(SignupActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject object =response.getJSONObject(i);
                                arrayUser.add(new User(
                                        object.getInt("Id"),
                                        object.getString("UserName"),
                                        object.getString("Email"),
                                        object.getString("Password"),
                                        object.getString("Phone")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestqueue.add(jsonArrayRequest);
    }
    private void Insert(String url)
    {
        RequestQueue requestqueue = Volley.newRequestQueue(SignupActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText((SignupActivity.this), "success", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else Toast.makeText(SignupActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("username",edtUsernam_reg.getText().toString().trim());
                params.put("email",edtEmail_reg.getText().toString().trim());
                params.put("password",edtPassword_reg.getText().toString().trim());
                params.put("phone",edtPassword_reg.getText().toString().trim());
                return params;
            }
        };
        requestqueue.add(stringRequest);
    }
}
