package com.kostiaxn.gracefinance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button btnLogOut, btnAddExpense, btnAddIncome;
    TextView tvCurrentBalanceAmount;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentBalance();
        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnAddIncome = findViewById(R.id.btnAddIncome);
        btnLogOut = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        tvCurrentBalanceAmount = findViewById(R.id.tvCurrentBalanceAmount);




        btnLogOut.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
        btnAddExpense.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddExpenseActivity.class)));
        btnAddIncome.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddIncomeActivity.class)));
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void showDialog(View v) {
        ChangeBalanceFragment dialog = new ChangeBalanceFragment();
        dialog.show(getSupportFragmentManager(), "changeBalance");
    }


    public void currentBalance() {
        BalanceUpdater balanceGetter = new BalanceUpdater();
        balanceGetter.getBalance(task -> {
            if (task.isSuccessful()) {
                Double balance = task.getResult();
                tvCurrentBalanceAmount.setText(String.valueOf(balance));
            } else {
                Exception e = task.getException();
                Log.e("BalanceUpdater", "Error getting balance", e);
            }
        });
    }
}



