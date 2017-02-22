package com.fz.mybaisi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fz.mybaisi.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowImageAndGifActivity extends AppCompatActivity {
    @BindView(R.id.iv_photo)
    PhotoView ivPhoto;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_and_gif);
        ButterKnife.bind(this);

        url = getIntent().getStringExtra("url");
        final PhotoViewAttacher attacher = new PhotoViewAttacher(ivPhoto);

        Picasso.with(this)
                .load(url)
                .into(ivPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        attacher.update();
                    }

                    @Override
                    public void onError() {
                    }
                });

    }
}
