package com.example.retrive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<String> list;
    ArrayList<String> url;
    ArrayAdapter<String> adapter;

    FirebaseDatabase database;
    DatabaseReference ref;

    User user;

    ProgressBar Loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Loader = findViewById(R.id.loader);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User");
        list = new ArrayList<String>();
        url = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,R.layout.userinfo,R.id.userInfo,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = new User();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    Loader.setVisibility(View.VISIBLE);
                    user = ds.getValue(User.class);
                    list.add(String.valueOf(user.getPortal()));
                    url.add(String.valueOf(user.getUrl()));
                }
                Loader.setVisibility(View.GONE);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        //Toast.makeText(getApplicationContext(), url.get(position), Toast.LENGTH_SHORT).show();
                        Intent webActivity = new Intent(MainActivity.this,WebLay.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url",url.get(position));
                        webActivity.putExtras(bundle);
                        startActivity(webActivity);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
