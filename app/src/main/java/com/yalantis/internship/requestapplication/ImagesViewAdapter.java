package com.yalantis.internship.requestapplication;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImagesViewAdapter extends RecyclerView.Adapter<ImagesViewAdapter.ImageHolder> {

  private final List<ImageModel> mImages;

  public ImagesViewAdapter(List<ImageModel> images) {
    this.mImages = images; //[Comment] Without this
  }

  @Override

  public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.
        from(parent.getContext()).
        inflate(R.layout.request_images_layout, parent, false);

    return new ImageHolder(itemView); //[Comment] Wrong formatting
  }

  @Override public void onBindViewHolder(final ImageHolder holder, int position) {
    final ImageModel imageModel = mImages.get(position);

    Resources r = holder.imageView.getResources();
    int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics()); //[Comment] Magic number
    Glide.with(holder.imageView.getContext())
        .load(imageModel.getUrl())
        .override(px, px)
        .centerCrop()
        .into(holder.imageView);
    holder.imageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        Pattern p = Pattern.compile(".*:id\\/(.*)");
        String s = holder.imageView.getContext().getResources().getResourceName(v.getId());
        Matcher m = p.matcher(s);
        if (m.find()) s = m.group(1);
        Toast toast = Toast.makeText(holder.imageView.getContext(), s + "-" + imageModel.getUrl(),
            Toast.LENGTH_SHORT);
        toast.show();
      }
    });

    //  holder.imageView.setImageResource(imageModel.getId()); //[Comment] Commented code
  }

  @Override public int getItemCount() {
    return mImages.size();
  }

  public static class ImageHolder extends RecyclerView.ViewHolder {
    final ImageView imageView; //[Comment] Wrong visibility modifier

    public ImageHolder(View v) {
      super(v);
      imageView = (ImageView) v.findViewById(R.id.imgRequest);
    }
  }
}