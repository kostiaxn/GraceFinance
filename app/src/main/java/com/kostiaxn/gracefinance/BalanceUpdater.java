package com.kostiaxn.gracefinance;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class BalanceUpdater {


    double balance;
    public void getBalance(OnCompleteListener<java.lang.Double> listener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference balanceRef = database.getReference("users/" + userId + "/balance");
        balanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get the balance value as a Double
                Double balance = snapshot.getValue(Double.class);
                listener.onComplete(Tasks.forResult(balance));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }







    public void setBalance() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference balanceRef = database.getReference("users/" + userId + "/balance");
        balanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                balanceRef.setValue(balance);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("AddExpenseActivity", "Failed to read balance value.", error.toException());
            }
        });
    }

        }


