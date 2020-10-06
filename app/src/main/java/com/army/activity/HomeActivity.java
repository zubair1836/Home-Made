package com.army.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.army.R;
import com.army.database.DatabaseHelper;
import com.army.fragment.AboutUsFragment;
import com.army.fragment.HomeFragment;

import com.army.fragment.ItemsFragment;
import com.army.fragment.MealFragment;
import com.army.fragment.PrivacyPolicyFragment;
import com.army.fragment.ProfileFragment;
import com.army.fragment.TermsAndConditionFragment;
import com.army.model.User;
import com.army.utils.CustPrograssbar;
import com.army.utils.SessionManager;
import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.army.utils.SessionManager.login;


public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.lvl_home)
    LinearLayout lvlHome;
    @BindView(R.id.lvl_mainhome)
    LinearLayout lvlMainhome;

    @BindView(R.id.txt_logintitel)
    TextView txtLogintitel;
    @BindView(R.id.fragment_frame)
    FrameLayout fragmentFrame;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.txt_mob)
    TextView txtMob;
    @BindView(R.id.txtfirstl)
    TextView txtfirstl;


    @BindView(R.id.txt_email)
    TextView txtEmail;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.myprofile)
    LinearLayout myprofile;


    @BindView(R.id.logout)
    LinearLayout logout;


    @BindView(R.id.privacy)
    LinearLayout privecy;
    @BindView(R.id.termcondition)
    LinearLayout termcondition;
    @BindView(R.id.drawer)
    LinearLayout drawer;
    @BindView(R.id.btnItems)
    Button btnItems;
    @BindView(R.id.btnMeals)
    Button btnMeals;


    public static FragmentManager fragmentManager;
    public static TextView txtActiontitle;


    User user;

    public static HomeActivity homeActivity = null;
    public static TextView txtNoti;
    public static CustPrograssbar custPrograssbar;
    Fragment fragment1 = null;
    DatabaseHelper databaseHelper;
    SessionManager sessionManager;

    public static LinearLayout topLyt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        custPrograssbar = new CustPrograssbar();
        databaseHelper = new DatabaseHelper(HomeActivity.this);
        sessionManager = new SessionManager(HomeActivity.this);
        // user = sessionManager.getUserDetails("");
        homeActivity = this;
        setDrawer();

        topLyt = findViewById(R.id.topLayout);
        txtActiontitle = findViewById(R.id.txt_actiontitle);
        fragmentManager = getSupportFragmentManager();

    }

    public static HomeActivity getInstance() {
        return homeActivity;
    }

    public void showMenu() {
        // rltNoti.setVisibility(View.GONE);
        //rltCart.setVisibility(View.VISIBLE);
    }


    @SuppressLint("SetTextI18n")
    private void setDrawer() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_icon);
        //char first = user.getName().charAt(0);
        //Log.e("first", "-->" + first);
        //txtfirstl.setText("" + first);

        txtMob.setText("Number here");
        txtEmail.setText("email here");


        Cursor res = databaseHelper.getAllData();

        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack("HomePage").commit();
        if (sessionManager.getBooleanData(login)) {
            txtLogintitel.setText("Logout");
        } else {
            txtLogintitel.setText("Login");

        }
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
            if (fragment instanceof HomeFragment && fragment.isVisible()) {
                finish();
            }
            else {
                topLyt.setVisibility(View.GONE);

                titleChange("Home");
                fragment = new HomeFragment();
                callFragment(fragment);
            }

        }
    }

    public void setdata() {

    }

    public void hideActionbar() {
        appBarLayout.setVisibility(View.GONE);
        drawer.setVisibility(View.GONE);
    }


    public static void titleChange(String s) {
        txtActiontitle.setText(s);
    }


    public void callFragment(Fragment fragmentClass) {
        fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentClass).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    @SuppressLint("SetTextI18n")
    @OnClick({R.id.img_close, R.id.myprofile, R.id.myoder, R.id.address, R.id.logout, R.id.about, R.id.privacy, R.id.lvl_home, R.id.termcondition})
    public void onViewClicked(View view) {
        Fragment fragment;
        Bundle args;
        switch (view.getId()) {
            case R.id.img_close:
                break;
            case R.id.lvl_home:
                topLyt.setVisibility(View.GONE);

                titleChange("Home");
                fragment = new HomeFragment();
                callFragment(fragment);
                break;
            case R.id.myprofile:
                topLyt.setVisibility(View.GONE);

                titleChange("Profile");
                fragment = new ProfileFragment();
                callFragment(fragment);

                break;


            case R.id.logout:
                topLyt.setVisibility(View.GONE);

                if (sessionManager.getBooleanData(login)) {
                    sessionManager.logoutUser();
                    databaseHelper.deleteCard();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
                }
                break;


            case R.id.about:
                topLyt.setVisibility(View.GONE);

                titleChange("About Us");

                fragment = new AboutUsFragment();
                callFragment(fragment);
                break;

            case R.id.privacy:
                topLyt.setVisibility(View.GONE);

                titleChange("Privacy Policy");
                fragment = new PrivacyPolicyFragment();
                callFragment(fragment);
                break;
            case R.id.termcondition:
                topLyt.setVisibility(View.GONE);

                titleChange("Terms and Conditions");
                fragment = new TermsAndConditionFragment();
                callFragment(fragment);
                break;


            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void ShowItemsFrag(View view) {

        btnMeals.setBackground(getDrawable(R.drawable.rounded_editetext));
        btnItems.setBackground(getDrawable(R.drawable.rounded_btn_filled));

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
        if (!(fragment instanceof ItemsFragment && fragment.isVisible())) {
            fragment = new ItemsFragment();
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                    .replace(R.id.fragment_frame, fragment).commit();
        }





    }

    public void showMealsFrag(View view) {

        btnItems.setBackground(getDrawable(R.drawable.rounded_editetext));
        btnMeals.setBackground(getDrawable(R.drawable.rounded_btn_filled));


        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
        if (!(fragment instanceof MealFragment && fragment.isVisible())) {
            fragment = new MealFragment();
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                    .replace(R.id.fragment_frame, fragment).commit();
        }

    }


}
