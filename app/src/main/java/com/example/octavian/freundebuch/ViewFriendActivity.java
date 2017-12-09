package com.example.octavian.freundebuch;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class ViewFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend);

        Intent intent = getIntent();
        Bundle bin = intent.getExtras();
        HashMap friend = (HashMap) bin.get("friend");

        ((TextView) findViewById(R.id.nameTextView)).setText(friend.get("forename").toString() + " " + friend.get("name").toString());
        ((TextView) findViewById(R.id.farbeTextView)).setText(friend.get("farbe").toString());
        ((TextView) findViewById(R.id.tierTextView)).setText(friend.get("tier").toString());
        ((TextView) findViewById(R.id.autorTextView)).setText(friend.get("autor").toString());
        ((TextView) findViewById(R.id.osTextView)).setText(friend.get("os").toString());
    }
}
