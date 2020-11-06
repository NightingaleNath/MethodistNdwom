package ideanity.oceans.methodistndwom.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

import ideanity.oceans.methodistndwom.fragments.CanticleFragment;
import ideanity.oceans.methodistndwom.R;
import ideanity.oceans.methodistndwom.fragments.ChoirAnthemFragment;
import ideanity.oceans.methodistndwom.fragments.CommunionFragment;
import ideanity.oceans.methodistndwom.fragments.ExtraFragment;
import ideanity.oceans.methodistndwom.fragments.HymnFragment;
import ideanity.oceans.methodistndwom.fragments.NdwomFragment;
import ideanity.oceans.methodistndwom.fragments.NoteFragment;
import ideanity.oceans.methodistndwom.fragments.OrderFragment;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    RelativeLayout navmethod, navndwom;
    FragmentManager fragmentManager;
    private static final String TAG = HomeActivity.class.getSimpleName();

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        View headerview = navigationView.getHeaderView(0);

       if(savedInstanceState == null)
       {
           try {
               navigationView.setCheckedItem(R.id.nav_ndwom);
               fragmentManager = getSupportFragmentManager();
               NdwomFragment ndwomFragment = new NdwomFragment();
               fragmentManager.beginTransaction().replace(R.id.frameLayout, ndwomFragment).commit();
               toolbar.setTitle(getString(R.string.asore_ndwom));
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment  fragment = null;
                switch (id){
                    case R.id.nav_ndwom:
                        fragment = new NdwomFragment();
                        //loadFragment(fragment);
                        toolbar.setTitle(getString(R.string.asore_ndwom));
                        break;
                    case R.id.nav_methodist:
                        fragment = new HymnFragment();
                        //loadFragment(fragment);
                        toolbar.setTitle(getString(R.string.methodist_hymn));
                        break;
                    case R.id.nav_canticles:
                        fragment = new CanticleFragment();
                        //loadFragment(fragment);
                        toolbar.setTitle(getString(R.string.canticles));
                        break;
                    case R.id.nav_holy:
                        fragment = new CommunionFragment();
                        //loadFragment(fragment);
                        toolbar.setTitle(getString(R.string.holy_communion));
                        break;
                    case R.id.nav_order:
                        fragment = new OrderFragment();
                        //loadFragment(fragment);
                        toolbar.setTitle(getString(R.string.order_of_service));
                        break;
                        case R.id.nav_extras:
                        fragment = new ExtraFragment();
                        //loadFragment(fragment);
                        toolbar.setTitle(getString(R.string.extra));
                        break;
                    case R.id.nav_choir:
                        fragment = new ChoirAnthemFragment();
                        //loadFragment(fragment);
                        toolbar.setTitle(getString(R.string.ghana_choir_anthem));
                        break;
                    case R.id.nav_bookmark:

                        break;
                    case R.id.nav_note:
                        fragment = new NoteFragment();
                        //loadFragment(fragment);
                        toolbar.setTitle(getString(R.string.all_notes));
                        break;
                    default:
                        break;
                }

                if (fragment != null)
                {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

                }else {
                    Log.e(TAG, "Error in creating fragment");

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int backCount = fragmentManager.getBackStackEntryCount();

        if(backCount > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

}
