package com.example.uia.myapp4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityLaunch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null) {
            fragment = new FragmentMain();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment, "main")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSettings:
                showMessage(item.toString());
                switchFragment("settings");
                break;
            case R.id.actionSearch:
                showMessage(item.toString());
                break;
            case R.id.actionExit:
                showMessage(item.toString());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    private void switchFragment(String name) {
        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentByTag(name);
        if(fragment == null) {
            switch (name) {
                case "settings":
                    fragment = new FragmentSettings();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment, name)
                            .addToBackStack(FragmentSettings.class.getName())
                            .commit();
                    break;
                default: break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
