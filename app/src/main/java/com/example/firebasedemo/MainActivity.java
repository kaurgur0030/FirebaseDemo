package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed1, ed2, ed3;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.qty);
        ed3 = findViewById(R.id.Price);
        b1 = findViewById(R.id.add);
        b2 = findViewById(R.id.post);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                break;
            case R.id.post:
                break;
        }
    }

    public void adddataFirestore() {
        String name = ed1.getText().toString();
        String qty = ed2.getText().toString();
        String price = ed3.getText().toString();

        HashMap<String,String> order = new HashMap<>();
        order.put("name", name);
        order.put("qty", qty);
        order.put("price", price);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders")
                .add(order);



    }
}