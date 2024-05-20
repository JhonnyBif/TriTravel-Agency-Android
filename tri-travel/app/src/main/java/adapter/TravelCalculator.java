package adapter;

import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.travel_agency_android.R;

import java.text.DecimalFormat;

public class TravelCalculator {
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

    public TravelCalculator(
            TextView totalGasolinaTextView, TextView totalPassagemAereaTextView,
            TextView totalHospedagemTextView, TextView totalRefeicoesTextView,
            EditText totalKmEditText, EditText mediaKmPorLitroEditText,
            EditText custoMedioPorLitroEditText, EditText qtdVeiculosEditText,
            EditText custoPessoaEditText, EditText aluguelVeiculoEditText,
            EditText custoPorNoiteEditText, EditText qtdNoitesEditText,
            EditText qtdQuartosEditText, EditText custoRefeicaoEditText,
            EditText refeicoesPorDiaEditText, EditText qtdPessoasEditText,
            EditText duracaoViagemEditText
    ) {
        this.totalGasolinaTextView = totalGasolinaTextView;
        this.totalPassagemAereaTextView = totalPassagemAereaTextView;
        this.totalHospedagemTextView = totalHospedagemTextView;
        this.totalRefeicoesTextView = totalRefeicoesTextView;

        this.totalKmEditText = totalKmEditText;
        this.mediaKmPorLitroEditText = mediaKmPorLitroEditText;
        this.custoMedioPorLitroEditText = custoMedioPorLitroEditText;
        this.qtdVeiculosEditText = qtdVeiculosEditText;

        this.custoPessoaEditText = custoPessoaEditText;
        this.aluguelVeiculoEditText = aluguelVeiculoEditText;

        this.custoPorNoiteEditText = custoPorNoiteEditText;
        this.qtdNoitesEditText = qtdNoitesEditText;
        this.qtdQuartosEditText = qtdQuartosEditText;

        this.custoRefeicaoEditText = custoRefeicaoEditText;
        this.refeicoesPorDiaEditText = refeicoesPorDiaEditText;
        this.qtdPessoasEditText = qtdPessoasEditText;
        this.duracaoViagemEditText = duracaoViagemEditText;

        setupTextWatchers();
    }

    private void setupTextWatchers() {
        totalKmEditText.addTextChangedListener(createTextWatcher(totalGasolinaTextView));
        mediaKmPorLitroEditText.addTextChangedListener(createTextWatcher(totalGasolinaTextView));
        custoMedioPorLitroEditText.addTextChangedListener(createTextWatcher(totalGasolinaTextView));
        qtdVeiculosEditText.addTextChangedListener(createTextWatcher(totalGasolinaTextView));

        custoPessoaEditText.addTextChangedListener(createTextWatcher(totalPassagemAereaTextView));
        aluguelVeiculoEditText.addTextChangedListener(createTextWatcher(totalPassagemAereaTextView));

        custoPorNoiteEditText.addTextChangedListener(createTextWatcher(totalHospedagemTextView));
        qtdNoitesEditText.addTextChangedListener(createTextWatcher(totalHospedagemTextView));
        qtdQuartosEditText.addTextChangedListener(createTextWatcher(totalHospedagemTextView));

        custoRefeicaoEditText.addTextChangedListener(createTextWatcher(totalRefeicoesTextView));
        refeicoesPorDiaEditText.addTextChangedListener(createTextWatcher(totalRefeicoesTextView));
        qtdPessoasEditText.addTextChangedListener(createTextWatcher(totalRefeicoesTextView));
        duracaoViagemEditText.addTextChangedListener(createTextWatcher(totalRefeicoesTextView));
    }

