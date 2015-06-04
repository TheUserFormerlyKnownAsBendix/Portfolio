package at.bendix.portfolio.network.callback;

import java.util.ArrayList;

import at.bendix.portfolio.data.App;

/**
 * Created by bendix on 03/06/15.
 */
public interface OnAppsLoadedCallback {
    void onAppsLoaded(ArrayList<App> apps);
}
