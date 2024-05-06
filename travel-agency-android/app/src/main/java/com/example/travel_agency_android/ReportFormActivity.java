package com.example.travel_agency_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

import models.AccommodationModelDB;
import models.AirfareModelDB;
import models.EntertainmentModelDB;
import models.GasolineModelDB;
import models.MealModelDB;
import models.TravelModelDB;

public class ReportFormActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private long travelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_form);

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("travelId")) {
            travelId = intent.getLongExtra("travelId", -1);

            if (travelId != -1) {
                TravelModelDB travel = databaseHelper.getTravelById(travelId);

                TextView travelNameTextView = findViewById(R.id.travel_name);
                travelNameTextView.setText(travel.getTravelName());

                TextView travelDescriptionTextView = findViewById(R.id.travel_description);
                travelDescriptionTextView.setText(travel.getDescription());

                TextView qtdPeopleTextView = findViewById(R.id.people_qtd);
                qtdPeopleTextView.setText("Quantidade de pessoas: " + String.valueOf(travel.getNumberOfPeople()));

                TextView departureLocationTextView = findViewById(R.id.departure_location);
                departureLocationTextView.setText("Local de partida: " + travel.getDepartureLocation());

                TextView arrivalLocationTextView = findViewById(R.id.arrival_location);
                arrivalLocationTextView.setText("Local de chegada: " + travel.getArrivalLocation());

                TextView transportationModeTextView = findViewById(R.id.transportation_mode);
                transportationModeTextView.setText("Meio de locomoção: " + travel.getTransportationMode());

                GasolineModelDB gasoline = databaseHelper.getGasolineById(travelId);
                AirfareModelDB airfare = databaseHelper.getAirfareById(travelId);
                AccommodationModelDB accommodation = databaseHelper.getAccommodationById(travelId);
                MealModelDB meal = databaseHelper.getMealById(travelId);
                EntertainmentModelDB entertainment = databaseHelper.getEntertainmentById(travelId);

                double totalLocomotion;

                if (gasoline != null && gasoline.getTotal() >= 0) {
                    totalLocomotion = gasoline.getTotal();
                } else if (airfare != null && airfare.getTotal() >= 0) {
                    totalLocomotion = airfare.getTotal();
                } else {
                    totalLocomotion = 0.0;
                }

                TextView totalLocomotionTextView = findViewById(R.id.total_locomotion);
                totalLocomotionTextView.setText("Gastos com locomoção: " + formatCurrency(totalLocomotion));

                TextView totalAccommodationTextView = findViewById(R.id.total_accommodation);
                totalAccommodationTextView.setText("Gastos com Hospedagem: " + formatCurrency(accommodation.getTotal()));

                TextView totalMealTextView = findViewById(R.id.total_meal);
                totalMealTextView.setText("Gastos com Alimentação: " + formatCurrency(meal.getTotal()));

                TextView totalEntertainmentTextView = findViewById(R.id.total_entertainment);
                totalEntertainmentTextView.setText("Gastos com Entretenimento: " + formatCurrency(entertainment.getTotal()));
            }
        }

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String formatCurrency(double value) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return currencyFormat.format(value);
    }
}
