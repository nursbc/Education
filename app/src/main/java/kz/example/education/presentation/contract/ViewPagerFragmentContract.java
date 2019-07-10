package kz.example.education.presentation.contract;

import android.widget.Button;

public interface ViewPagerFragmentContract {
    public interface View{
        public void initializeViews();

        public void initializeListeners();

        public void initializeViewPager();

        public void initializeViewPagerAdapter();

        public void initializeFragments();

        public void checkButtonsVisibility(int position);

        public void enableButton(Button button);

        public void disableButton(Button button);

    }
}
