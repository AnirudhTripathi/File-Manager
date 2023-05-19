package com.example.filemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;


import android.os.Environment;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();

        recents recents = new recents();
        browse browse = new browse();
        cloud cloud = new cloud();

//
//        String path = Environment.getExternalStorageDirectory().getPath();
//        browse fragment = browse.newInstance(path);
//        Toast.makeText(this, ""+path, Toast.LENGTH_SHORT).show();
//


        bottomNavigationView = findViewById(R.id.bottom_Navigation_View);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, recents).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.recents:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, recents).commit();
                        return true;
                    case R.id.browse:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, browse).commit();
                        return true;
                    case R.id.cloud:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, cloud).commit();
                        return true;


                }
                return false;
            }
        });
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else
            return false;
    }
    private void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this, "Storage permission is Required", Toast.LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},111);
    }
}