package com.example.oopd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add_to_cart extends AppCompatActivity {

    String[] items = {"Broccoli    Rs 10","Pumpkin    Rs 10","Carrot    Rs 10","Mushroom    Rs 10","Capsicum    Rs 10","Onion    Rs 10","Tomato    Rs 10",
            "Corn    Rs 10","Green Beans    Rs 10","Garlic    Rs 10"};
    //int[] images = {R.drawable.dress,R.drawable.ic_launcher_background};
    //String[] price={"10","20","30","4","40","50","60","70","80","90"};
    Integer[] price_list = {1,2,3,4,5,6,7,8,9,10};
    TextView prices_text;


    private ArrayList<String> data = new ArrayList<String>();
    //private ArrayList<Integer> data2 = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        ListView lv = (ListView) findViewById(R.id.listview);
        //prices_text=findViewById(R.id.list_item_price);
        generateListContent();
        lv.setAdapter(new MyListAdaper(this, R.layout.list_item, data));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Add_to_cart.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();



            }
        });
    }

    private void generateListContent() {
        for(int i = 0; i < 10; i++) {
            data.add(items[i]);

            //data.add(price[i]);
            //prices_text.append(price.get(i).toString());

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

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                //viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.pbutton = (Button) convertView.findViewById(R.id.list_plus_btn);
                //viewHolder.price = (TextView) convertView.findViewById(R.id.list_item_price);
                //viewHolder.quantity=(TextView) convertView.findViewById(R.id.quan);
                //viewHolder.nbutton=(Button)convertView.findViewById(R.id.list_minus_btn);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.pbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + items[position], Toast.LENGTH_SHORT).show();
                }
            });
           /*
            mainViewholder.nbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                }
            });
            */
            mainViewholder.title.setText(getItem(position));

            return convertView;
        }
    }
    public class ViewHolder {

        //ImageView thumbnail;
        TextView title;
        TextView price;
        Button pbutton;
        //TextView quantity;
        //Button nbutton;
    }
}
