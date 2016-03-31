package com.yalantis.internship.requestapplication;

import com.bumptech.glide.Glide;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class ImagesViewAdapter extends RecyclerView.Adapter<ImagesViewAdapter.ImageHolder> {

    private final String[] imagesDataSet;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ImagesViewAdapter(String[] imagesDataSet) {
        this.imagesDataSet = imagesDataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.request_images_layout, parent, false);
        return new ImageHolder(itemView); //[Comment] Wrong formatting
    }

    @Override
    public void onBindViewHolder(final ImageHolder holder, int position) {
        final String imageUrl = imagesDataSet[position];

        Resources r = holder.imageView.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, r.getDimension(R.dimen.image_size), r.getDisplayMetrics());
        Glide.with(holder.imageView.getContext())
                .load(imageUrl)
                .override(px, px)
                .centerCrop()
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(holder.imageView.getContext(),
                        v.getClass().getSimpleName(),
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesDataSet.length;
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView; //[Comment] Wrong visibility modifier

        public ImageHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imgRequest);
        }
    }
}