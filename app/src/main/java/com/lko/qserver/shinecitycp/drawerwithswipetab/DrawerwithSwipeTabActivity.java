package com.lko.qserver.shinecitycp.drawerwithswipetab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lko.qserver.shinecitycp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DrawerwithSwipeTabActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerwithswipetab);

      //  FontsOverride.setDefaultFont(this, "MONOSPACE", "Pacifico.ttf");

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
      //  toolbar.setTitle(Html.fromHtml("<small>YOUR TITLE</small>"));
        /**
         *Setup the DrawerLayout and NavigationView
         */

             mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
             mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;
                  //  getSupportActionBar().setTitle(Html.fromHtml("<small>YOUR TITLE</small>"));
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

             mFragmentManager = getSupportFragmentManager();
             mFragmentTransaction = mFragmentManager.beginTransaction();
             mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */
        View nav_header = LayoutInflater.from(this).inflate(R.layout.nav_header, null);
              mNavigationView.addHeaderView(nav_header);
        TextView user = (TextView)nav_header.findViewById(R.id.username);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome User",Toast.LENGTH_LONG).show();
            }
        });
             mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();



                 if (menuItem.getItemId() == R.id.nav_item_sent) {
                     FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                     fragmentTransaction.replace(R.id.containerView,new SentFragment()).commit();

                 }

                if (menuItem.getItemId() == R.id.nav_item_inbox) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
                }

                 if (menuItem.getItemId() == R.id.nav_item_bin) {
                     FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                     xfragmentTransaction.replace(R.id.containerView,new SentFragment()).commit();
                 }

                 return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */


                ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,

                R.string.app_name);

                mDrawerLayout.setDrawerListener(mDrawerToggle);

                mDrawerToggle.syncState();

    }
}