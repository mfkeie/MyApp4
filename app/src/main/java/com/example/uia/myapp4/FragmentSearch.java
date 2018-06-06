package com.example.uia.myapp4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FragmentSearch extends Fragment {

    private SharedPreferencesHelper mSharedPreferencesHelper;
    private Button mButtonSearch;
    private EditText mEditTextSearch;

    public FragmentSearch() {
    }

    public static FragmentSearch newInstance(String param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        mButtonSearch = view.findViewById(R.id.btnSearch);
        mEditTextSearch = view.findViewById(R.id.etSearch);

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(mEditTextSearch.getText())) {
                    String searchText = mEditTextSearch.getText().toString();
                    try {
                        String searchEngine = "";
                        String param = "q";
                        switch (mSharedPreferencesHelper.getSerarchEngine()) {
                            case R.id.rbGoogle :
                                searchEngine = "google";
                                param = "q";
                                break;
                            case R.id.rbYandex :
                                searchEngine = "yandex";
                                param = "text";
                                break;
                            case R.id.rbBing :
                                searchEngine = "bing";
                                param = "q";
                                break;
                            default: break;
                        }
                        if(!TextUtils.isEmpty(searchEngine)) {
                            String escapedQuery = URLEncoder.encode(searchText, "UTF-8");
                            Uri uri = Uri.parse(String.format("http://www.%s.ru/search?%s=%s", searchEngine, param, escapedQuery));
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        } else {
                            showMessage("Необходимо в настройках выбрать поисковую систему!");
                        }
                    } catch (UnsupportedEncodingException ex) {
                        Log.d("ERROR", ex.getMessage());
                    }
                }
            }
        });

        return view;
    }

    private void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}
