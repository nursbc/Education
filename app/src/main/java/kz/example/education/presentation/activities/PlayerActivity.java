package kz.example.education.presentation.activities;

import kz.example.education.presentation.base.BaseActivity;
import kz.example.education.presentation.base.BaseFragment;
import kz.example.education.presentation.fragments.TutorialSlideFragment;

public class PlayerActivity extends BaseActivity {

    public PlayerActivity getPlayerActivity(){
        return this;
    }

    @Override
    public BaseFragment onInitFragment() {
        return new TutorialSlideFragment();
    }
}
