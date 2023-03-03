package com.kostiaxn.gracefinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kostiaxn.gracefinance.model.Income;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddIncomeActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private Button datePickerButton, incomeSaveButton;
    private EditText etAccountName, etCardName, etIncomeCategory, etIncomeSubCategory, etAmount, etPlace, etComment, etCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        datePickerButton = findViewById(R.id.date_picker_button);
        incomeSaveButton = findViewById(R.id.btnSaveIncome);
        etAccountName = findViewById(R.id.etAccountName);
        etCardName = findViewById(R.id.etCardName);
        etIncomeCategory = findViewById(R.id.etIncomeCategory);
        etIncomeSubCategory = findViewById(R.id.etIncomeSubCategory);
        etAmount = findViewById(R.id.etAmount);
        etPlace = findViewById(R.id.etPlace);
        etComment = findViewById(R.id.etComment);
        etCurrency = findViewById(R.id.etCurrency);


        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        incomeSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExpenseInFirebase();
            }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // Format the selected date as "dd.MM.yyyy"
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(year1, monthOfYear, dayOfMonth);
                    String selectedDate = dateFormat.format(selectedCalendar.getTime());

                    datePickerButton.setText(selectedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void saveExpenseInFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference myRef = database.getReference("users/" + userId + "/transactions/incomes");

        // Retrieve the current ID from Firebase
        DatabaseReference idRef = myRef.child("current_id");
        idRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Long currentId = snapshot.getValue(Long.class);

                // Check if the current ID exists
                if (currentId == null) {
                    currentId = 1L;
                    idRef.setValue(currentId);
                }
                String date = datePickerButton.getText().toString().trim();
                String accountName = etAccountName.getText().toString().trim();
                String cardName = etCardName.getText().toString().trim();
                double amount = Double.parseDouble(etAmount.getText().toString().trim());
                String place = etPlace.getText().toString().trim();
                String comment = etComment.getText().toString().trim();
                String currency = etCurrency.getText().toString().trim();
                double exchangeRate = 1; // Create variable in User class
                String incomeCategory = etIncomeCategory.getText().toString().trim();
                String incomeSubCategory = etIncomeSubCategory.getText().toString().trim();


                // Создание нового объекта Income с текущим ID
                Income income = new Income(Math.toIntExact(currentId), date, accountName, cardName, amount, place, comment, currency,
                        exchangeRate, incomeCategory, incomeSubCategory);

                // Сохранение объекта Income в Firebase
                DatabaseReference incomeRef = myRef.child(String.valueOf(currentId));
                incomeRef.setValue(income);

                // Инкрементация текущего ID и сохранение его в firebase
                long newId = currentId + 1;
                idRef.setValue(newId);


                // Обновление баланса юзера в Firebase
                DatabaseReference balanceRef = database.getReference("users/" + userId + "/balance");
                balanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Double currentBalance = snapshot.getValue(Double.class);

                        // Проверка, существует ли баланс
                        if (currentBalance == null) {
                            currentBalance = 0.0;
                        }

                        // новый баланс на основе суммы дохода и обменного курса

//                        double expenseAmount = expense.getAmount() * expense.getExchangeRate();
                        double newBalance = currentBalance + income.getAmount();

                        // Сохранение нового баланса в Firebase
                        balanceRef.setValue(newBalance);

                        Toast.makeText(AddIncomeActivity.this, "Новое поступление добавлено", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddIncomeActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("AddIncomeActivity", "Failed to read balance value.", error.toException());
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("AddIncomeActivity", "Failed to read current_id value.", error.toException());
            }
        });

    }
}

