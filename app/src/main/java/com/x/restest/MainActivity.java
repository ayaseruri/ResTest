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
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int IMG_TYPE_SIMPLE = 0;
    private static final int IMG_TYPE_COMPLEX = 1;
    private static final int MAX_COUNT = 100;

    private int mImgType;
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

        mImgType = IMG_TYPE_SIMPLE;

        mImgView = (ImageView) findViewById(R.id.img);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgType = (mImgType == IMG_TYPE_SIMPLE ? IMG_TYPE_COMPLEX : IMG_TYPE_SIMPLE);
            }
        });
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
                    mImgView.setImageDrawable(getResources().getDrawable(
                            mImgType == IMG_TYPE_SIMPLE ? R.drawable.record_png : R.drawable.loading_png
                    ));
                }
                hint.append("png");
                break;
            case R.id.action_webp:
                for (int i = 0; i < MAX_COUNT; i ++) {
                    mImgView.setImageDrawable(getResources().getDrawable(
                            mImgType == IMG_TYPE_SIMPLE ? R.drawable.record_webp : R.drawable.loading_webp
                    ));
                }
                hint.append("webp");
                break;
            case R.id.action_svg:
                for (int i = 0; i < MAX_COUNT; i ++) {
                    Drawable drawable = VectorDrawableCompat.create(
                            getResources()
                            , mImgType == IMG_TYPE_SIMPLE ? R.drawable.record_svg : R.drawable.loading_svg
                            , getTheme());
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
