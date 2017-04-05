package com.x.restest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_COUNT = 100;
    private ImageView mImgView;

    static{
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImgView = (ImageView) findViewById(R.id.img);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        StringBuilder hint = new StringBuilder("开始加载").append(MAX_COUNT).append("次");


        long startTime = System.currentTimeMillis();
        switch (id) {
            case R.id.action_png:
                for (int i = 0; i < MAX_COUNT; i ++) {
                    mImgView.setImageDrawable(getResources().getDrawable(R.drawable.loading_png));
                }
                hint.append("png");
                break;
            case R.id.action_webp:
                for (int i = 0; i < MAX_COUNT; i ++) {
                    mImgView.setImageDrawable(getResources().getDrawable(R.drawable.loading_webp));
                }
                hint.append("webp");
                break;
            case R.id.action_svg:
                for (int i = 0; i < MAX_COUNT; i ++) {
                    Drawable drawable = VectorDrawableCompat.create(getResources(), R.drawable.loading_svg, getTheme());
                    mImgView.setImageDrawable(drawable);
                }
                hint.append("svg");
                break;
            default:
                break;
        }

        Snackbar.make(getWindow().getDecorView(), hint.append("。 共耗时: ").append(System.currentTimeMillis() - startTime)
                .toString(), Snackbar.LENGTH_LONG)
                .show();

        return super.onOptionsItemSelected(item);
    }
}
