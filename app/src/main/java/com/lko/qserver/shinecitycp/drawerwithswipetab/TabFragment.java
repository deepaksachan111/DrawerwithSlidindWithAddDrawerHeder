package com.lko.qserver.shinecitycp.drawerwithswipetab;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lko.qserver.shinecitycp.Fragment.LoginFragment;
import com.lko.qserver.shinecitycp.R;

/**
 * Created by Ratan on 7/27/2015.
 */
public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;
    MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
            View x =  inflater.inflate(R.layout.drawerwithswipe_tab_layout,null);
            tabLayout = (TabLayout) x.findViewById(R.id.tabs);
            viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        myAdapter =new MyAdapter(getChildFragmentManager());
        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(myAdapter);

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                    tabLayout.setupWithViewPager(viewPager);

                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Pacifico.ttf");
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    TextView tv = new TextView(getActivity());
                   tv.setGravity(Gravity.CENTER );
                    tv.setTextColor(Color.WHITE);
                    tv.setText(myAdapter.getPageTitle(i));
                    tv.setTypeface(tf);
                    tabLayout.getTabAt(i).setCustomView(tv);
                }
                   }
        });



        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case 0 : return new DashboardFragment();
              case 1 : return new LoginFragment();
              case 2 : return new UpdatesFragment();
          }
        return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Primary";
                case 1 :
                    return "Social";
                case 2 :
                    return "Updates";
            }
                return null;
        }
    }

}
