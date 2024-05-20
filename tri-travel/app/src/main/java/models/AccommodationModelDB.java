package models;

public class AccommodationModelDB {
    private long id;
    private long travelId;
    private double estimatedCostPerPerson;
    private int totalNights;
    private int totalRooms;
    private double total;

    public AccommodationModelDB() {
    }

    public AccommodationModelDB(long travelId, double estimatedCostPerPerson, int totalNights, int totalRooms, double total) {
        this.travelId = travelId;
        this.estimatedCostPerPerson = estimatedCostPerPerson;
        this.totalNights = totalNights;
        this.totalRooms = totalRooms;
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

    public double getEstimatedCostPerPerson() {
        return estimatedCostPerPerson;
    }

    public void setEstimatedCostPerPerson(double estimatedCostPerPerson) {
        this.estimatedCostPerPerson = estimatedCostPerPerson;
    }

    public int getTotalNights() {
        return totalNights;
    }

    public void setTotalNights(int totalNights) {
        this.totalNights = totalNights;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
