package com.william.thermometer;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * 主类，负责导航窗格和Fragment的协调
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar myToolbar;
    DrawerLayout myDrawer;
    ActionBarDrawerToggle myDrawerViewToggle;
    NavigationView myNavigationView;
    FloatingActionButton myFloatingActionButton;

    FragmentManager fragmentManager = getSupportFragmentManager();


    private boolean isBandOpen = true;
    private boolean isBlueToothOpen = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*将首页Fragment设为默认显示*/
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().add(R.id.frame_content, new MainPageFragment()).commit();
        }

        /*设置toolbar*/
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        /*设置floatingactionbutton*/
//        myFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
//        myFloatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "没有数据可以编辑", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        /*设置抽屉和导航窗格*/
        myDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        myDrawerViewToggle = new ActionBarDrawerToggle(
                this, myDrawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        myDrawer.addDrawerListener(myDrawerViewToggle);//添加监视器
        myDrawerViewToggle.syncState();//同步两个的状态

        myNavigationView = (NavigationView) findViewById(R.id.nav_view);
        myNavigationView.setNavigationItemSelectedListener(this);

//
//        FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.floatingActionMenu);

        com.getbase.floatingactionbutton.FloatingActionButton floatingActionButton = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.floatingActionButton1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"蓝牙已"+(isBlueToothOpen?"关闭":"打开"),Snackbar.LENGTH_SHORT).show();

                isBlueToothOpen=!isBlueToothOpen;
            }
        });


        com.getbase.floatingactionbutton.FloatingActionButton floatingActionButton2 = (com.getbase.floatingactionbutton.FloatingActionButton) findViewById(R.id.floatingActionButton2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"手环已"+(isBandOpen?"关闭":"打开"),Snackbar.LENGTH_SHORT).show();
                isBandOpen=!isBandOpen;
            }
        });
    }

    /*返回按钮，是否退出主程序*/
    @Override
    public void onBackPressed() {

        if (myDrawer.isDrawerOpen(GravityCompat.START)) {
            myDrawer.closeDrawer(GravityCompat.START);
        } else {
            doExitApp();
        }
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    /*初始化右上方设置按钮*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_main, menu);
        return true;
    }


    /*右上方按钮所绑定的行动*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_content, new SettingsFragment())
                        .commit();
                myToolbar.setTitle("设置");
                break;
            case R.id.action_share:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_content, new ShareFragment())
                        .commit();
                myToolbar.setTitle("分享");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /*导航框体元素选中*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {
            case R.id.nav_mainPage:
                fragmentManager
                        .beginTransaction()
//                        .addToBackStack(null)
                        .replace(R.id.frame_content, new MainPageFragment())
                        .commit();
                myToolbar.setTitle("首页-监测体温");
                break;
            case R.id.nav_findBand:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_content, new FindBandFragment())
                        .commit();
                myToolbar.setTitle("查找手环");
                break;
            case R.id.nav_showBandInfor:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_content, new ShowBandInforFragment())
                        .commit();
                myToolbar.setTitle("显示手环信息");
                break;
            case R.id.nav_addPatient:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_content, new AddPatientFragment())
                        .commit();
                myToolbar.setTitle("录入患者信息");
                break;
            case R.id.nav_editPatient:
                new AlertDialog.Builder(this).setTitle("抱歉").setMessage("没有数据可以编辑").show();
                break;
            case R.id.nav_settings:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_content, new SettingsFragment())
                        .commit();
                myToolbar.setTitle("设置");
                break;
            case R.id.nav_share:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_content, new ShareFragment())
                        .commit();
                myToolbar.setTitle("分享");
                break;
            default:
                break;
        }

        myDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
