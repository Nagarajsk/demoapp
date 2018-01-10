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

    private DrawerLayout drawer;
    NavigationView navigationView;

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
                Intent intent = new Intent(Navigation_Drawer.this,MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // set the new task and clear flags
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;

            case R.id.nav_team:
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/doctors.html",1);
                break;

            case R.id.nav_enquiry:
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/appointment.html",2);
                break;

            case R.id.nav_departments:
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/departments.html",3);
                break;

            case R.id.nav_contact:
                gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/contact.html",4);
                break;

            default:
                break;
        }

        //Closing drawer on item click
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }  //End of onNavigationItemSelected() Method


    public void gotoIntent(String url,int index){
        Intent intent = new Intent(this,Webview_activity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // set the new task and clear flags
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS |Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        intent.putExtra("URL",url);
        intent.putExtra("INDEX",index);
        finish();
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);

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