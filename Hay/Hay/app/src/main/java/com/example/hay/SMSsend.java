package com.example.hay;

import android.util.Log;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.hay.NaverService.NAVER_URL;

public class SMSsend {
    Retrofit retrofit;
    NaverService ns;
    private String number;
    String confirmnumber ="";
    public SMSsend(String number) {
        this.number = number;
    }

    public String smssend(){
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json; charset=utf-8");
        header.put("x-ncp-auth-key", "uiV7hHbu3DyAt2g3jVT2");
        header.put("X-NCP-service-secret", "30af487ac92546429cb03d8a21436746");
        naverVO vo = new naverVO();
        vo.setType("SMS");
        vo.setFrom("01055067909");
        vo.setContentType("COMM");
        vo.setCountryCode("82");

        for(int i=0;i<=5;i++){
            confirmnumber+=(int)(Math.random()*10);
        }
        vo.setContent("인증번호는"+confirmnumber+"입니다.");
        String[] numberlist = new String[1];
        numberlist[0]= (number+"");
        vo.setTo(numberlist);
        System.out.println(vo.toString());
        retrofit = new Retrofit.Builder().baseUrl(NAVER_URL).addConverterFactory(GsonConverterFactory.create()).build();

        ns = retrofit.create(NaverService.class);

        Call<ResponseBody> call = ns.sendSMS(header,vo);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("승리", response.isSuccessful() + ""+response.headers().toString());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("패배", t.getMessage());
            }
        });
        return confirmnumber;
    }
}
