package kz.example.education.presentation;

import android.app.Application;
import android.content.Intent;

import kz.example.education.presentation.service.ToastService;

public class EducationApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initializeToastService();
    }

    public void initializeToastService(){
        Intent toastService = new Intent(this, ToastService.class);
        startService(toastService);
    }

}
