package kz.example.education.presentation.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import kz.example.education.R;
import kz.example.education.domain.usecase.GetCategoriesUseCase;
import kz.example.education.presentation.activities.SplashActivity;
import kz.example.education.presentation.base.BaseFragment;
import kz.example.education.presentation.contract.SplashFragmentContract;

public class SplashFragment extends BaseFragment implements SplashFragmentContract.View,View.OnClickListener{

    GetCategoriesUseCase getCategoriesUseCase;

    int counter = 0;

    Observable<String> stringObservable;

    String[] trees = new String[10];

    Button mButtonData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getCategoriesUseCase = new GetCategoriesUseCase();
        getCategoriesUseCase.execute();

        initializeViews();
        initializeListeners();
        initializeObservable();
        initializeObserver();

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPagerTransition();
            }
        }, 3000);*/

        ((SplashActivity)getActivity()).getBaseTextViewTitle().setText("CHANGED");

        return baseView;
    }

    public void initializeListeners(){
        mButtonData.setOnClickListener(this);
    }


    public void initializeViews(){
        mButtonData = (Button)baseView.findViewById(R.id.fragment_splash_button_date);
    }
    public void initializeObservable(){
        stringObservable = Observable.fromArray(trees);
    }

    public void initializeObserver(){
        Observer<String> observer = new Observer<String>(){

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("NEW DATA: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        stringObservable.subscribe(observer);
    }

    @Override
    public String getFragmentTag() {
        return SplashFragment.class.getName();
    }

    @Override
    public void customizeActionBar() {
        //((SplashActivity)getActivity()).getBaseTextViewTitle().setText("ЭТО СПЛАШЬ");
        ((SplashActivity)getActivity()).getBaseTextViewTitle().setTextColor(Color.RED);
        ((SplashActivity)getActivity()).getBaseToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().openDrawer();
            }
        });

    }

    @Override
    public int onLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void viewPagerTransition() {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        getBaseActivity().displayFragment(viewPagerFragment);
    }


    @Override
    public void onClick(View v) {
        if(counter <= 9)
        {
            trees[counter] = "Яблоня";
            counter++;


        }
    }
}
