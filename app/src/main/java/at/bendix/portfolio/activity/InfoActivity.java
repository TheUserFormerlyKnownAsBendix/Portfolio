package at.bendix.portfolio.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import at.bendix.portfolio.R;

public class InfoActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private TextView credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        toolbar = (Toolbar) findViewById(R.id.activity_info_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });

        credits = (TextView) findViewById(R.id.activity_info_credits);
        credits.setMovementMethod(LinkMovementMethod.getInstance());

    }

}
