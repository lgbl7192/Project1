package com.ocv.testproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ArrayList<Model> items = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //TODO Follow the instructions below to complete this project
        /*
            Take the parsed items in the Arraylist 'items' and display
            them in a list via a RecyclerView. Each item, when clicked,
            should go to a detail page with the ability to show all of
            said items details and images (if there aren't any images it should
            note that as well in the detail page)

            IMO: Though a bit outdated at this point, Vogella has nice
            Android Tutorials.

            http://www.vogella.com/tutorials/android.html
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context = this;

        //region AsyncTask
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try{
                    items = Parser.parse();
                }catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException f){
                    f.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                setupList();
            }
        }.execute();
        //endregion

    }

    private void setupList(){
        //TODO Code used to attach list to RecyclerView should go here
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MainAdapter(context,items);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
