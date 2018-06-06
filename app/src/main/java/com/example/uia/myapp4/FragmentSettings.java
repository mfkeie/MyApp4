package com.example.uia.myapp4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class FragmentSettings extends Fragment {

    private SharedPreferencesHelper mSharedPreferencesHelper;
    private RadioGroup mRadioGroup;
    private RadioButton mRbGoogle;
    private RadioButton mRbYandex;
    private RadioButton mRbBing;

    public FragmentSettings() {
    }

    public static FragmentSettings newInstance() {
        FragmentSettings fragment = new FragmentSettings();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        mRadioGroup = view.findViewById(R.id.rgSearchEngine);
        mRbGoogle = view.findViewById(R.id.rbGoogle);
        mRbYandex = view.findViewById(R.id.rbYandex);
        mRbBing = view.findViewById(R.id.rbBing);

        mRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);

        switch (mSharedPreferencesHelper.getSerarchEngine()) {
            case R.id.rbGoogle :
                mRbGoogle.setChecked(true);
                break;
            case R.id.rbYandex :
                mRbYandex.setChecked(true);
                break;
            case R.id.rbBing :
                mRbBing.setChecked(true);
                break;
            default: break;
        }
        return view;
    }

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            mSharedPreferencesHelper.saveSearchEngine(checkedId);
        }
    };

}
