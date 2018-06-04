package com.example.uia.myapp4;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    public static final String SEARCH_ENGINE_KEY = "SEARCH_ENGINE";

    private SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveSearchEngine(int searchEngine) {
        if (mSharedPreferences.getInt(SEARCH_ENGINE_KEY, 0) != searchEngine)
            mSharedPreferences.edit().putInt(SEARCH_ENGINE_KEY, searchEngine).apply();
    }

    public int getSerarchEngine() {
        return mSharedPreferences.getInt(SEARCH_ENGINE_KEY, 0);
    }
}
