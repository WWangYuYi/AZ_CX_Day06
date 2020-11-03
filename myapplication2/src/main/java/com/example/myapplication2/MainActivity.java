package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication2.fragment.HomeFragment;
import com.example.myapplication2.fragment.KnowledgeFragment;
import com.example.myapplication2.fragment.NaviagtionFragment;
import com.example.myapplication2.fragment.PeopleFragment;
import com.example.myapplication2.fragment.ProjectFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TextView tol_title;
    private Toolbar tol;
    private FrameLayout fra;
    private TabLayout tab;
    private LinearLayout ll;
    private NavigationView nav;
    private DrawerLayout dra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tol_title = (TextView) findViewById(R.id.tol_title);
        tol = (Toolbar) findViewById(R.id.tol);
        fra = (FrameLayout) findViewById(R.id.fra);
        tab = (TabLayout) findViewById(R.id.tab);
        ll = (LinearLayout) findViewById(R.id.ll);
        nav = (NavigationView) findViewById(R.id.nav);
        dra = (DrawerLayout) findViewById(R.id.dra);
        //支持ToolBar
        setSupportActionBar(tol);
        //侧滑页面menu的图标正常显示
        nav.setItemIconTintList(null);
        //toolbar的开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dra, tol, 0, 0);
        dra.addDrawerListener(toggle);
        toggle.syncState();
        //
        //initFra();
        //initTab();
    }

    private void initTab() {

    }

    private void initFra() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        KnowledgeFragment knowledgeFragment = new KnowledgeFragment();
        NaviagtionFragment naviagtionFragment = new NaviagtionFragment();
        PeopleFragment peopleFragment = new PeopleFragment();
        ProjectFragment projectFragment = new ProjectFragment();
        transaction.add(R.id.fra,homeFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_music:
                startActivity(new Intent(MainActivity.this,MusicMainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
