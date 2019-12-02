package com.example.oopd_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Add_to_cart extends AppCompatActivity {

    Button java_card_button;
    FirebaseUser user;
    private DatabaseReference mDatabase,mDatabase1;
    String[] items = {"Broccoli    Rs 10","Pumpkin    Rs 10","Carrot    Rs 10","Mushroom    Rs 10","Capsicum    Rs 10","Onion    Rs 10","Tomato    Rs 10",
            "Corn    Rs 10","Green Beans    Rs 10","Garlic    Rs 10"};
    //int[] images = {R.drawable.dress,R.drawable.ic_launcher_background};
    //String[] price={"10","20","30","4","40","50","60","70","80","90"};
    Integer[] price_list = {1,2,3,4,5,6,7,8,9,10};
    TextView prices_text;
    FirebaseDatabase firebaseDatabase;


     ArrayList<String> data = new ArrayList<>();
    ArrayList<String> data1 = new ArrayList<>();
    String[] cart=new String[data.size()];
    //private ArrayList<Integer> data2 = new ArrayList<Integer>();

     int i=0;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_add_to_cart);
       final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
     // Log.d("SAUMYA",);

       final ListView lv = (ListView) findViewById(R.id.listview);
       lv.setAdapter(adapter);
       java_card_button = (Button) findViewById(R.id.Show_cart_Button);
       firebaseDatabase = FirebaseDatabase.getInstance();

       mDatabase1 = firebaseDatabase.getReference("Inventory");
       mDatabase = firebaseDatabase.getReference("Placeorder");
       //cart= data.toArray(cart);
       mDatabase1.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               String value1 = dataSnapshot.getKey();
               String value2 = "" + dataSnapshot.getValue();
               String val = value1 + "  RS " + value2;
               //cart[i]=value1;
               // Log.d("SAUMYA:"," "+cart[i]);
               //i++;


               data.add(val);
               adapter.notifyDataSetChanged();


           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               adapter.notifyDataSetChanged();

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
          //     Log.d("Saumya",(Item)adapterView.getAdapter().getItem(i));
//                Intent intent=new Intent(getApplicationContext(),check.class);
//
//                startActivity(intent);

            }
        });
        //generateListContent();
//        lv.setAdapter(new MyListAdaper(this, R.layout.list_item, data));
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Add_to_cart.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
//
//
//
//            }
//        });
        java_card_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Place_Order.class);
                startActivity(intent);

            }
        });
    }
//
//    private void generateListContent() {
//        for(int i = 0; i < 10; i++) {
//            data.add(items[i]);

            //data.add(price[i]);
            //prices_text.append(price.get(i).toString());

//        }
//    }

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
