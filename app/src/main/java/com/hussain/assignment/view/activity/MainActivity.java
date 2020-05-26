package com.hussain.assignment.view.activity;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hussain.assignment.R;
import com.hussain.assignment.model.Dogs;

import com.hussain.assignment.network.RequestGenerator;
import com.hussain.assignment.view.adapter.GeneratedListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    //views
    private TextView mTvTestingAPiResponse;
    private Button mBtnRequest;
    private Button mShowCachedImages;
    private ImageView mImageView;
    //
    private GeneratedListAdapter mGeneratedListAdapter;
    private List<String> mUrlList;
    private RecyclerView mKidMoviesRecyclerView;

    //Network layer related
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Object initialization
        objectInitialization();
        // Initialize view
        viewInitialization();
        ///Adapter initialization
        adapterInitialization();
        ///Set click listener
        setClickListenerOnViews();

//        mBtnRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callRandomImageAPIThroughVolley();
//                //callRandomImageAPIThroughRetrofit();
//            }
//        });
//
//        mShowCachedImages.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mUrlList != null && !mUrlList.isEmpty()) {
//                    mKidMoviesRecyclerView.setVisibility(View.VISIBLE);
//                    mGeneratedListAdapter.setList(mUrlList);
//                }
//            }
//        });
    }


    private void objectInitialization() {
        mUrlList = new ArrayList<>();
    }

    private void viewInitialization() {
        mTvTestingAPiResponse = findViewById(R.id.tv_testing);
        mBtnRequest = (Button) findViewById(R.id.btn_send);
        mShowCachedImages = (Button) findViewById(R.id.btn_view_list);
        mImageView = findViewById(R.id.iv_sample);
        mKidMoviesRecyclerView = findViewById(R.id.recycler_image_list);
        mKidMoviesRecyclerView.setVisibility(View.VISIBLE);
    }

    private void adapterInitialization() {
        mGeneratedListAdapter = new GeneratedListAdapter(this, mUrlList);
        mKidMoviesRecyclerView.setAdapter(mGeneratedListAdapter);
        mKidMoviesRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    private void setClickListenerOnViews() {
        mBtnRequest.setOnClickListener(this);
        mShowCachedImages.setOnClickListener(this);
    }


    /**
     * Gets Random Image API response through retrofit
     */
    //TODO: Network layer needs to be modularized. JSON response have some issues with retrofit,
    // so moved to Volley for network call
    private void callRandomImageAPIThroughRetrofit() {

        RequestGenerator.getInstance().create().getRandomDogs().enqueue(
                new Callback<Dogs>() {
                    @Override
                    public void onResponse(Call<Dogs> call, retrofit2.Response<Dogs> response) {

                        if (response != null && response.body() != null) {
                            Dogs randomImagesModel = response.body();
                            Log.e(TAG, randomImagesModel.getStatus());
                            Log.e(TAG, randomImagesModel.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<Dogs> call, Throwable t) {
                        Log.e(TAG, "ERROR:: " + t.getLocalizedMessage());
                    }
                });
    }


    /**
     * Gets Random Image API response through Volley
     */
    //TODO: Network layer needs to be modularized. JSON response have some issues with retrofit,
    // so moved to Volley for network call
    private void callRandomImageAPIThroughVolley() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET,
                "https://dog.ceo/api/breeds/image/random",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonobject = new JSONObject(response);
                            String value = jsonobject.getString("message");
                            Log.e(TAG, value);

                            if (null != value) {
                                //LRU cache maintained by glide lib
                                Glide.with(MainActivity.this)
                                        .load(value)
                                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                        .placeholder(R.drawable.movie_place_holder)
                                        .into(mImageView);
                                //Add image urls to List
                                mUrlList.add(value);
                            }
                        } catch (JSONException e) {
                            Log.d(TAG, e.getMessage());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Response :" + error.getLocalizedMessage(),
                        Toast.LENGTH_LONG).show();//display the response on screen
                Log.e(TAG, "ERROR:: " + error.getLocalizedMessage());
            }
        }
        );

        mRequestQueue.add(mStringRequest);
    }


    @Override
    public void onClick(@NonNull View v) {

        switch (v.getId()) {
            case R.id.btn_send:
                callRandomImageAPIThroughVolley();
                //callRandomImageAPIThroughRetrofit();
                break;

            case R.id.btn_view_list:
                if (mUrlList != null && !mUrlList.isEmpty()) {
                    mKidMoviesRecyclerView.setVisibility(View.VISIBLE);
                    mGeneratedListAdapter.setList(mUrlList);
                }
                break;


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Release object for memory management
        releaseMemory();
    }

    private void releaseMemory() {

        mTvTestingAPiResponse = null;
        mBtnRequest = null;
        mShowCachedImages = null;
        mImageView = null;
        mGeneratedListAdapter = null;
        mUrlList = null;
        mKidMoviesRecyclerView = null;
        //Network layer related
        mRequestQueue = null;
        mStringRequest = null;
    }


}
