package com.example.travel_agency_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.TravelAdapter;
import adapter.TravelModel;

public class MainActivity extends AppCompatActivity {
    private ListView travelList;
    private TravelAdapter adapter;
    private Button btnAdd;
    private Button btnLogout;
    private  ArrayList<TravelModel> travels = new ArrayList<TravelModel>();

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        travelList = findViewById(R.id.travelList);
        adapter = new TravelAdapter(MainActivity.this);

        databaseHelper = new DatabaseHelper(this);
        travels.addAll(databaseHelper.findAllTravels());

        adapter.setTravelList(travels);
        travelList.setAdapter(adapter);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TravelFormActivity.class);
                startActivity(intent);
            }
        });

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("email", "");
                editor.putString("password", "");
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAddDialog() {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_layout, null);
//        dialogBuilder.setView(dialogView);
//
//        final EditText editTextNome = dialogView.findViewById(R.id.editTextNome);
//        final EditText editTextTelefone = dialogView.findViewById(R.id.editTextTelefone);
//
//        dialogBuilder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String nome = editTextNome.getText().toString();
//                String telefone = editTextTelefone.getText().toString();
//
//                ContatoModel contato = new ContatoModel();
//
//                contato.setNome(nome);
//                contato.setTelefone(telefone);
//
//                contatos.add(contato);
//
//                adapter.notifyDataSetChanged();
//
//                dialog.dismiss();
//            }
//        });
//
//        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
    }
}