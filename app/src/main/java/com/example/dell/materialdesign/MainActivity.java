package com.example.dell.materialdesign;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.materialdesign.tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager mpager;
    SlidingTabLayout mtabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        mpager = (ViewPager) findViewById(R.id.pager);
        mtabs = (SlidingTabLayout) findViewById(R.id.slidingtab);
        mpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mtabs.setViewPager(mpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.navigate) {
            Intent i = new Intent();
            i.setClass(this, SubActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
String[]tabs={"HOME","BLOGS","SCREENINGS"};
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Myfragment myfragment = Myfragment.getInstance(position);

            return myfragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    public static class Myfragment extends Fragment {
        private TextView text;
        public static Myfragment getInstance(int position) {
            Myfragment myfragment = new Myfragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myfragment.setArguments(args);
            return myfragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
            View layout=inflater.inflate(R.layout.fragment_my,container,false);
            text=(TextView)layout.findViewById(R.id.fragmentText);
            Bundle bundle=getArguments();
            if(bundle!=null){
                text.setText(String.valueOf(bundle.getInt("position")+1));
            }
            return  layout;
        }
    }
}