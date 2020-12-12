package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {


    RecyclerView recyclerView;

    DrawerLayout drawerLayout;

    Toolbar t;
    ActionBarDrawerToggle  i ;
    NavigationView n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbarsync();

        recyclerView = (RecyclerView)this.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));


        n = this.findViewById(R.id.navigation);
        n.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home: Toast.makeText(MainActivity2.this,"Homme",Toast.LENGTH_SHORT).show();
                                    break;
                    case R.id.profile: Toast.makeText(MainActivity2.this,"Profile",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.android: Toast.makeText(MainActivity2.this,"Android",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting: Toast.makeText(MainActivity2.this,"Setting",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exit: Toast.makeText(MainActivity2.this,"Exit",Toast.LENGTH_SHORT).show();
                        break;

                }

                return false;
            }

        });
        getData();
    }

    private void toolbarsync (){
        t = this.findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)this.findViewById(R.id.drawer);
        setSupportActionBar(t);
        i = new ActionBarDrawerToggle(this, drawerLayout, t, R.string.app_name, R.string.app_name);
        i.syncState();


    }

    private void getData(){
        Call<Posts> call =  BloggerApi.getPostService().getPostList();
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                Posts posts = response.body();
                recyclerView.setAdapter(new CustomAdapter(MainActivity2.this,posts.getItems()));
                Toast.makeText(MainActivity2.this,"Response Received",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

                Toast.makeText(MainActivity2.this,"Error",Toast.LENGTH_SHORT).show();

            }
        });
    }


}