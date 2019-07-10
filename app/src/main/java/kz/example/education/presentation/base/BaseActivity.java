package kz.example.education.presentation.base;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import kz.example.education.R;
import kz.example.education.presentation.activities.CameraActivity;
import kz.example.education.presentation.activities.PlayerActivity;
import kz.example.education.presentation.activities.ProfileActivity;
import kz.example.education.presentation.fragments.TutorialSlideFragment;

public abstract class BaseActivity extends AppCompatActivity{

    NavigationView mNavigationView;
    DrawerLayout mDrawerLayoutNavigation;

    private Toolbar mToolbarBase;
    private TextView mTextViewToolbarTitle;

    MenuItem mCurrentSelectedItem;

    public Toolbar getBaseToolbar(){
        return mToolbarBase;
    }

    public TextView getBaseTextViewTitle(){
        return mTextViewToolbarTitle;
    }

    public int getLayout(){
        return R.layout.activity_base;
    }

    public BaseActivity getMainActivity(){
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        initializeSupportToolbar();
        initializeFragment();
        initializeNavigationView();
        initialzieNavigationDrawer();

        initializeDrawerListener();
        initializeNavigationListener();
    }

    public abstract BaseFragment onInitFragment();

    public void initializeNavigationView(){
        mNavigationView = (NavigationView)findViewById(R.id.navigationview_activity_start_navigator);
    }

    public void initialzieNavigationDrawer(){
        mDrawerLayoutNavigation = new DrawerLayout(this);
    }

    public void openDrawer(){
        mDrawerLayoutNavigation.openDrawer(Gravity.START);
    }

    public void initializeDrawerListener(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayoutNavigation,
                mToolbarBase,
                R.string.navigation_menu_open,
                R.string.navigation_menu_close

        ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        toggle.setDrawerIndicatorEnabled(true);
        mDrawerLayoutNavigation.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void initializeNavigationListener(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayoutNavigation.closeDrawer(Gravity.START);

                if(mCurrentSelectedItem == null){
                    mCurrentSelectedItem = item;
                    mCurrentSelectedItem.setChecked(true);
                }else{
                    mCurrentSelectedItem.setChecked(false);
                    mCurrentSelectedItem = item;
                    mCurrentSelectedItem.setChecked(true);
                }
                return false;
            }
        });
    }

    public void initializeSupportToolbar(){
        mToolbarBase = (Toolbar)findViewById(R.id.activity_base_toolbar);
        mTextViewToolbarTitle = (TextView)findViewById(R.id.textview_activity_base_toolbar_title);

        mToolbarBase.setTitle("");
        setSupportActionBar(mToolbarBase);
    }

    public void initializeFragment(){
        if(getCurrentFragment() == null){
            displayFragment(onInitFragment());
        }
    }

    public void displayFragment(BaseFragment baseFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(fragmentManager.getBackStackEntryCount() > 0){
            fragmentTransaction.hide(getCurrentFragment());
        }

        fragmentTransaction.replace(R.id.framelayout_activity_base_fragment_container, baseFragment, baseFragment.getFragmentTag());
        fragmentTransaction.commit();
    }

    private BaseFragment getCurrentFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        int entryCount = fragmentManager.getBackStackEntryCount();
        if (entryCount > 0) {
            String fragmentTag = fragmentManager.getBackStackEntryAt(entryCount - 1).getName();
            return (BaseFragment) fragmentManager.findFragmentByTag(fragmentTag);
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        }else{
            finish();
        }
    }

    public void navigate(Class activity){
        Intent navigator = new Intent(this, activity);
        startActivity(navigator);
    }
}
