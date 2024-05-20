package com.example.travel_agency_android.api.model;

import java.io.Serializable;
import java.util.Date;

public class ViagemCustoGasolina implements Serializable {

    private double totalEstimadoKM;
    private double mediaKMLitro;
    private double custoMedioLitro;
    private double totalVeiculos;

    public ViagemCustoGasolina(double totalEstimadoKM, double mediaKMLitro, double custoMedioLitro, double totalVeiculos) {
        this.totalEstimadoKM = totalEstimadoKM;
        this.mediaKMLitro = mediaKMLitro;
        this.custoMedioLitro = custoMedioLitro;
        this.totalVeiculos = totalVeiculos;
    }

    public double getTotalEstimadoKM() {
        return totalEstimadoKM;
    }

    public void setTotalEstimadoKM(double totalEstimadoKM) {
        this.totalEstimadoKM = totalEstimadoKM;
    }

    public double getMediaKMLitro() {
        return mediaKMLitro;
    }

    public void setMediaKMLitro(double mediaKMLitro) {
        this.mediaKMLitro = mediaKMLitro;
    }

    public double getCustoMedioLitro() {
        return custoMedioLitro;
    }

    public void setCustoMedioLitro(double custoMedioLitro) {
        this.custoMedioLitro = custoMedioLitro;
    }

    public double getCustoPorPessoa() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(double totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }
}
