package com.example.bitjini.demoapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;


public class Navigation_Drawer extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the Pages
    private static final String TAG_HOME = "home";
    private static final String TAG_TEAM = "team";
    private static final String TAG_ENQUIRY = "enquiry";
    private static final String TAG_DEPARTMENTS = "departments";
    private static final String TAG_CONTACT = "contact";
    public static String CURRENT_TAG = TAG_HOME;

    private DrawerLayout drawer;
    private NavigationView navigationView;

    Toolbar toolbar;
    LinearLayout fullLayout;
    CoordinatorLayout actContent;
    Button toolbarLogin,toolbarRegister;

    @Override
    public void setContentView(final int layoutResID)
    {
        // Your base layout here
        fullLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_navigation_drawer, null);
        actContent = (CoordinatorLayout) fullLayout.findViewById(R.id.drawCordinate);
        // Setting the content of layout you provided to the act_content frame
        getLayoutInflater().inflate(layoutResID, actContent, true);
        super.setContentView(fullLayout);
        setSupportActionBar(toolbar);

        //initialize variables
        initialize_variables();

        // initializing navigation menu
        setUpNavigationView();

        //Calling onclickListener to Display Dialog Box
        onClickListeners();

        //To change the FONT FAMILY of toolbar text
        fontFamily();


    }//END OF ONCREATE

    public void initialize_variables(){

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        toolbarLogin = (Button)findViewById(R.id.toolbar_login);
        toolbarRegister =(Button)findViewById(R.id.toolbar_register);
    }


    public void onClickListeners(){
        toolbarLogin.setOnClickListener(this);
        toolbarRegister.setOnClickListener(this);
    }


    //OnClick Method to Display Dialog Box
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.toolbar_login :
                if (toolbarLogin.getText().toString().equals("Logout"))
                {
                    toolbarLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login,0,0,0);
                    toolbarLogin.setText("Login");
                    toolbarLogin.setPadding(0,0,35,0);
                    toolbarRegister.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"You are Logged out", Toast.LENGTH_LONG).show();
                }else{
                    new Login().Show_Login_Dialog(Navigation_Drawer.this);
                }
                break;

            case R.id.toolbar_register :
                new Register().Show_Register_Dialog(Navigation_Drawer.this);
                break;
            default:
                Toast.makeText(this,"No case Matched",Toast.LENGTH_SHORT).show();
                break;
        }

    }


    public void gotoIntent(String url){
        Intent intent = new Intent(this,Webview_activity.class);
        intent.putExtra("URL",url);
        startActivity(intent);
        finish();
    }

    private void setUpNavigationView() {

        ActionBarDrawerToggle actionBarDrawerToggle = new
                ActionBarDrawerToggle(this, drawer, toolbar,
                        R.string.openDrawer, R.string.closeDrawer) {

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                        super.onDrawerOpened(drawerView);
                    }
                };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

    }//End of setUpNavigationView() Method


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Check to see which item was being clicked and perform appropriate action
        switch (item.getItemId()) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.nav_home:
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                Intent intent = new Intent(Navigation_Drawer.this,MainActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_team:
                navItemIndex = 1;
                CURRENT_TAG = TAG_TEAM;
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/doctors.html");
                break;

            case R.id.nav_enquiry:
                navItemIndex = 2;
                CURRENT_TAG = TAG_ENQUIRY;
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/appointment.html");
                break;
            case R.id.nav_departments:
                navItemIndex = 3;
                CURRENT_TAG = TAG_DEPARTMENTS;
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/departments.html");
                break;
            case R.id.nav_contact:
                navItemIndex = 4;
                CURRENT_TAG = TAG_CONTACT;
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/contact.html");
                break;

            default:
                navItemIndex = 0;
                break;
        }

        //Closing drawer on item click
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }  //End of onNavigationItemSelected() Method


    //To change the FONT FAMILY of toolbar text
    public void fontFamily() {
        TextView textView = (TextView)findViewById(R.id.toolbar_title);
        Button toolbar_login = (Button)findViewById(R.id.toolbar_login);
        Button toolbar_register = (Button)findViewById(R.id.toolbar_register);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/poppins-v5-latin-regular.ttf");

        textView.setTypeface(typeface);
        toolbar_login.setTypeface(typeface);
        toolbar_register.setTypeface(typeface);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        super.onBackPressed();

    }

    final public void changeView(){
        toolbarLogin.setText("Logout");
        toolbarLogin.setCompoundDrawables(null,null,null,null);
        toolbarLogin.setPadding(0,0,0,0);
        toolbarRegister.setVisibility(GONE);
    }

}



