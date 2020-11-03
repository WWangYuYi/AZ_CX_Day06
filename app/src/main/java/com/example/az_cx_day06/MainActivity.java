package com.example.az_cx_day06;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.az_cx_day06.adapter.MyRcyAdapter;
import com.example.az_cx_day06.bean.FuLiBean;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int _postion;
    private RecyclerView rcy;
    //private List<String> list;
    private List<FuLiBean.ResultsBean> mList;
    private MyRcyAdapter myRcyAdapter;
    private final static String FULI_URL = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/3";
    private Toolbar tol;
    private LinearLayout ll;
    private NavigationView nav;
    private DrawerLayout dra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(FULI_URL).openConnection();
                    if (connection.getResponseCode() == 200) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuffer.append(line);
                        }
                        String json = stringBuffer.toString();
                        Log.i("TAG", "run: json" + json);
                        FuLiBean fuLiBean = new Gson().fromJson(json, FuLiBean.class);
                        List<FuLiBean.ResultsBean> results = fuLiBean.getResults();
                        mList.addAll(results);
//                        for (FuData.ResultsBean data: results) {
//                            list.add(data.getUrl());
//                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myRcyAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                OkHttpClient okHttpClient = new OkHttpClient();
//                Request.Builder builder = new Request.Builder();
//                builder.get().url(FULI_URL);
//                Request build = builder.build();
//                Call call = okHttpClient.newCall(build);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.e("TAG", "onFailure: 请求失败"+e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        ResponseBody body = response.body();
//                        String json = body.toString();
//                        FuLiBean fuLiBean = new Gson().fromJson(json, FuLiBean.class);
//                        List<FuLiBean.ResultsBean> results = fuLiBean.getResults();
//                        mList.addAll(results);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                myRcyAdapter.notifyDataSetChanged();
//                            }
//                        });
//                    }
//                });

            }
        }).start();
    }



    private void initView() {
        rcy = (RecyclerView) findViewById(R.id.rcy);
        tol = (Toolbar) findViewById(R.id.tol);
        setSupportActionBar(tol);
        rcy.setLayoutManager(new LinearLayoutManager(this));
//        rcy.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcy.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mList = new ArrayList<>();
        myRcyAdapter = new MyRcyAdapter(this, mList);
        rcy.setAdapter(myRcyAdapter);

        //单击修改
        myRcyAdapter.setSetOnItemClickListener(new MyRcyAdapter.setOnItemClickListener() {
            @Override
            public void setItemOnClick(int postion) {
                //String mNewData = list.get(postion);
                //Toast.makeText(MainActivity.this, "修改"+mNewData, Toast.LENGTH_SHORT).show();
                myRcyAdapter.notifyDataSetChanged();
            }
        });

        //长按弹出上下文菜单
        myRcyAdapter.setSetItemLongClickListener(new MyRcyAdapter.setItemLongClickListener() {
            @Override
            public void setItemLongClick(int postion) {
                _postion = postion;
//                mList.remove(postion);
//                myRcyAdapter.notifyDataSetChanged();
            }
        });

        //给RecyclerView注册上下文
        registerForContextMenu(rcy);

        ll = (LinearLayout) findViewById(R.id.ll);
        nav = (NavigationView) findViewById(R.id.nav);
        dra = (DrawerLayout) findViewById(R.id.dra);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dra, tol, 0, 0);
        dra.addDrawerListener(toggle);
        toggle.syncState();
        nav.setItemIconTintList(null);
        dra.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                ll.setX(drawerView.getWidth()*slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menu_music:
                Toast.makeText(this, (String)item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, 1, "修改");
        menu.add(2, 2, 1, "删除");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //修改
            case 1:
                View view_update = LayoutInflater.from(MainActivity.this).inflate(R.layout.update_data, null);
                final EditText et_input = view_update.findViewById(R.id.et_input);
                final String newData = et_input.getText().toString().trim();
                et_input.setText(mList.get(_postion).getDesc());
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setMessage("删除或者修改")
                        .setView(view_update)
                        .setPositiveButton("取消", null)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                FuLiBean.ResultsBean resultsBean = mList.get(_postion);
                                String data = et_input.getText().toString().trim();
                                mList.get(_postion).setDesc(data);
                                myRcyAdapter.notifyDataSetChanged();
                            }
                        }).show();
                break;
            case 2:
                //删除
                mList.remove(_postion);
                myRcyAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}