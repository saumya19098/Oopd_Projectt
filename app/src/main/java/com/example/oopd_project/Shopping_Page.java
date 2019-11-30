package com.example.oopd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Shopping_Page extends AppCompatActivity {
    GridView gridView;
    String[] items = {"dress"};
    int[] images= {R.drawable.dress};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping__page);
        gridView=findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);

            TextView name= findViewById(R.id.fruits);
            //getting view in row_data
            ImageView image = view1.findViewById(R.id.images);

            name.setText(items[i]);
            image.setImageResource(images[i]);
            return view1;


        }
    }
}
