package com.example.octavian.freundebuch;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

public class AddFriendActivity extends AppCompatActivity {

    ArrayList friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        Intent intent = getIntent();
        Bundle bin = intent.getExtras();
        friendList = (ArrayList) bin.get("friendlist");

        ((Button) findViewById(R.id.addButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap newFriend = new HashMap<String, String>();

                String forename = ((EditText)findViewById(R.id.forenameEditText)).getText().toString();
                String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
                String farbe = ((EditText) findViewById(R.id.farbeEditText)).getText().toString();
                String tier = ((EditText) findViewById(R.id.tierEditText)).getText().toString();
                String autor = ((EditText) findViewById(R.id.autorEditText)).getText().toString();
                String os = ((EditText) findViewById(R.id.osEditText)).getText().toString();
                newFriend.put("name", name);
                newFriend.put("forename", forename);
                newFriend.put("farbe", farbe);
                newFriend.put("tier", tier);
                newFriend.put("autor", autor);
                newFriend.put("os", os);

                friendList.add(newFriend);

                Intent intent = new Intent(AddFriendActivity.this, MainActivity.class);
                intent.putExtra("friendlist", friendList);
                ActivityCompat.startActivityForResult(AddFriendActivity.this, intent, 0, null);
            }
        });
    }
}