/*
package com.example.bitjini.demoapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;


public class Navigation_Drawer extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the Pages
    private static final String TAG_HOME = "home";
    private static final String TAG_TEAM = "team";
    private static final String TAG_ENQUIRY = "enquiry";
    private static final String TAG_DEPARTMENTS = "departments";
    private static final String TAG_CONTACT = "contact";
    public static String CURRENT_TAG = TAG_HOME;

    private DrawerLayout drawer;
    private NavigationView navigationView;

    Toolbar toolbar;
    LinearLayout fullLayout;
    CoordinatorLayout actContent;
    Button toolbarLogin,toolbarRegister;

    @Override
    public void setContentView(final int layoutResID)
    {
        // Your base layout here
        fullLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_navigation_drawer, null);
        actContent = (CoordinatorLayout) fullLayout.findViewById(R.id.drawCordinate);
        // Setting the content of layout you provided to the act_content frame
        getLayoutInflater().inflate(layoutResID, actContent, true);
        super.setContentView(fullLayout);
        setSupportActionBar(toolbar);

        //initialize variables
        initialize_variables();

        // initializing navigation menu
        setUpNavigationView();

        //Calling onclickListener to Display Dialog Box
        onClickListeners();

        //To change the FONT FAMILY of toolbar text
        fontFamily();


    }//END OF ONCREATE

    public void initialize_variables(){

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        toolbarLogin = (Button)findViewById(R.id.toolbar_login);
        toolbarRegister =(Button)findViewById(R.id.toolbar_register);
    }


    private void setUpNavigationView() {

        ActionBarDrawerToggle actionBarDrawerToggle = new
                ActionBarDrawerToggle(this, drawer, toolbar,
                        R.string.openDrawer, R.string.closeDrawer) {

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                        super.onDrawerOpened(drawerView);
                    }
                };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(navItemIndex);

    }//End of setUpNavigationView() Method


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Check to see which item was being clicked and perform appropriate action
        switch (item.getItemId()) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.nav_home:
               // navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                if (navItemIndex!=0){
                    //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                    Intent intent = new Intent(Navigation_Drawer.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    break;
                }
                break;

            case R.id.nav_team:
                navItemIndex = 1;
                CURRENT_TAG = TAG_TEAM;
               // navigationView.setBackgroundColor(this.getResources().getColor(R.color.colorPrimary));
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/doctors.html");
                break;

            case R.id.nav_enquiry:
                navItemIndex = 2;
                CURRENT_TAG = TAG_ENQUIRY;
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/appointment.html");
                break;
            case R.id.nav_departments:
                navItemIndex = 3;
                CURRENT_TAG = TAG_DEPARTMENTS;
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/departments.html");
                break;
            case R.id.nav_contact:
                navItemIndex = 4;
                CURRENT_TAG = TAG_CONTACT;
                //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/contact.html");
                break;

            default:
                navItemIndex = 0;
                break;
        }

        //Closing drawer on item click
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }  //End of onNavigationItemSelected() Method


    public void gotoIntent(String url){
        Intent intent = new Intent(this,Webview_activity.class);
        intent.putExtra("URL",url);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        finish();
    }

    //Calling onclickListener to Display Dialog Box
    public void onClickListeners(){
        toolbarLogin.setOnClickListener(this);
        toolbarRegister.setOnClickListener(this);
    }


    //OnClick Method to Display Dialog Box
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.toolbar_login :
                if (toolbarLogin.getText().toString().equals("Logout"))
                {
                    toolbarLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login,0,0,0);
                    toolbarLogin.setText("Login");
                    toolbarLogin.setPadding(0,0,35,0);
                    toolbarRegister.setVisibility(View.VISIBLE);
                    Toast.makeText(this,"You are Logged out", Toast.LENGTH_LONG).show();
                }else{
                    new Login().Show_Login_Dialog(Navigation_Drawer.this);
                }
                break;

            case R.id.toolbar_register :
                new Register().Show_Register_Dialog(Navigation_Drawer.this);
                break;
            default:
                Toast.makeText(this,"No case Matched",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    //To change the FONT FAMILY of toolbar text
    public void fontFamily() {
        TextView textView = (TextView)findViewById(R.id.toolbar_title);
        Button toolbar_login = (Button)findViewById(R.id.toolbar_login);
        Button toolbar_register = (Button)findViewById(R.id.toolbar_register);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/poppins-v5-latin-regular.ttf");

        textView.setTypeface(typeface);
        toolbar_login.setTypeface(typeface);
        toolbar_register.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        super.onBackPressed();

    }

    final public void changeView(){
        toolbarLogin.setText("Logout");
        toolbarLogin.setCompoundDrawables(null,null,null,null);
        toolbarLogin.setPadding(0,0,0,0);
        toolbarRegister.setVisibility(GONE);
    }

}

 */
