package com.example.travel_agency_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_agency_android.api.Api;
import com.example.travel_agency_android.api.model.PostDTO;
import com.example.travel_agency_android.api.model.Resposta;
import com.example.travel_agency_android.api.model.ViagemCustoAereo;
import com.example.travel_agency_android.api.model.ViagemCustoGasolina;
import com.example.travel_agency_android.api.model.ViagemCustoHospedagem;
import com.example.travel_agency_android.api.model.ViagemCustoRefeicao;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import adapter.TravelCalculator;
import models.AccommodationModelDB;
import models.AirfareModelDB;
import models.EntertainmentModelDB;
import models.GasolineModelDB;
import models.MealModelDB;
import models.TravelModelDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelFormActivity extends AppCompatActivity {
    private TextView totalGasolinaTextView;
    private TextView totalPassagemAereaTextView;
    private TextView totalHospedagemTextView;
    private TextView totalRefeicoesTextView;

    private EditText totalKmEditText;
    private EditText mediaKmPorLitroEditText;
    private EditText custoMedioPorLitroEditText;
    private EditText qtdVeiculosEditText;
    private EditText custoPessoaEditText;
    private EditText aluguelVeiculoEditText;
    private EditText custoPorNoiteEditText;
    private EditText qtdNoitesEditText;
    private EditText qtdQuartosEditText;
    private EditText custoRefeicaoEditText;
    private EditText refeicoesPorDiaEditText;
    private EditText qtdPessoasEditText;
    private EditText duracaoViagemEditText;

    final private List<String> locaisPartida = Arrays.asList("Criciuma", "Laguna", "Urussanga");

    final private List<String> locaisChegada = Arrays.asList("Criciuma", "Laguna", "Urussanga");

    final private List<String> tipoLocomocao = Arrays.asList("Aviao", "Onibus", "Carro");

    private LinearLayout gasolinaSection;
    private LinearLayout aereoSection;

    private TravelCalculator travelCalculator;

    private double totalViagem = 0.0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_form);

        totalGasolinaTextView = findViewById(R.id.totalGasolina);
        totalPassagemAereaTextView = findViewById(R.id.totalPassagemAerea);
        totalHospedagemTextView = findViewById(R.id.totalHospedagem);
        totalRefeicoesTextView = findViewById(R.id.totalRefeicoes);

        totalKmEditText = findViewById(R.id.totalKm);
        mediaKmPorLitroEditText = findViewById(R.id.mediaKmL);
        custoMedioPorLitroEditText = findViewById(R.id.custoMedioLitro);
        qtdVeiculosEditText = findViewById(R.id.qtdVeiculos);

        custoPessoaEditText = findViewById(R.id.custo_pessoa);
        aluguelVeiculoEditText = findViewById(R.id.aluguel_veiculo);

        custoPorNoiteEditText = findViewById(R.id.custo_noite);
        qtdNoitesEditText = findViewById(R.id.noites);
        qtdQuartosEditText = findViewById(R.id.quartos);

        custoRefeicaoEditText = findViewById(R.id.custo_refeicao);
        refeicoesPorDiaEditText = findViewById(R.id.refeicoes_dia);
        qtdPessoasEditText = findViewById(R.id.etQuantasPessoas);
        duracaoViagemEditText = findViewById(R.id.etDuracaoViagem);

        travelCalculator = new TravelCalculator(
                totalGasolinaTextView, totalPassagemAereaTextView,
                totalHospedagemTextView, totalRefeicoesTextView,
                totalKmEditText, mediaKmPorLitroEditText,
                custoMedioPorLitroEditText, qtdVeiculosEditText,
                custoPessoaEditText, aluguelVeiculoEditText,
                custoPorNoiteEditText, qtdNoitesEditText,
                qtdQuartosEditText, custoRefeicaoEditText,
                refeicoesPorDiaEditText, qtdPessoasEditText,
                duracaoViagemEditText
        );

        gasolinaSection = findViewById(R.id.sectionGasolina);
        aereoSection = findViewById(R.id.sectionAviao);

        gasolinaSection.setVisibility(View.GONE);
        aereoSection.setVisibility(View.GONE);

        loadSpinnerLocalPartida();

        loadSpinnerLocalChegada();

        loadSpinnerLocomocao();

        setupCheckBoxListener(findViewById(R.id.option1));
        setupCheckBoxListener(findViewById(R.id.option2));
        setupCheckBoxListener(findViewById(R.id.option3));
        setupCheckBoxListener(findViewById(R.id.option4));
        setupCheckBoxListener(findViewById(R.id.option5));
        setupCheckBoxListener(findViewById(R.id.option6));
        setupCheckBoxListener(findViewById(R.id.option7));
        setupCheckBoxListener(findViewById(R.id.option8));
        setupCheckBoxListener(findViewById(R.id.option9));
        setupCheckBoxListener(findViewById(R.id.option10));

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areAllFieldsFilled()) {
                    if (insertData()) {
                        totalViagem += calculateTotalViagem();
                        int qtdPessoas = Integer.parseInt(qtdPessoasEditText.getText().toString());
                        int duracaoViagem = Integer.parseInt(duracaoViagemEditText.getText().toString());

                        ViagemCustoAereo custoAereo = new ViagemCustoAereo(
                                getDoubleValueFromEditText(R.id.custo_pessoa),
                                getDoubleValueFromEditText(R.id.aluguel_veiculo)
                        );

                        ViagemCustoGasolina custoGasolina = new ViagemCustoGasolina(
                                getDoubleValueFromEditText(R.id.totalKm),
                                getDoubleValueFromEditText(R.id.mediaKmL),
                                getDoubleValueFromEditText(R.id.custoMedioLitro),
                                getDoubleValueFromEditText(R.id.qtdVeiculos)
                        );

                        ViagemCustoHospedagem custoHospedagem = new ViagemCustoHospedagem(
                                getDoubleValueFromEditText(R.id.custo_noite),
                                getIntValueFromEditText(R.id.noites),
                                getIntValueFromEditText(R.id.quartos)
                        );

                        ViagemCustoRefeicao custoRefeicao = new ViagemCustoRefeicao(
                                getDoubleValueFromEditText(R.id.custo_refeicao),
                                getIntValueFromEditText(R.id.refeicoes_dia)
                        );

                        PostDTO dtoEnviar = new PostDTO(
                                qtdPessoas,
                                duracaoViagem,
                                totalViagem,
                                ((Spinner) findViewById(R.id.spLocalChegada)).getSelectedItem().toString()
                        );

                        dtoEnviar.setViagemCustoAereo(custoAereo);
                        dtoEnviar.setViagemCustoGasolina(custoGasolina);
                        dtoEnviar.setViagemCustoHospedagem(custoHospedagem);
                        dtoEnviar.setViagemCustoRefeicao(custoRefeicao);

                        Api.postViagem(dtoEnviar, new Callback<Resposta>() {
                            @Override
                            public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                                if (response != null && response.isSuccessful()) {

                                    Resposta r = response.body();
                                    r.getDado();
                                    r.getMensagem();
                                }
                            }

                            @Override
                            public void onFailure(Call<Resposta> call, Throwable t) {
                                Toast.makeText(TravelFormActivity.this, "Ocorreu um erro ao enviar.", Toast.LENGTH_SHORT).show();

                                t.printStackTrace();
                            }
                        });

                        Toast.makeText(TravelFormActivity.this, "Viagem registrada com sucesso.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(TravelFormActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(TravelFormActivity.this, "Erro ao registrar viagem.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TravelFormActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupCheckBoxListener(final CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTotalEntertainment();
            }
        });
    }

    private void updateTotalEntertainment() {
        CheckBox[] checkBoxes = {
                findViewById(R.id.option1),
                findViewById(R.id.option2),
                findViewById(R.id.option3),
                findViewById(R.id.option4),
                findViewById(R.id.option5),
                findViewById(R.id.option6),
                findViewById(R.id.option7),
                findViewById(R.id.option8),
                findViewById(R.id.option9),
                findViewById(R.id.option10)
        };

        double[] costs = {80.00, 120.00, 50.00, 150.00, 40.00, 80.00, 30.00, 70.00, 90.00, 60.00};

        double totalEntertainment = calculateTotalEntertainmentCost(checkBoxes, costs);
        setTotalTextView(findViewById(R.id.totalEntertainment), totalEntertainment);
    }

    private void setTotalTextView(TextView totalTextView, double totalValue) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String totalValueFormatted = decimalFormat.format(totalValue);
        totalTextView.setText("Total: " + totalValueFormatted);
    }

    private boolean areAllFieldsFilled() {
        Spinner spLocalPartida = findViewById(R.id.spLocalPartida);
        Spinner spLocalChegada = findViewById(R.id.spLocalChegada);
        Spinner spLocomocao = findViewById(R.id.spLocomocao);
        EditText etTravelName = findViewById(R.id.etNomeViagem);
        EditText etDescription = findViewById(R.id.etDescricaoViagem);
        EditText etQuantasPessoas = findViewById(R.id.etQuantasPessoas);

        String selectedLocalPartida = spLocalPartida.getSelectedItem().toString();
        String selectedLocalChegada = spLocalChegada.getSelectedItem().toString();
        String selectedLocomocao = spLocomocao.getSelectedItem().toString();
        String travelName = etTravelName.getText().toString();
        String description = etDescription.getText().toString();
        String quantidadePessoas = etQuantasPessoas.getText().toString();

        return !selectedLocalPartida.isEmpty() && !selectedLocalChegada.isEmpty() &&
                !selectedLocomocao.isEmpty() && !travelName.isEmpty() &&
                !description.isEmpty() && !quantidadePessoas.isEmpty();
    }

    private boolean insertData() {
        long travelId = insertTravel();

        if (travelId == -1) {
            return false;
        }

        Spinner spLocomocao = findViewById(R.id.spLocomocao);
        String selectedLocomocao = spLocomocao.getSelectedItem().toString();

        AirfareModelDB airfare = new AirfareModelDB();
        GasolineModelDB gasoline = new GasolineModelDB();
        AccommodationModelDB accommodation = new AccommodationModelDB();
        MealModelDB meal = new MealModelDB();
        EntertainmentModelDB entertainment = new EntertainmentModelDB();

        if (selectedLocomocao.equals("Aviao")) {
            if (!insertAirfare(travelId, airfare)) {
                return false;
            }
        } else if (selectedLocomocao.equals("Onibus") || selectedLocomocao.equals("Carro")) {
            if (!insertGasoline(travelId, gasoline)) {
                return false;
            }
        }

        if (!insertAccommodation(travelId, accommodation)
                || !insertMeals(travelId, meal)
                || !insertEntertainment(travelId, entertainment)) {
            return false;
        }

        return true;
    }

    private long insertTravel() {
        Spinner spLocalPartida = findViewById(R.id.spLocalPartida);
        Spinner spLocalChegada = findViewById(R.id.spLocalChegada);
        Spinner spLocomocao = findViewById(R.id.spLocomocao);

        String selectedLocalPartida = spLocalPartida.getSelectedItem().toString();
        String selectedLocalChegada = spLocalChegada.getSelectedItem().toString();
        String selectedLocomocao = spLocomocao.getSelectedItem().toString();

        EditText etTravelName = findViewById(R.id.etNomeViagem);
        EditText etDescription = findViewById(R.id.etDescricaoViagem);
        int quantidadePessoas = getIntValueFromEditText(R.id.etQuantasPessoas);
        int travelDuration = getIntValueFromEditText(R.id.etDuracaoViagem);

        TravelModelDB travel = new TravelModelDB();
        travel.setNumberOfPeople(quantidadePessoas);
        travel.setTravelDuration(travelDuration);
        travel.setDepartureLocation(selectedLocalPartida);
        travel.setArrivalLocation(selectedLocalChegada);
        travel.setTransportationMode(selectedLocomocao);

        travel.setTravelName(etTravelName.getText().toString());
        travel.setDescription(etDescription.getText().toString());

        DatabaseHelper dbHelper = new DatabaseHelper(TravelFormActivity.this);

        return dbHelper.insertTravel(travel);
    }

    private boolean insertGasoline(long travelId, GasolineModelDB gasoline) {
        double totalKm = getDoubleValueFromEditText(R.id.totalKm);
        double mediaKmPorLitro = getDoubleValueFromEditText(R.id.mediaKmL);
        double custoMedioPorLitro = getDoubleValueFromEditText(R.id.custoMedioLitro);
        int qtdVeiculos = getIntValueFromEditText(R.id.qtdVeiculos);
        double totalGasoline = calculateTotalGasoline(totalKm, mediaKmPorLitro, custoMedioPorLitro, qtdVeiculos);

        gasoline = new GasolineModelDB(travelId, totalKm, mediaKmPorLitro, custoMedioPorLitro, qtdVeiculos, totalGasoline);

        DatabaseHelper dbHelper = new DatabaseHelper(TravelFormActivity.this);
        return dbHelper.insertGasoline(gasoline);
    }

    private boolean insertAirfare(long travelId, AirfareModelDB airfare) {
        double custoPessoa = getDoubleValueFromEditText(R.id.custo_pessoa);
        double aluguelVeiculo = getDoubleValueFromEditText(R.id.aluguel_veiculo);
        double totalAirfare = calculateTotalAirfare(custoPessoa, aluguelVeiculo);

        airfare = new AirfareModelDB(travelId, custoPessoa, aluguelVeiculo, totalAirfare);

        DatabaseHelper dbHelper = new DatabaseHelper(TravelFormActivity.this);
        return dbHelper.insertAirfare(airfare);
    }

    private boolean insertMeals(long travelId, MealModelDB meal) {
        double mealCost = getDoubleValueFromEditText(R.id.custo_refeicao);
        int mealsPerDay = getIntValueFromEditText(R.id.refeicoes_dia);
        double totalMeals = calculateTotalMeals(mealCost, mealsPerDay);

        meal = new MealModelDB(travelId, mealCost, mealsPerDay, totalMeals);

        DatabaseHelper dbHelper = new DatabaseHelper(TravelFormActivity.this);
        return dbHelper.insertMeal(meal);
    }

    private boolean insertAccommodation(long travelId, AccommodationModelDB accommodation) {
        double custoPorNoite = getDoubleValueFromEditText(R.id.custo_noite);
        int noites = getIntValueFromEditText(R.id.noites);
        int quartos = getIntValueFromEditText(R.id.quartos);
        double total = calculateTotalAccommodation(custoPorNoite, noites, quartos);

        accommodation = new AccommodationModelDB(travelId, custoPorNoite, noites, quartos, total);

        DatabaseHelper dbHelper = new DatabaseHelper(TravelFormActivity.this);
        return dbHelper.insertAccommodation(accommodation);
    }

    private boolean insertEntertainment(long travelId, EntertainmentModelDB entertainment) {
        CheckBox[] checkBoxes = {
                findViewById(R.id.option1),
                findViewById(R.id.option2),
                findViewById(R.id.option3),
                findViewById(R.id.option4),
                findViewById(R.id.option5),
                findViewById(R.id.option6),
                findViewById(R.id.option7),
                findViewById(R.id.option8),
                findViewById(R.id.option9),
                findViewById(R.id.option10)
        };

        double[] costs = {80.00, 120.00, 50.00, 150.00, 40.00, 80.00, 30.00, 70.00, 90.00, 60.00};

        double totalCost = calculateTotalEntertainmentCost(checkBoxes, costs);
        int[] checkBoxValues = getCheckBoxValues(checkBoxes);

        entertainment = new EntertainmentModelDB(
                travelId,
                checkBoxValues[0],
                checkBoxValues[1],
                checkBoxValues[2],
                checkBoxValues[3],
                checkBoxValues[4],
                checkBoxValues[5],
                checkBoxValues[6],
                checkBoxValues[7],
                checkBoxValues[8],
                checkBoxValues[9],
                totalCost
        );

        DatabaseHelper dbHelper = new DatabaseHelper(TravelFormActivity.this);
        return dbHelper.insertEntertainment(entertainment);
    }

    private double getDoubleValueFromEditText(int editTextId) {
        EditText editText = findViewById(editTextId);
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            return Double.parseDouble(text);
        }
        return 0.0;
    }

    private int getIntValueFromEditText(int editTextId) {
        EditText editText = findViewById(editTextId);
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            return Integer.parseInt(text);
        }
        return 0;
    }

    private double calculateTotalGasoline(
            double totalKm,
            double mediaKmPorLitro,
            double custoMedioPorLitro,
            int qtdVeiculos
    ) {
        return ((totalKm / mediaKmPorLitro) * custoMedioPorLitro) / qtdVeiculos;
    }

    private double calculateTotalAirfare(double custoPessoa, double aluguelVeiculo) {
        int qtdPessoas = getIntValueFromEditText(R.id.etQuantasPessoas);

        return (custoPessoa * qtdPessoas) + aluguelVeiculo;
    }

    private double calculateTotalMeals(double mealCost, int mealsPerDay) {
        int qtdPessoas = getIntValueFromEditText(R.id.etQuantasPessoas);
        int travelDuration = getIntValueFromEditText(R.id.etDuracaoViagem);

        return ((mealsPerDay * qtdPessoas) * mealCost) * travelDuration;
    }

    private double calculateTotalAccommodation(
            double costPerNight,
            int totalNights,
            int totalRooms
    ) {
        return (costPerNight * totalNights) * totalRooms;
    }

    private double calculateTotalEntertainmentCost(CheckBox[] checkBoxes, double[] costs) {
        double totalCost = 0.0;

        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                totalCost += costs[i];
            }
        }

        return totalCost;
    }

    private double calculateTotalViagem() {
        double totalGasolina = getDoubleValueFromTextView(totalGasolinaTextView);
        double totalPassagemAerea = getDoubleValueFromTextView(totalPassagemAereaTextView);
        double totalHospedagem = getDoubleValueFromTextView(totalHospedagemTextView);
        double totalRefeicoes = getDoubleValueFromTextView(totalRefeicoesTextView);

        return totalGasolina + totalPassagemAerea + totalHospedagem + totalRefeicoes;
    }

    // Helper method to get double value from TextView
    private double getDoubleValueFromTextView(TextView textView) {
        String text = textView.getText().toString().replaceAll("[^\\d.]", ""); // Remove non-numeric characters
        if (!text.isEmpty()) {
            return Double.parseDouble(text);
        }
        return 0.0;
    }

    private int[] getCheckBoxValues(CheckBox[] checkBoxes) {
        int[] checkBoxValues = new int[checkBoxes.length];

        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxValues[i] = checkBoxes[i].isChecked() ? 1 : 0;
        }

        return checkBoxValues;
    }

    private void loadSpinnerLocomocao() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoLocomocao);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spLocomocao);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = (String) parentView.getItemAtPosition(position);
                // Faça algo com o item selecionado
                if (selectedItem.equals("Aviao")) {

                    gasolinaSection.setVisibility(View.GONE);
                    aereoSection.setVisibility(View.VISIBLE);
                } else {
                    aereoSection.setVisibility(View.GONE);
                    gasolinaSection.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nada foi selecionado
            }
        });
    }

    private void loadSpinnerLocalChegada() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locaisChegada);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spLocalChegada);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = (String) parentView.getItemAtPosition(position);
                // Faça algo com o item selecionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nada foi selecionado
            }
        });
    }

    private void loadSpinnerLocalPartida() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locaisPartida);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spLocalPartida);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = (String) parentView.getItemAtPosition(position);
                // Faça algo com o item selecionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nada foi selecionado
            }
        });
    }
}
