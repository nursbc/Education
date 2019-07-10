package kz.example.education.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import kz.example.education.R;
import kz.example.education.presentation.base.BaseFragment;

public class TutorialSlideFragment extends BaseFragment {

    ImageView mImageViewBanner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        initializeViews();
        initializeImage();

        return baseView;
    }

    public void initializeViews(){
        mImageViewBanner = (ImageView)baseView.findViewById(R.id.imageview_fragment_tutorial_slider);
    }

    public void initializeImage(){
        Picasso.get().load(R.drawable.nature).into(mImageViewBanner);
    }

    @Override
    public String getFragmentTag() {
        return TutorialSlideFragment.class.getName();
    }

    @Override
    public void customizeActionBar() {
        getBaseActivity().getBaseToolbar().setVisibility(View.VISIBLE);
    }

    @Override
    public int onLayoutId() {
        return R.layout.fragment_tutorial_slider;
    }
}
