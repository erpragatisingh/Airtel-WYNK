package com.droidrank.sample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.droidrank.sample.ImageUtils.ImageLoader;
import com.droidrank.sample.net.HttpGetRequest;

import java.util.ArrayList;

// https://b674d8f5.ngrok.io/fetch.php?offset=2
public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> imageList = new ArrayList<>();
    int loadImageIndex = 0;
    private Button previous, next;
    private ImageLoader imageLoader;
    private Activity activity;
    private ImageView displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        initView();
        if (Utility.isNetworkAvailable(activity)) {
            new HttpGetRequest().execute("http://b674d8f5.ngrok.io/fetch.php?offset=1");
        }
        //Utility.getResponse();

        imageLoader = new ImageLoader(this);
    }

    private void initView() {
        previous = (Button) findViewById(R.id.previous);
        //onclick of previous button should navigate the user to previous image
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageInHolder(loadImageIndex);
                loadImageIndex--;
            }
        });
        next = (Button) findViewById(R.id.next);
        //onclick of next button should navigate the user to next image
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageInHolder(loadImageIndex);
                loadImageIndex++;
                if (Utility.isNetworkAvailable(activity) && loadImageIndex > imageList.size()) {
                    new HttpGetRequest().execute("http://b674d8f5.ngrok.io/fetch.php?offset=" + imageList.size() + 1);
                }
            }
        });


    }

    private void showImageInHolder(int index) {
        displayImage = (ImageView) findViewById(R.id.imageview);
        if (imageList.size() >= index) {
            new HttpGetImageRequest().execute(index);

        }

    }

    private class HttpGetImageRequest extends AsyncTask<Integer, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Integer... params) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            Bitmap bitmap = null;
            try {
                bitmap = Utility.getResizedBitmap(Utility.getBitmapFromURL(imageList.get(params[0])), height, width);
            } catch (Exception e) {
                Utility.showToastMessage(activity, getString(R.string.error_mgs));
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (null != bitmap) {
                displayImage.setImageBitmap(bitmap);
            }
        }
    }
}
