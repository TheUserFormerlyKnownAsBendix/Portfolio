package at.bendix.portfolio.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import at.bendix.portfolio.MainActivity;
import at.bendix.portfolio.R;
import at.bendix.portfolio.data.App;

/**
 * Created by bendix on 02/06/15.
 */
public class AppButton extends CardView {

    private static boolean initialized = false;
    private App app;

    private CardView card;
    private TextView text;

    public AppButton(Context context) {
        super(context);

        inflate(context, R.layout.widget_app_button, this);

        card = (CardView) findViewById(R.id.widget_app_button);
        text = (TextView) findViewById(R.id.widget_app_button_text);

        initialized = true;

        if(app != null) {
            setApp(app);
        }

    }

    public void setApp(App app) {
        this.app = app;
        if(initialized) {
            card.setCardBackgroundColor(app.getColor());

            text.setText(app.getName());
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getContext()).startIntent(AppButton.this.app.getPackageName());
                }
            });
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
