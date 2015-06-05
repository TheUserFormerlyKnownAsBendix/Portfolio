package at.bendix.portfolio.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import at.bendix.portfolio.R;
import at.bendix.portfolio.adapter.AppAdapter;
import at.bendix.portfolio.data.App;
import at.bendix.portfolio.network.Fetcher;
import at.bendix.portfolio.network.callback.OnAppsLoadedCallback;
import at.bendix.portfolio.network.callback.OnErrorCallback;
import at.bendix.portfolio.widget.recyclerview.SpaceDecoration;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        recycler = (RecyclerView) findViewById(R.id.activity_main_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recycler.addItemDecoration(new SpaceDecoration(getResources().getDimensionPixelSize(R.dimen.margin)));

        Fetcher.fetchApps(this, new OnAppsLoadedCallback() {
            @Override
            public void onAppsLoaded(ArrayList<App> apps) {
                recycler.setAdapter(new AppAdapter(apps));
            }
        }, new OnErrorCallback() {
            @Override
            public void onError(Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_info) {
            startActivity(new Intent(this, InfoActivity.class));
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startIntent(String name) {
        if(!isAppInstalled(name)) {
            Toast.makeText(this, getResources().getString(R.string.action_start_app), Toast.LENGTH_SHORT).show();
        } else {
            try {
                Intent i = getPackageManager().getLaunchIntentForPackage(name);
                if (i != null) {
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isAppInstalled(String name) {
        try {
            getPackageManager().getPackageInfo(name, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
