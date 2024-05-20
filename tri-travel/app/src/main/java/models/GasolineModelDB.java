package models;

public class GasolineModelDB {
    private long id;
    private long travelId;
    private double totalKm;
    private double averageKmPerLiter;
    private double averageCostPerLiter;
    private int numberOfVehicles;
    private double total;

    public GasolineModelDB() {
    }

    public GasolineModelDB(long travelId, double totalKm, double averageKmPerLiter, double averageCostPerLiter, int numberOfVehicles, double total) {
        this.travelId = travelId;
        this.totalKm = totalKm;
        this.averageKmPerLiter = averageKmPerLiter;
        this.averageCostPerLiter = averageCostPerLiter;
        this.numberOfVehicles = numberOfVehicles;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }

    public double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(double totalKm) {
        this.totalKm = totalKm;
    }

    public double getAverageKmPerLiter() {
        return averageKmPerLiter;
    }

    public void setAverageKmPerLiter(double averageKmPerLiter) {
        this.averageKmPerLiter = averageKmPerLiter;
    }

    public double getAverageCostPerLiter() {
        return averageCostPerLiter;
    }

    public void setAverageCostPerLiter(double averageCostPerLiter) {
        this.averageCostPerLiter = averageCostPerLiter;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
