package com.example.user.flotingiconpage;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class FlotingButtonService extends Service implements GooeyMenu.GooeyMenuInterface {
    //private ImageView imageView;
    private WindowManager windowManager;
    private GooeyMenu imageView;
    private Toast mToast;

    public FlotingButtonService() {

        Log.d("suri", "inside service");

    }


    @Override
    public void onCreate() {
        super.onCreate();

        /*//imageView = new ImageView(this);
        imageView = new GooeyMenu(this);

        Log.d("suri", "inside service");


        // imageView.setBackgroundResource(R.drawable.circle);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams myParams = new WindowManager.LayoutParams(
                800,
                800,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        myParams.gravity = Gravity.CENTER;
        myParams.x = 0;
        myParams.y = 100;
        Log.d("suri", "adding view to windowmaanger");
        windowManager.addView(imageView, myParams);
        try {


            imageView.setOnMenuListener(this);

            //for moving the picture on touch and slide
           *//* imageView.setOnTouchListener(new View.OnTouchListener() {
                WindowManager.LayoutParams paramsT = myParams;
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;
                private long touchStartTime = 0;


                @Override
                public boolean onTouch(View v, MotionEvent event) {


                    //remove face bubble on long press
                    if (System.currentTimeMillis() - touchStartTime > ViewConfiguration.getLongPressTimeout() && initialTouchX == event.getX()) {
                        windowManager.removeView(imageView);
                        stopSelf();
                        return false;
                    }
                    switch (event.getAction()) {


                        case MotionEvent.ACTION_DOWN:
                            touchStartTime = System.currentTimeMillis();
                            initialX = myParams.x;
                            initialY = myParams.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            myParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                            myParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                            windowManager.updateViewLayout(v, myParams);
                            break;


                    }
                    return false;
                }
            });*//*
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void menuOpen() {
        //showToast("open");
    }

    @Override
    public void menuClose() {
        //showToast("close");
    }

    private void showToast(String msg) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ArrayList<ApplicationInfo> passs = intent.getParcelableArrayListExtra("extra");
        Toast.makeText(getApplicationContext(), "" + passs.size(), Toast.LENGTH_SHORT).show();


        //imageView = new ImageView(this);
        imageView = new GooeyMenu(this, passs);

        Log.d("suri", "inside service");


        // imageView.setBackgroundResource(R.drawable.circle);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams myParams = new WindowManager.LayoutParams(
                500,
                500,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        myParams.gravity = Gravity.CENTER;
        myParams.x = 0;
        myParams.y = 100;
        Log.d("suri", "adding view to windowmaanger");
        windowManager.addView(imageView, myParams);
        try {


            imageView.setOnMenuListener(this);

            //for moving the picture on touch and slide
            imageView.setOnTouchListener(new View.OnTouchListener() {
                WindowManager.LayoutParams paramsT = myParams;
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;
                private long touchStartTime = 0;


                @Override
                public boolean onTouch(View v, MotionEvent event) {


                    //remove face bubble on long press
                   /* if (System.currentTimeMillis() - touchStartTime > ViewConfiguration.getLongPressTimeout() && initialTouchX == event.getX()) {
                        windowManager.removeView(imageView);
                        stopSelf();
                        return false;
                    }*/
                    switch (event.getAction()) {


                        case MotionEvent.ACTION_DOWN:
                            touchStartTime = System.currentTimeMillis();
                            initialX = myParams.x;
                            initialY = myParams.y;
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            myParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                            myParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                            windowManager.updateViewLayout(v, myParams);
                            break;


                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void menuItemClicked(ApplicationInfo menuNumber, PackageManager pm, int menuint) {
        Intent launchIntent = pm.getLaunchIntentForPackage(menuNumber.packageName);
        startActivity(launchIntent);
        imageView.cancelAllAnimation();
        imageView.startHideAnimate();
        imageView.isMenuVisible=!imageView.isMenuVisible;
        Toast.makeText(getApplicationContext(), "" + menuint, Toast.LENGTH_SHORT).show();
    }
}
