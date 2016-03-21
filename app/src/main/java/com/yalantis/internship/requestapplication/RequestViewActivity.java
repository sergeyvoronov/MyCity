package com.yalantis.internship.requestapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestViewActivity extends AppCompatActivity {

  //array of urls images
  private final List<ImageModel> mImagesList = new ArrayList<>();
  //array activity views
  private final ArrayList<View> mViews = new ArrayList<>();


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_request_layout);
    setupUpActionPanel();
    showImages();

    //get activity viewgroup
    ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
    findViews(viewGroup);

    //add onclick listener views in activity
    for (View v : mViews) {
      v.setOnClickListener(onClickListener);
      Log.v(getString(R.string.app_name), getResources().getResourceName(v.getId()));
    }
  }

  //on click listener views. show toast with id name
  private final View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override public void onClick(final View v) {
      Pattern p = Pattern.compile(".*:id\\/(.*)");
      String s = getResources().getResourceName(v.getId());
      Matcher m = p.matcher(s);
      if (m.find()) s = m.group(1);
      Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
      toast.show();
    }
  };

 //find all views in view group
  private void findViews(ViewGroup viewGroup) {
    for (int i = 0, N = viewGroup.getChildCount(); i < N; i++) {
      View child = viewGroup.getChildAt(i);
      if (child instanceof ViewGroup) {
        findViews((ViewGroup) child);
      } else {
        mViews.add(child);
      }
    }
  }

  //set up actionbar(add home button, and title)
  private void setupUpActionPanel() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar!=null) {
      actionBar.setHomeButtonEnabled(true);
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
      actionBar.setTitle(getString(R.string.request_number));
    }
  }

  //home(up) event action
  @Override public boolean onOptionsItemSelected(MenuItem item) {
    finish();
    return super.onOptionsItemSelected(item);
  }
  //recycleView  show
  private void showImages() {
    String[] pics_urls = getResources().getStringArray(R.array.pic_urls);
    for (String u : pics_urls) {
      mImagesList.add(new ImageModel(u));
    }

    ImagesViewAdapter imageViewAdapter = new ImagesViewAdapter(mImagesList);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.images_view);
    LinearLayoutManager linearLayoutManager =
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

    recyclerView.setAdapter(imageViewAdapter);
    recyclerView.setLayoutManager(linearLayoutManager);
  }
}
