package com.army.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.army.R;
import com.army.database.DatabaseHelper;
import com.army.fragment.HomeFragment;

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
    @BindView(R.id.txt_actiontitle)
    TextView txtActiontitle;
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


    @BindView(R.id.privecy)
    LinearLayout privecy;
    @BindView(R.id.termcondition)
    LinearLayout termcondition;
    @BindView(R.id.drawer)
    LinearLayout drawer;


    User user;

    public static HomeActivity homeActivity = null;
    public static TextView txtNoti;
    public static CustPrograssbar custPrograssbar;
    Fragment fragment1 = null;
    DatabaseHelper databaseHelper;
    SessionManager sessionManager;

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
    }

    public static HomeActivity getInstance() {
        return homeActivity;
    }

    public void showMenu() {
       // rltNoti.setVisibility(View.GONE);
        //rltCart.setVisibility(View.VISIBLE);
    }

    public void setFrameMargin(int top) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) lvlMainhome.getLayoutParams();
        params.setMargins(0, top, 0, 0);
        lvlMainhome.setLayoutParams(params);
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

       // txtMob.setText("" + user.getMobile());
        //txtEmail.setText("" + user.getEmail());

        titleChange();

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


        }
    }

    public void setdata() {

    }

    public void hideActionbar() {
        appBarLayout.setVisibility(View.GONE);
        drawer.setVisibility(View.GONE);
    }



    public void serchviewShow() {
        //lvlActionsearch.setVisibility(View.VISIBLE);
    }

    public void titleChange(String s) {
        txtActiontitle.setText(s);
    }

    public void titleChange() {
        //txtActiontitle.setText("Hello " + user.getName());
    }


    public void callFragment(Fragment fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentClass).addToBackStack("adds").commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }



    @SuppressLint("SetTextI18n")
    @OnClick({R.id.img_close, R.id.myprofile, R.id.myoder, R.id.address, R.id.logout, R.id.about, R.id.privecy, R.id.lvl_home, R.id.termcondition})
    public void onViewClicked(View view) {
        Fragment fragment;
        Bundle args;
        switch (view.getId()) {
            case R.id.img_close:
                break;
            case R.id.lvl_home:
                //lvlActionsearch.setVisibility(View.VISIBLE);
                titleChange();
                fragment = new HomeFragment();
                callFragment(fragment);
                break;
            case R.id.myprofile:
                titleChange();

                if (sessionManager.getBooleanData(login)) {
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));

                } else {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
                break;



            case R.id.logout:
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
                titleChange();

                startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
                break;
            case R.id.privecy:
                titleChange();

                startActivity(new Intent(HomeActivity.this, PrivacyPolicyActivity.class));
                break;
            case R.id.termcondition:
                titleChange();

                startActivity(new Intent(HomeActivity.this, TermsAndConditionActivity.class));
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
    }




}
