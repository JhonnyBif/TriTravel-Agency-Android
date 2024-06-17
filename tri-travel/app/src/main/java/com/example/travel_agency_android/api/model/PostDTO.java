package com.example.travel_agency_android.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PostDTO implements Serializable {
    private int idConta;
    private int totalViajantes;
    private int duracaoViagem;
    private double custoTotalViagem;
    private double custoPorPessoa;
    private String local;

    public PostDTO(int totalViajantes, int duracaoViagem, double custoTotalViagem, String local) {
        this.totalViajantes = totalViajantes;
        this.duracaoViagem = duracaoViagem;
        this.custoTotalViagem = custoTotalViagem;
        this.local = local;
        this.idConta = 126180;
        this.custoPorPessoa = custoTotalViagem / totalViajantes;
    }

    @SerializedName("aereo")
    private ViagemCustoAereo viagemCustoAereo;

    @SerializedName("listaEntretenimento")
    private List<ViagemCustoEntretenimento> viagemCustoEntretenimentos;

    @SerializedName("gasolina")
    private ViagemCustoGasolina viagemCustoGasolina;

    @SerializedName("hospedagem")
    private ViagemCustoHospedagem viagemCustoHospedagem;

    @SerializedName("refeicao")
    private ViagemCustoRefeicao viagemCustoRefeicao;

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getTotalViajantes() {
        return totalViajantes;
    }

    public void setTotalViajantes(int totalViajantes) {
        this.totalViajantes = totalViajantes;
    }

    public int getDuracaoViagem() {
        return duracaoViagem;
    }

    public void setDuracaoViagem(int duracaoViagem) {
        this.duracaoViagem = duracaoViagem;
    }

    public double getCustoTotalViagem() {
        return custoTotalViagem;
    }

    public void setCustoTotalViagem(double custoTotalViagem) {
        this.custoTotalViagem = custoTotalViagem;
    }

    public double getCustoPorPessoa() {
        return custoPorPessoa;
    }

    public void setCustoPorPessoa(double custoPorPessoa) {
        this.custoPorPessoa = custoPorPessoa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public ViagemCustoAereo getViagemCustoAereo() {
        return viagemCustoAereo;
    }

    public void setViagemCustoAereo(ViagemCustoAereo viagemCustoAereo) {
        this.viagemCustoAereo = viagemCustoAereo;
    }

    public List<ViagemCustoEntretenimento> getViagemCustoEntretenimentos() {
        return viagemCustoEntretenimentos;
    }

    public void setViagemCustoEntretenimentos(List<ViagemCustoEntretenimento> viagemCustoEntretenimentos) {
        this.viagemCustoEntretenimentos = viagemCustoEntretenimentos;
    }

    public ViagemCustoGasolina getViagemCustoGasolina() {
        return viagemCustoGasolina;
    }

    public void setViagemCustoGasolina(ViagemCustoGasolina viagemCustoGasolina) {
        this.viagemCustoGasolina = viagemCustoGasolina;
    }

    public ViagemCustoHospedagem getViagemCustoHospedagem() {
        return viagemCustoHospedagem;
    }

    public void setViagemCustoHospedagem(ViagemCustoHospedagem viagemCustoHospedagem) {
        this.viagemCustoHospedagem = viagemCustoHospedagem;
    }

    public ViagemCustoRefeicao getViagemCustoRefeicao() {
        return viagemCustoRefeicao;
    }

    public void setViagemCustoRefeicao(ViagemCustoRefeicao viagemCustoRefeicao) {
        this.viagemCustoRefeicao = viagemCustoRefeicao;
    }
}
