package kz.example.education.presentation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    public View baseView;

    public abstract String getFragmentTag();

    public abstract void customizeActionBar();

    public abstract int onLayoutId();

    public BaseActivity getBaseActivity(){
        return (BaseActivity)getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        customizeActionBar();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            customizeActionBar();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(onLayoutId(), container, false);
        return baseView;
    }
}
