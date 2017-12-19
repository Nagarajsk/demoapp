package com.example.bitjini.demoapp.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.bitjini.demoapp.R;
import com.example.bitjini.demoapp.fragment.HomeFragment;
import com.example.bitjini.demoapp.fragment.MoviesFragment;
import com.example.bitjini.demoapp.fragment.NotificationsFragment;
import com.example.bitjini.demoapp.fragment.PhotosFragment;
import com.example.bitjini.demoapp.fragment.SettingsFragment;
import com.example.bitjini.demoapp.other.CircleTransform;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.security.MessageDigest;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener,
        MoviesFragment.OnFragmentInteractionListener,
        NotificationsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    //private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
   // private FloatingActionButton fab;

    //These elements for Login details
    Button toolbar_loginbtn;
    TextView login_title;
    EditText login_email,login_password;
    CheckBox login_checkbox;
    Button login_button;//used ,after filling the form
    ImageButton login_close_button;

    //These elements for Registration details
    Button toolbar_registerbtn;
    TextView register_title;
    EditText register_user_name, register_email,register_password;
    CheckBox register_checkbox;
    Button register_button;//used ,after filling the form
    ImageButton register_close_button;


    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};



    //Recycler view
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;


    String[] title;
    int [] Img_res = {R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4,R.drawable.icon_5};
    ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();




    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_TEAM = "team";
    private static final String TAG_ENQUIRY = "enquiry";
    private static final String TAG_DEPARTMENTS = "departments";
    private static final String TAG_CONTACT = "contact";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //fab = (FloatingActionButton) findViewById(R.id.fab);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
       // imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
       // imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);



        //Downcast the login related elements
        toolbar_loginbtn = (Button)findViewById(R.id.login);
        login_email = (EditText)findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        login_checkbox =(CheckBox)findViewById(R.id.login_checkbox);
        login_close_button = (ImageButton)findViewById(R.id.login_close_button);



        //Downcast the Registration related elements
        toolbar_registerbtn = (Button)findViewById(R.id.register);
        register_user_name = (EditText)findViewById(R.id.User_name);
        register_email =(EditText)findViewById(R.id.register_email);
        register_password =(EditText)findViewById(R.id.register_password);
        register_checkbox =(CheckBox)findViewById(R.id.register_checkbox);
        register_close_button =(ImageButton)findViewById(R.id.register_close_button);


        final Dialog login_dialog = new Dialog(MainActivity.this);
        //To open a dialog window on click of login button in toolbar
        toolbar_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
                login_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                login_dialog.setContentView(R.layout.login_details);
                login_dialog.show();


            }
        });

//        //To Close Login Dialog
//        login_close_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login_dialog.dismiss();
//            }
//        });


        //To open a dialog window on click of register button in toolbar
        toolbar_registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog register_dialog = new Dialog(MainActivity.this);
                register_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                register_dialog.setContentView(R.layout.registration_details);
                register_dialog.show();
            }
        });



//        //Onclick of C P Pandey & Co.  back to home page
//        public void BacktoHome(View v)
//        {
//            Intent in=new Intent();
//            in.setClass(this,Third.class);
//            startActivity(in);
//        }

        //Carousel view
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

/**********************************RecyclerView***************************/
        recyclerView = (RecyclerView)findViewById(R.id.cardList);
        title = getResources().getStringArray(R.array.cardview_title);

        int i= 0;

        for (String name : title)
        {
            DataProvider dataprovider = new DataProvider(Img_res[i],name);
            arrayList.add(dataprovider);
            i++;
        }

        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
/**********************************RecyclerView***************************/

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
//                Toast.makeText(MainActivity.this, "Position :"+position, Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0 :
                       gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/appointment.html");
                       break;

                    case 1 :
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/doctors.html");
                        break;

                    case 2:
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/services.html");
                        break;

                    case 3:
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/locations.html");
                        break;

                    case 4:
                        gotoIntent("http://demo.technowebmart.com/pandeyji_mob_app/contact.html");
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }// EnD OF ONCREATE()

    public void gotoIntent(String url){
        Intent intent = new Intent(MainActivity.this, Webview_activity.class);
        intent.putExtra("URL",url);
        startActivity(intent);
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }





    //This is called for CarouselView of images
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        txtName.setText("Nagaraj S K");
        txtWebsite.setText("www.bitjini.com");


    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
//        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
//            drawer.closeDrawers();
//
//            // show or hide the fab button
//            toggleFab();
//            return;
//        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // photos
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;
            case 2:
                // movies fragment
                MoviesFragment moviesFragment = new MoviesFragment();
                return moviesFragment;
            case 3:
                // notifications fragment
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                return notificationsFragment;

            case 4:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
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
                        break;
                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_TEAM;
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_ENQUIRY;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_DEPARTMENTS;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_CONTACT;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_logout) {
//            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
//            return true;
//        }

        // user is in notifications fragment
        // and selected 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        // user is in notifications fragment
        // and selected 'Clear All'
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

  //dfhjdhgd
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}