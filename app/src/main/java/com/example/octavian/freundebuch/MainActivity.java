package com.example.octavian.freundebuch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        if (getCallingActivity() != null) {
            Intent intent = getIntent();
            Bundle bin = intent.getExtras();
            if (bin !=null) {
                friendList = (ArrayList) bin.get("friendlist");
                SharedPreferences.Editor e = prefs.edit();
                e.putInt("friendcount", friendList.size());
                for (int i = 0; i < friendList.size(); i++) {
                    HashMap friend = (HashMap) friendList.get(i);
                    e.putString("name_" + String.valueOf(i), friend.get("name").toString());
                    e.putString("forename_" + String.valueOf(i), friend.get("forename").toString());
                    e.putString("os_" + String.valueOf(i), friend.get("os").toString());
                    e.putString("tier_" + String.valueOf(i), friend.get("tier").toString());
                    e.putString("farbe_" + String.valueOf(i), friend.get("farbe").toString());
                    e.putString("autor_" + String.valueOf(i), friend.get("autor").toString());
                }
                e.commit();

            } else {
                friendList = new ArrayList();
            }
        } else {
            friendList = new ArrayList();
            int friendCount = prefs.getInt("friendcount", 0);
            if (friendCount == 0) {
                Log.e("DEBUG", "friendList empty");
            } else {
                for (int i = 0; i < friendCount; i++) {
                    HashMap friend = new HashMap<String, String>();
                    friend.put("name", prefs.getString("name_" + String.valueOf(i), ""));
                    friend.put("forename", prefs.getString("forename_" + String.valueOf(i), ""));
                    friend.put("os", prefs.getString("os_" + String.valueOf(i), ""));
                    friend.put("tier", prefs.getString("tier_" + String.valueOf(i), ""));
                    friend.put("farbe", prefs.getString("farbe_" + String.valueOf(i), ""));
                    friend.put("autor", prefs.getString("autor_" + String.valueOf(i), ""));
                    friendList.add(friend);
                }
            }
        }
        Log.e("DEBUG", Integer.toString(friendList.size()));

        lv = findViewById(R.id.friendList);
        fillListView();

        ((Button) findViewById(R.id.addButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriendActivity.class);
                intent.putExtra("friendlist", friendList);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewFriendActivity.class);
                intent.putExtra("friend", (HashMap) friendList.get(position));
                startActivity(intent);
            }
        });
    }

    protected void fillListView() {
        List<String> listViewArray = new ArrayList<String>();
        if (friendList.size() != 0 ) {
            for (Object friend : friendList) {
                HashMap _friend = (HashMap) friend;
                String id = _friend.get("name") + ", " + _friend.get("forename");
                listViewArray.add(id);
            }
            Log.e("DEBUG", Integer.toString(listViewArray.size()));
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    listViewArray );

            lv.setAdapter(arrayAdapter);
        }

    }

}
