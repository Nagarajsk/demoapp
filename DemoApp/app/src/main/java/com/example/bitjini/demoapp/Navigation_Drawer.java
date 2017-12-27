package com.example.bitjini.demoapp;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Navigation_Drawer extends AppCompatActivity implements View.OnClickListener {


    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_TEAM = "team";
    private static final String TAG_ENQUIRY = "enquiry";
    private static final String TAG_DEPARTMENTS = "departments";
    private static final String TAG_CONTACT = "contact";
    public static String CURRENT_TAG = TAG_HOME;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

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
        // Setting the content of layout your provided to the act_content frame
        getLayoutInflater().inflate(layoutResID, actContent, true);
        super.setContentView(fullLayout);
        setSupportActionBar(toolbar);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        mHandler = new Handler();


        // initializing navigation menu
        setUpNavigationView();

        //initialize variables
        initialize_variables();

        //Calling onclickListener
        onClickListeners();



    }//END OF ONCREATE

    public void initialize_variables(){
        toolbarLogin = (Button)findViewById(R.id.toolbar_login);
        toolbarRegister =(Button)findViewById(R.id.toolbar_register);
    }


    public void onClickListeners(){
        toolbarLogin.setOnClickListener(this);
        toolbarRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.toolbar_login :
                new Login().Show_Login_Dialog(Navigation_Drawer.this);
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
    }






    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect

        Runnable mPendingRunnable = new Runnable() {
            private Fragment homeFragment;

            public Fragment getHomeFragment() {
                return homeFragment;
            }

            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
//                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }


    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/main.html");
                        break;

                    case R.id.nav_team:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_TEAM;
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/doctors.html");
                        break;

                    case R.id.nav_enquiry:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_ENQUIRY;
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/appointment.html");
                        break;
                    case R.id.nav_departments:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_DEPARTMENTS;
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/departments");
                        break;
                    case R.id.nav_contact:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_CONTACT;
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/contact.html");
                        break;

                    default:
                        navItemIndex = 0;
                        break;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


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
    }




    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

}
