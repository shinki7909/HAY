package com.example.hay;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinActivity extends AppCompatActivity {
Button btnsend,btnjoin;
    LinearLayout confirmout;
    EditText etphone,etemail,etpass,etconfirmpass,etname,etage,connumber;
    String tempnumber;
    boolean confirm = false;
    boolean checkedid = false;
    UserService userService;
    Retrofit retrofit;
    int gender;
    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_join);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        etphone= findViewById(R.id.etPhone);
        btnjoin = findViewById(R.id.btnJoin);
        etemail= findViewById(R.id.etEmail);

        etpass=findViewById(R.id.etPassword);
        connumber =findViewById(R.id.connumber);
        etconfirmpass=findViewById(R.id.etconfirmpass);
        etname=findViewById(R.id.etname);
        etage=findViewById(R.id.etage);
        confirmout = findViewById(R.id.confirmout);
        group = findViewById(R.id.genderGroup);
        retrofit = new Retrofit.Builder().baseUrl(UserService.USER_URL).addConverterFactory(GsonConverterFactory.create()).build();

        userService=retrofit.create(UserService.class);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.optionMan:
                        gender =0;
                            break;
                    case R.id.optionWoman:
                        gender =1;
                        break;
                }


            }
        });

    }
    public void mClick(View v){
        switch(v.getId()){
            case R.id.btnSend:
                String number = etphone.getText().toString();

                if(number!=null&&!number.equals("")) {
                    tempnumber = new SMSsend(etphone.getText().toString()).smssend();
                    confirmout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btnJoin:
                if(confirm){
                    AppuserVO vo = new AppuserVO();
                    vo.setUserage(Integer.parseInt(etage.getText().toString()));
                    vo.setUsergender(gender);
                    vo.setUsername(etname.getText().toString());
                    vo.setUserpass(etpass.getText().toString());
                    vo.setUserid(etemail.getText().toString());
                    vo.setUserphone(etphone.getText().toString());

                    Call<Void> insertvo = userService.insertappuser(vo);
                    insertvo.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(JoinActivity.this, "가입완료", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(JoinActivity.this, "실패", Toast.LENGTH_SHORT).show();
                            Log.d("이유",t.getMessage());
                        }
                    });
                }else{
                    Toast.makeText(this, "인증번호 확인을 해주십시오", Toast.LENGTH_SHORT).show();
                }
break;
            case R.id.btnCheck:
                String numbertext =connumber.getText().toString();
                if(numbertext.equals(tempnumber)){
                    confirm=true;
                }else{
                    Toast.makeText(this, "인증번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.checkid:
                Call<Integer> checkid = userService.checkid(etemail.getText().toString());
                checkid.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        int count = response.body();
                        if(count==0){
                            checkedid=true;
                            Toast.makeText(JoinActivity.this, "사용가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        }else{
                            checkedid=false;
                            Toast.makeText(JoinActivity.this, "사용할 수 없는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.d("어류",t.getMessage());
                    }
                });
        }

    }
}
