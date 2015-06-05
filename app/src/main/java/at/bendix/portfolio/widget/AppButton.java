package at.bendix.portfolio.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import at.bendix.portfolio.activity.MainActivity;
import at.bendix.portfolio.data.App;

/**
 * Created by bendix on 02/06/15.
 */
public class AppButton extends Button {

    private static boolean initialized = false;
    private App app;

    public AppButton(Context context) {
        super(context);

        setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setTextColor(Color.WHITE);

        initialized = true;

        if(app != null) {
            setApp(app);
        }

    }

    public void setApp(App app) {
        this.app = app;
        if(initialized) {
            setBackgroundColor(app.getColor());

            setText(app.getName());
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getContext()).startIntent(AppButton.this.app.getPackageName());
                }
            });
        }
    }

}
