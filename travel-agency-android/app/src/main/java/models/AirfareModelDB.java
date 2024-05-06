package models;

public class AirfareModelDB {
    private int id;
    private long travelId;
    private double estimatedCostPerPerson;
    private double vehicleRental;
    private double total;

    public AirfareModelDB() {
    }

    public AirfareModelDB(
            long travelId,
            double estimatedCostPerPerson,
            double vehicleRental,
            double total
    ) {
        this.travelId = travelId;
        this.estimatedCostPerPerson = estimatedCostPerPerson;
        this.vehicleRental = vehicleRental;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }

    public double getEstimatedCostPerPerson() {
        return estimatedCostPerPerson;
    }

    public void setEstimatedCostPerPerson(double estimatedCostPerPerson) {
        this.estimatedCostPerPerson = estimatedCostPerPerson;
    }

    public double getVehicleRental() {
        return vehicleRental;
    }

    public void setVehicleRental(double vehicleRental) {
        this.vehicleRental = vehicleRental;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

