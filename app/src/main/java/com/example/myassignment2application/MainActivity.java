package com.example.myassignment2application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button notifyBtn; // notification button
//    private Button buttonStart; // service start
//    private Button buttonStop; // service stop

    //button objects
    private Button buttonStart;
    private Button buttonStop;

    //button5 content provider
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       notifyBtn = findViewById(R.id.notify_btn); //android:id="@+id/notify_btn" in activity_main.xml
       //Start: If we want to create notification(>=)greater then Oreo version then we have to create notification channel for it.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel); //this manager creates notification channel.
        }
        //End: If we want to create notification(>=)greater then Oreo version then we have to create notification channel for it.

        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //Start notification code is here.
              NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "My Notification");
              builder.setContentTitle("My Title");
              builder.setContentText("This is an notification example.");
              builder.setSmallIcon(R.drawable.ic_launcher_background);
              builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());

               //End notification code is here.
            }
        });

        //getting buttons from xml
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        //attaching onclick listener to buttons
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        //content provider
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonStart) {
            //start the service here
            startService(new Intent(this, MyService.class));
        } else if (view == buttonStop) {
            //stop the service here
            stopService(new Intent(this, MyService.class));
        }
    }

    //content provider
    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}
