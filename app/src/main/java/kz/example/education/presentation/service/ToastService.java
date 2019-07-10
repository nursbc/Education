package kz.example.education.presentation.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class ToastService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(ToastService.class.getSimpleName(), "STARTED");
                Toast.makeText(getApplicationContext(), "RUNNING", Toast.LENGTH_SHORT).show();
            }
        },20000);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
