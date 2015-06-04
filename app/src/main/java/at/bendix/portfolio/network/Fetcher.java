package at.bendix.portfolio.network;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import at.bendix.portfolio.data.App;
import at.bendix.portfolio.network.callback.OnAppsLoadedCallback;
import at.bendix.portfolio.network.callback.OnErrorCallback;

/**
 * Created by bendix on 03/06/15.
 */
public class Fetcher {

    private static String url = "http://nanodegree.dingbat.at/api/apps";

    public static class Task extends AsyncTask<String, Integer, ArrayList<App>> {

        private Activity context;
        private OnAppsLoadedCallback onAppsLoadedCallback;
        private OnErrorCallback onErrorCallback;

        public Task(Activity context, OnAppsLoadedCallback onAppsLoadedCallback, OnErrorCallback onErrorCallback) {
            this.context = context;
            this.onAppsLoadedCallback = onAppsLoadedCallback;
            this.onErrorCallback = onErrorCallback;
        }

        @Override
        protected void onPostExecute(final ArrayList<App> apps) {
            super.onPostExecute(apps);

            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(apps != null) {
                        Task.this.onAppsLoadedCallback.onAppsLoaded(apps);
                    } else {
                        Task.this.onErrorCallback.onError(new Exception("Something went terribly wrong."));
                    }
                }
            });
        }

        @Override
        protected ArrayList<App> doInBackground(String... strings) {

            ArrayList<App> apps = new ArrayList<App>();

            try {
                OkHttpClient ok = new OkHttpClient();
                Request request = new Request.Builder().url(Fetcher.url).get().build();
                Response response = ok.newCall(request).execute();
                String json = response.body().string();
                Log.d("", "Response: "+json);

                JSONObject obj = new JSONObject(json);
                JSONArray array = obj.getJSONArray("apps");
                for(int i = 0; i < array.length(); i++) {
                    JSONObject app = array.getJSONObject(i);
                    apps.add(new App(
                            Color.parseColor(app.getString("color")),
                            app.getString("name"),
                            app.getString("packageName")
                    ));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return apps;
        }

    }

    public static void fetchApps(Context context, OnAppsLoadedCallback onAppsLoaded, OnErrorCallback onErrorCallback) {
        Task task = new Task((Activity)context, onAppsLoaded, onErrorCallback);
        task.execute();
    }

}
