package kz.example.education.presentation.activities;

import kz.example.education.presentation.base.BaseActivity;
import kz.example.education.presentation.base.BaseFragment;
import kz.example.education.presentation.fragments.SplashFragment;

public class SplashActivity extends BaseActivity {
    @Override
    public BaseFragment onInitFragment() {
        return new SplashFragment();
    }
}
