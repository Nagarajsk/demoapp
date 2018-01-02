package com.example.sahar.cppandey22;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DrawerBaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Button login,register;
    LinearLayout fullLayout;
    CoordinatorLayout actContent;


    @Override
    public void setContentView(final int layoutResId){
        fullLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        actContent = (CoordinatorLayout) fullLayout.findViewById(R.id.coordinator_layout);

        // Setting the content of layout
        getLayoutInflater().inflate(layoutResId, actContent, true);
        super.setContentView(fullLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView mTitle = (TextView) toolbar.findViewById(R.id.title);
        Typeface myFont = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        mTitle.setTypeface(myFont);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();
        initListener();

        //navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        login = (Button) fullLayout.findViewById(R.id.login);
        register = (Button) fullLayout.findViewById(R.id.register);
    }

    private void initListener() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_view);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handling navigation view item clicks here
        int id = item.getItemId();
        String url = "";

        switch (id) {
            case R.id.nav_home:
                url = "http://demo.technowebmart.com/pandeyji_mob_app/main.html";
                goToNewActivity(url);
                break;

            case R.id.nav_team:
                url = "http://demo.technowebmart.com/pandeyji_mob_app/doctors.html";
                goToNewActivity(url);
                break;

            case R.id.nav_enquiry:
                url = "http://demo.technowebmart.com/pandeyji_mob_app/apointment.html";
                goToNewActivity(url);
                break;

            case R.id.nav_departments:
                url = "http://demo.technowebmart.com/pandeyji_mob_app/departments.html";
                goToNewActivity(url);
                break;

            case R.id.nav_contact:
                url = "http://demo.technowebmart.com/pandeyji_mob_app/contact.html";
                goToNewActivity(url);
                break;

            default:
                url = "http://demo.technowebmart.com/pandeyji_mob_app/main.html";
                goToNewActivity(url);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_view);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void goToNewActivity(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        String url;
        switch (v.getId()) {
            case R.id.login:
                Log.e("LOGIN","login");
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                new LoginClass().showDialogContent(DrawerBaseActivity.this);
                break;

            case R.id.register:
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                new RegisterClass().showDialogContent(DrawerBaseActivity.this);
                break;
        }
    }
}