package com.kostiaxn.gracefinance;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ChangeBalanceFragment extends DialogFragment {


    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.changebalancedialog, null);

        return builder
                .setTitle("Введите новый баланс")
                .setView(view)
                .setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BalanceUpdater balanceUpdater = new BalanceUpdater();
                        EditText editText = (EditText) getDialog().findViewById(R.id.etChangeBalance); // get reference to EditText view
                        String newBalanceString = editText.getText().toString(); // extract value from EditText view
                        double newBalance = Double.parseDouble(newBalanceString); // convert string value to double
                        balanceUpdater.balance = newBalance; // set the new balance value
                        balanceUpdater.setBalance(); // call the method
                        ((MainActivity) requireActivity()).tvCurrentBalanceAmount.setText(String.valueOf(newBalance)); //update MainActivity
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}
