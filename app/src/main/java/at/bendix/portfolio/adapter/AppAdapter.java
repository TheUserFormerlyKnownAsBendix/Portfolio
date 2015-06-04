package at.bendix.portfolio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import at.bendix.portfolio.data.App;
import at.bendix.portfolio.widget.AppButton;

/**
 * Created by bendix on 02/06/15.
 */
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private ArrayList<App> apps;

    public AppAdapter(ArrayList<App> apps) {
        this.apps = apps;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new AppButton(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item.setApp(apps.get(position));
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public AppButton item;
        public ViewHolder(AppButton itemView) {
            super(itemView);
            this.item = itemView;
        }
    }

}
