package com.yalantis.internship.requestapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
//look at issue please:)

public class RequestViewActivity extends AppCompatActivity {
    //array activity views
    //on click listener views. show toast with class simple name
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            Toast toast = Toast.makeText(getApplicationContext(), v.getClass().getSimpleName(), Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_layout);
        showImages();
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        ArrayList<View> views = new ArrayList<>();
        setupUpActionPanel();
        views = findViews(viewGroup, views);
        //add onclick listener event views in activity
        for (View v : views) {
            v.setOnClickListener(onClickListener);
        }
        setupUpActionPanel();
    }

    //find all views in view group
    private ArrayList<View> findViews(ViewGroup viewGroup, ArrayList<View> views) {
        for (int i = 0, N = viewGroup.getChildCount(); i < N; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                findViews((ViewGroup) child, views);
            } else {
                views.add(child);
            }
        }
        return views;
    }

    //set up actionbar(add home button, and title)
    private void setupUpActionPanel() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.request_number));
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "ImageButton", Toast.LENGTH_SHORT).show();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //recycleView  show
    private void showImages() {
        String[] pics_urls = getResources().getStringArray(R.array.pic_urls);
        ImagesViewAdapter imageViewAdapter = new ImagesViewAdapter(pics_urls);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.images_view);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(imageViewAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
