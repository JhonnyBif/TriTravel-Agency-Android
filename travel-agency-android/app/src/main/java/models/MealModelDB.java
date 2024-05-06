package models;

public class MealModelDB {
    private long travelId;
    private double mealCost;
    private int mealsPerDay;
    private double total;

    public MealModelDB() {
    }

    public MealModelDB(long travelId, double mealCost, int mealsPerDay, double total) {
        this.travelId = travelId;
        this.mealCost = mealCost;
        this.mealsPerDay = mealsPerDay;
        this.total = total;
    }

    public long getTravelId() {
        return travelId;
    }

    public void setTravelId(long travelId) {
        this.travelId = travelId;
    }

    public double getMealCost() {
        return mealCost;
    }

    public void setMealCost(double mealCost) {
        this.mealCost = mealCost;
    }

    public int getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(int mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

