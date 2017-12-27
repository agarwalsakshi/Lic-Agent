package com.agarwalapps.licagent.model.firebase;

import android.util.Log;

import com.agarwalapps.licagent.model.pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by sakshi on 18/12/17.
 */

public class WriteRequest {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference loginRef = database.getReference("agents");

    public void makeRegisterRequest(User user) {
        loginRef.child(user.getMobileNo()).setValue(user);
//        myRef.setValue("Hello, World!");
//
//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("TAG", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("TAG", "Failed to read value.", error.toException());
//            }
//        });

    }
}