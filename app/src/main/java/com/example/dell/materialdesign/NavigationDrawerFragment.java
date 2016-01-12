package com.example.dell.materialdesign;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements CustomArrayAdapter.Clicklistener{
    private RecyclerView recyclerview;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearned;
    private boolean mFromSavedInstanceState;
    private static final String PREF_FILE_NAME="check.txt";
    private static final String KEY_MUSER_LEARNED="user_learned_drawer";
    private View containerView;
    private CustomArrayAdapter adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mUserLearned=Boolean.valueOf(readFromPreferences(getActivity(),KEY_MUSER_LEARNED,"false"));
       // if(savedInstanceState!=null){
         //   mFromSavedInstanceState=true;
       // }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerview=(RecyclerView)layout.findViewById(R.id.recycler);
        adapter=new CustomArrayAdapter(getActivity(),getdata());
        adapter.setclicklistener(this);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

public static List<information> getdata(){
    List<information>data=new ArrayList<>();
    int [] icons={R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a};
    String[] title={"Leicester","Arsenal","Manchester City","Spuds"};
    for(int i=0;i<title.length && i<icons.length;i++){
        information current=new information();
        current.IconId=icons[i];
        current.title=title[i];
        data.add(current);
    }
    return  data;
}
    public void setUp(int Fragment_ID,DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView=getActivity().findViewById(Fragment_ID);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearned){
                    mUserLearned=true;
                    saveToPreferences(getActivity(),KEY_MUSER_LEARNED,mUserLearned+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<0.7){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };

        //if(!mUserLearned && !mFromSavedInstanceState)
        if(!mUserLearned){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
    public static void saveToPreferences(Context context,String PreferenceName,String PreferenceValue){
        SharedPreferences sp=context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(PreferenceName,PreferenceValue);
        editor.commit();

    }
    public static String readFromPreferences(Context context,String PreferenceName,String defaultValue){
        SharedPreferences sp=context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        return sp.getString(PreferenceName,defaultValue);
    }

    @Override
    public void itemclick(View view, int position) {
startActivity(new Intent(getActivity(),SubActivity.class));
    }
}