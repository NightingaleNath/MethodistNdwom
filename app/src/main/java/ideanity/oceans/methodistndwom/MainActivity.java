package ideanity.oceans.methodistndwom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.WindowManager;

import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ideanity.oceans.methodistndwom.fragments.CanticleFragment;
import ideanity.oceans.methodistndwom.fragments.ExtraFragment;
import ideanity.oceans.methodistndwom.fragments.HymnFragment;
import ideanity.oceans.methodistndwom.fragments.NdwomFragment;
import ideanity.oceans.methodistndwom.fragments.OrderFragment;

public class MainActivity extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    int color1 = 0;
    Class fragmentClass;
    public static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Methodist Hymn",R.drawable.news_bg));
        menuItems.add(new MenuItem("Methodist Ndwom",R.drawable.feed_bg));
        menuItems.add(new MenuItem("Canticles",R.drawable.message_bg));
        menuItems.add(new MenuItem("Extras",R.drawable.music_bg));
        menuItems.add(new MenuItem("Order of Service",R.drawable.music_bg));
        sNavigationDrawer.setMenuItemList(menuItems);

        fragmentClass =  HymnFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){
                    case 0:{
                        //color1 = R.color.red;
                        fragmentClass = HymnFragment.class;
                        break;
                    }
                    case 1:{
                        //color1 = R.color.orange;
                        fragmentClass = NdwomFragment.class;
                        break;
                    }
                    case 2:{
                        //color1 = R.color.green;
                        fragmentClass = CanticleFragment.class;
                        break;
                    }
                    case 3:{
                        //color1 = R.color.blue;
                        fragmentClass = ExtraFragment.class;
                        break;
                    }
                    case 4:{
                        //color1 = R.color.blue;
                        fragmentClass = OrderFragment.class;
                        break;
                    }
                }
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });

    }

}
