package com.zms.jniLearning;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            System.out.println(getApplicationContext().getFilesDir() + "/libtestJNI.so");
            SoUtils.copySo(getApplicationContext());
            System.out.println(new File(getApplicationContext().getFilesDir() + "/libtestJNI.so").exists());
            //load并不是随便路径都可以，只支持应用本地存储路径/data/data/${package-name}/，或者是系统lib路径system/lib等,这2类路径；
            //otherwise:java.lang.UnsatisfiedLinkError: dlopen failed: library "" " is not accessible for the namespace "classloader-namespace" at java.lang.Runtime.load0(Runtime.java:938)
            System.load(getApplicationContext().getFilesDir() + "/libtestJNI.so");
            String getString = getString();
            System.out.println(getString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static native String getString();
}