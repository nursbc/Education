package kz.example.education.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kz.example.education.R;
import kz.example.education.presentation.adapters.viewpager_adapters.TutorialPagerAdapter;
import kz.example.education.presentation.base.BaseFragment;
import kz.example.education.presentation.contract.ViewPagerFragmentContract;

public class ViewPagerFragment extends BaseFragment implements
        ViewPagerFragmentContract.View,
        View.OnClickListener,
        ViewPager.OnPageChangeListener{

    ViewPager mViewPagerTutorialSlider;
    TutorialPagerAdapter mTutorialPagerAdapterSlider;

    Button mButtonPrevious;
    Button mButtonNext;

    @Override
    public String getFragmentTag() {
        return ViewPagerFragment.class.getName();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        initializeViews();
        initializeViewPagerAdapter();
        initializeFragments();
        initializeViewPager();
        initializeListeners();

        return baseView;
    }

    @Override
    public void customizeActionBar() {
        getBaseActivity()
                .getBaseTextViewTitle()
                .setText(getString(R.string.fragment_view_pager_toolbar_title));

        getBaseActivity()
                .getBaseToolbar()
                .setVisibility(View.VISIBLE);
    }

    @Override
    public int onLayoutId() {
        return R.layout.fragment_viewpager;
    }

    @Override
    public void initializeViews() {
        mViewPagerTutorialSlider = (ViewPager)baseView.findViewById(R.id.viewpager_fragment_tutorial_slider);
        mButtonNext = (Button)baseView.findViewById(R.id.button_fragment_viewpager_next);
        mButtonPrevious = (Button)baseView.findViewById(R.id.button_fragment_viewpager_previous);
    }

    @Override
    public void initializeListeners() {
        mButtonPrevious.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mViewPagerTutorialSlider.addOnPageChangeListener(this);
    }

    @Override
    public void initializeViewPager() {
        mViewPagerTutorialSlider.setAdapter(mTutorialPagerAdapterSlider);
    }

    @Override
    public void initializeViewPagerAdapter() {

        mTutorialPagerAdapterSlider = new TutorialPagerAdapter(getFragmentManager());
    }

    @Override
    public void initializeFragments() {
        TutorialSlideFragment firstSlideFragment = new TutorialSlideFragment();
        TutorialSlideFragment secondSlideFragment = new TutorialSlideFragment();
        TutorialSlideFragment thirdSlideFragment = new TutorialSlideFragment();

        mTutorialPagerAdapterSlider.addFragment(firstSlideFragment);
        mTutorialPagerAdapterSlider.addFragment(secondSlideFragment);
        mTutorialPagerAdapterSlider.addFragment(thirdSlideFragment);
    }

    @Override
    public void checkButtonsVisibility(int position) {
        switch (position){
            case 0:
                disableButton(mButtonPrevious);
                enableButton(mButtonNext);
                break;
            case 1:
                enableButton(mButtonPrevious);
                enableButton(mButtonNext);
                break;
            case 2:
                disableButton(mButtonNext);
                enableButton(mButtonPrevious);
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        checkButtonsVisibility(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void enableButton(Button button) {
        button.setVisibility(View.VISIBLE);
        button.setEnabled(true);
    }

    @Override
    public void disableButton(Button button) {
        button.setVisibility(View.INVISIBLE);
        button.setEnabled(false);
    }
}
