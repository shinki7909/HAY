package com.example.hay;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
String[] required_permission= {Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        int permssioncamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int permssionlocation  = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        int permssionread = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int permssionwrite = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

if(permssioncamera== PackageManager.PERMISSION_GRANTED&&permssionlocation==PackageManager.PERMISSION_GRANTED&&permssionread==PackageManager.PERMISSION_GRANTED
        &&permssionwrite==PackageManager.PERMISSION_GRANTED){
                startActivity(new Intent(this,LogoActivity.class));
        }else{
    ActivityCompat.requestPermissions(this,required_permission,100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        startActivity(new Intent(this,LogoActivity.class));
    }
}
