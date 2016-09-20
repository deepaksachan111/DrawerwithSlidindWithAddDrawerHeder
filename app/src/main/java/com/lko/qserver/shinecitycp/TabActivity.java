package com.lko.qserver.shinecitycp;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lko.qserver.shinecitycp.Fragment.LoginFragment;
import com.lko.qserver.shinecitycp.drawerwithswipetab.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {


    String  login_name;
    // private Toolbar toolbar;
    // private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.mipmap.home,
            R.mipmap.ic_tab_contacts, R.mipmap.key

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        viewPager = (ViewPager) findViewById(R.id.viewpager);



       /* TextView textView = (TextView)findViewById(R.id.tv_hometext);
        Typeface type = Typeface.createFromAsset(getAssets(), "regular.ttf");
        textView.setTypeface(type);*/

        if(login_name != null  ){
           // setupViewPager2(viewPager);

        }else {
            //sessionManager.logoutUser();
            setupViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);


        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.drawerwithswipe_tab_layout, tabLayout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.iv_tab);

            String names = "Login";
            if(tab.getText().equals(names)&&login_name != null){
                tabTextView.setText(login_name);
                tab.select();
            }else{
                tabTextView.setText(tab.getText());
            }

            // tabTextView.setText(tab.getText());
            imageView.setImageResource(tabIcons[i]);
            tab.setCustomView(relativeLayout);


            // setupTabIcons();
            // tab.select();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    /*  private void setupTabIcons() {
          tabLayout.getTabAt(0).setIcon(tabIcons[0]);
          tabLayout.getTabAt(1).setIcon(tabIcons[1]);
          tabLayout.getTabAt(2).setIcon(tabIcons[2]);
      }*/
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new LoginFragment(), "Home");
        adapter.addFrag(new DashboardFragment(), "AboutUs");
        adapter.addFrag(new LoginFragment(), "Login");

        viewPager.setAdapter(adapter);
    }

/*
    public void setupViewPager2(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "Home");
        adapter.addFrag(new AboutFragment(), "AboutUs");
        adapter.addFrag(new LoginWebView(), "User");

        viewPager.setAdapter(adapter);
    }
*/

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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
        if (id == android.R.id.home) {

            // startActivity(new Intent(this,HomeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideKeyboard(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
