package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
        b1 = findViewById(R.id.retreive);
        b2 = findViewById(R.id.post);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retreive:
                RetreiveData();
                break;
            case R.id.post:
                adddataFirestore();
                break;
        }
    }

    public  void RetreiveData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                System.out.println(documentSnapshot.getData().toString());

                            }
                            }
                        }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
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
                .add(order)



    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
        @Override
        public void onComplete(@NonNull Task<DocumentReference> task) {
            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
        }
    })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                   e.printStackTrace(); }
                });

    }


}