    private TextWatcher createTextWatcher(final TextView totalTextView) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateTotalValues(totalTextView);
            }
        };
    }

    private void updateTotalValues(TextView totalTextView) {
        String totalKmText = totalKmEditText.getText().toString();
        String mediaKmPorLitroText = mediaKmPorLitroEditText.getText().toString();
        String custoMedioPorLitroText = custoMedioPorLitroEditText.getText().toString();
        String qtdVeiculosText = qtdVeiculosEditText.getText().toString();

        String custoPessoaText = custoPessoaEditText.getText().toString();
        String aluguelVeiculoText = aluguelVeiculoEditText.getText().toString();

        String custoPorNoiteText = custoPorNoiteEditText.getText().toString();
        String qtdNoitesText = qtdNoitesEditText.getText().toString();
        String qtdQuartosText = qtdQuartosEditText.getText().toString();

        String custoRefeicaoText = custoRefeicaoEditText.getText().toString();
        String refeicoesPorDiaText = refeicoesPorDiaEditText.getText().toString();
        String qtdPessoasText = qtdPessoasEditText.getText().toString();
        String duracaoViagemText = duracaoViagemEditText.getText().toString();

        if (totalTextView == totalGasolinaTextView) {
            if (isEmpty(totalKmText)
                    || isEmpty(mediaKmPorLitroText)
                    || isEmpty(custoMedioPorLitroText)
                    || isEmpty(qtdVeiculosText)) {
                totalTextView.setText("Total Gasolina: Valores em branco");
                return;
            }
        } else if (totalTextView == totalPassagemAereaTextView) {
            if (isEmpty(custoPessoaText) || isEmpty(aluguelVeiculoText)) {
                totalTextView.setText("Total Passagem Aérea: Valores em branco");
                return;
            }
        } else if (totalTextView == totalHospedagemTextView) {
            if (isEmpty(custoPorNoiteText) || isEmpty(qtdNoitesText) || isEmpty(qtdQuartosText)) {
                totalTextView.setText("Total Hospedagem: Valores em branco");
                return;
            }
        } else if (totalTextView == totalRefeicoesTextView) {
            if (isEmpty(custoRefeicaoText)
                    || isEmpty(refeicoesPorDiaText)
                    || isEmpty(qtdPessoasText)
                    || isEmpty(duracaoViagemText)) {
                totalTextView.setText("Total Refeições: Valores em branco");
                return;
            }
        }

        if (totalTextView == totalGasolinaTextView) {
            int qtdVeiculos = Integer.parseInt(qtdVeiculosText);
            double totalKm = Double.parseDouble(totalKmText);
            double mediaKmPorLitro = Double.parseDouble(mediaKmPorLitroText);
            double custoMedioPorLitro = Double.parseDouble(custoMedioPorLitroText);

            double totalGasolina = calculateTotalGasoline(totalKm, mediaKmPorLitro, custoMedioPorLitro, qtdVeiculos);
            setTotalTextView(totalTextView, totalGasolina);
        } else if (totalTextView == totalPassagemAereaTextView) {
            int qtdPessoas = 1;

            double custoPessoa = Double.parseDouble(custoPessoaText);
            double aluguelVeiculo = Double.parseDouble(aluguelVeiculoText);

            if (!qtdPessoasText.isEmpty()) {
                qtdPessoas = Integer.parseInt(qtdPessoasText);
            }

            double totalPassagemAerea = calculateTotalAirfare(custoPessoa, aluguelVeiculo, qtdPessoas);
            setTotalTextView(totalTextView, totalPassagemAerea);
        } else if (totalTextView == totalHospedagemTextView) {
            double custoPorNoite = Double.parseDouble(custoPorNoiteText);
            int qtdNoites = Integer.parseInt(qtdNoitesText);
            int qtdQuartos = Integer.parseInt(qtdQuartosText);

            double totalHospedagem = calculateTotalAccommodation(custoPorNoite, qtdNoites, qtdQuartos);
            setTotalTextView(totalTextView, totalHospedagem);
        } else if (totalTextView == totalRefeicoesTextView) {
            double custoRefeicao = Double.parseDouble(custoRefeicaoText);
            int refeicoesPorDia = Integer.parseInt(refeicoesPorDiaText);
            int qtdPessoas = Integer.parseInt(qtdPessoasText);
            int duracaoViagem = Integer.parseInt(duracaoViagemText);

            double totalRefeicoes = calculateTotalMeals(
                    custoRefeicao,
                    refeicoesPorDia,
                    qtdPessoas,
                    duracaoViagem
            );
            setTotalTextView(totalTextView, totalRefeicoes);
        }
    }

    private void setTotalTextView(TextView totalTextView, double totalValue) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String totalValueFormatted = decimalFormat.format(totalValue);
        totalTextView.setText("Total: " + totalValueFormatted);
    }

    private boolean isEmpty(String text) {
        return text.trim().isEmpty();
    }

    private double calculateTotalGasoline(
            double totalKm,
            double mediaKmPorLitro,
            double custoMedioPorLitro,
            int qtdVeiculos
    ) {
        return ((totalKm / mediaKmPorLitro) * custoMedioPorLitro) / qtdVeiculos;
    }

    private double calculateTotalAirfare(double custoPessoa, double aluguelVeiculo, int qtdPessoas) {
        return (custoPessoa * qtdPessoas) + aluguelVeiculo;
    }

    private double calculateTotalAccommodation(
            double costPerNight,
            int totalNights,
            int totalRooms
    ) {
        return (costPerNight * totalNights) * totalRooms;
    }

    private double calculateTotalMeals(
            double mealCost,
            int mealsPerDay,
            int qtdPessoas,
            int travelDuration
    ) {
        return ((mealsPerDay * qtdPessoas) * mealCost) * travelDuration;
    }
}