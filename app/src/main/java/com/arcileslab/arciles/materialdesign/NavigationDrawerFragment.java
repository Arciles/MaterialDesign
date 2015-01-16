package com.arcileslab.arciles.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String PREF_FILE_NAME = "testPref";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


    public void setUp(DrawerLayout drawerLayout , Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu(); //ActionBar'ı tekrardan cizmeye yariyor
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void savedThePrefrences (Context context , String preferenceName , String prefrenceValue) {

        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE); //SharedPreferences ı ac dosya ismini ayarla ve private yap
        SharedPreferences.Editor editor = preferences.edit(); //SharedPreferences ı editor olarak ac
        editor.putString(preferenceName, prefrenceValue).apply(); // icine yaz ve apply ile asenkron olarak yaz

    }

    public static String readThePrefrences (Context context , String preferenceName , String prefrenceValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE_NAME,context.MODE_PRIVATE);
        return preferences.getString(preferenceName,prefrenceValue);

    }
}
