package com.example.travel_agency_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import TablesHelper.AccommodationTable;
import TablesHelper.AirfareTable;
import TablesHelper.EntertainmentTable;
import TablesHelper.GasolineTable;
import TablesHelper.MealsTable;
import TablesHelper.TravelsTable;
import TablesHelper.UsersTable;
import adapter.TravelModel;
import models.AccommodationModelDB;
import models.AirfareModelDB;
import models.EntertainmentModelDB;
import models.GasolineModelDB;
import models.MealModelDB;
import models.TravelModelDB;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "travel_agency.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UsersTable.TABLE_NAME + " (" +
                UsersTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsersTable.COL_EMAIL + " TEXT, " +
                UsersTable.COL_PASSWORD + " TEXT)");

        db.execSQL("CREATE TABLE " + TravelsTable.TABLE_NAME + " (" +
                TravelsTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TravelsTable.COL_TRAVEL_NAME + " TEXT, " +
                TravelsTable.COL_DESCRIPTION + " TEXT, " +
                TravelsTable.COL_QUANTITY_OF_PEOPLE + " INTEGER, " +
                TravelsTable.COL_TRAVEL_DURATION + " INTEGER, " +
                TravelsTable.COL_DEPARTURE_LOCATION + " TEXT, " +
                TravelsTable.COL_ARRIVAL_LOCATION + " TEXT, " +
                TravelsTable.COL_TRANSPORTATION_MODE + " TEXT)");

        db.execSQL("CREATE TABLE " + GasolineTable.TABLE_NAME + " (" +
                GasolineTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GasolineTable.COL_TRAVEL_ID + " INTEGER, " +
                GasolineTable.COL_TOTAL_KM + " REAL, " +
                GasolineTable.COL_MEDIA_KM_LITRES + " REAL, " +
                GasolineTable.COL_AVERAGE_COST_PER_LITER + " REAL, " +
                GasolineTable.COL_NUMBER_OF_VEHICLES + " INTEGER, " +
                GasolineTable.COL_TOTAL + " REAL, " +
                "FOREIGN KEY (" + GasolineTable.COL_TRAVEL_ID + ") REFERENCES "
                + TravelsTable.TABLE_NAME + "(" + TravelsTable.COL_ID + "))");

        db.execSQL("CREATE TABLE " + AirfareTable.TABLE_NAME + " (" +
                AirfareTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AirfareTable.COL_TRAVEL_ID + " INTEGER, " +
                AirfareTable.COL_ESTIMATED_COST_PER_PERSON + " REAL, " +
                AirfareTable.COL_VEHICLE_RENTAL + " REAL, " +
                AirfareTable.COL_TOTAL + " REAL, " +
                "FOREIGN KEY (" + AirfareTable.COL_TRAVEL_ID + ") REFERENCES " +
                TravelsTable.TABLE_NAME + "(" + TravelsTable.COL_ID + "))");

        db.execSQL("CREATE TABLE " + MealsTable.TABLE_NAME + " (" +
                MealsTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MealsTable.COL_TRAVEL_ID + " INTEGER, " +
                MealsTable.COL_MEAL_COST + " REAL, " +
                MealsTable.COL_MEALS_PER_DAY + " INTEGER, " +
                MealsTable.COL_TOTAL + " REAL, " +
                "FOREIGN KEY (" + AirfareTable.COL_TRAVEL_ID + ") REFERENCES " +
                TravelsTable.TABLE_NAME + "(" + TravelsTable.COL_ID + "))");

        db.execSQL("CREATE TABLE " + AccommodationTable.TABLE_NAME + " (" +
                AccommodationTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AccommodationTable.COL_TRAVEL_ID + " INTEGER, " +
                AccommodationTable.COL_COST_PER_NIGHT + " REAL, " +
                AccommodationTable.COL_TOTAL_OF_NIGHTS + " INTEGER, " +
                AccommodationTable.COL_TOTAL_OF_ROOMS + " INTEGER, " +
                AccommodationTable.COL_TOTAL + " TEXT)");

        db.execSQL("CREATE TABLE " + EntertainmentTable.TABLE_NAME + " (" +
                EntertainmentTable.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EntertainmentTable.COL_TRAVEL_ID + " INTEGER," +
                EntertainmentTable.COL_OPTION_1 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_2 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_3 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_4 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_5 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_6 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_7 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_8 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_9 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_OPTION_10 + " INTEGER DEFAULT 0," +
                EntertainmentTable.COL_TOTAL + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TravelsTable.TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + AirfareTable.TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + MealsTable.TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + AccommodationTable.TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS " + EntertainmentTable.TABLE_NAME);
        onCreate(MyDB);
    }

    public boolean insertUser(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersTable.COL_EMAIL, email);
        contentValues.put(UsersTable.COL_PASSWORD, password);
        long result = MyDatabase.insert(UsersTable.TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME + " WHERE "
                + UsersTable.COL_EMAIL + " = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public void deleteTravelById(int travelId) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        String whereClause = TravelsTable.COL_ID + " = ?";
        String[] whereArgs = {String.valueOf(travelId)};

        MyDatabase.delete(TravelsTable.TABLE_NAME, whereClause, whereArgs);
    }


    public List<TravelModel> findAllTravels() {
        List<TravelModel> travelList = new ArrayList<>();
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TravelsTable.TABLE_NAME;
        Cursor cursor = MyDatabase.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int nomeColumnIndex = cursor.getColumnIndex(TravelsTable.COL_TRAVEL_NAME);
                int descricaoColumnIndex = cursor.getColumnIndex(TravelsTable.COL_DESCRIPTION);
                int idColumnIndex = cursor.getColumnIndex(TravelsTable.COL_ID);

                do {
                    String nome = cursor.getString(nomeColumnIndex);
                    String descricao = cursor.getString(descricaoColumnIndex);
                    Long id = cursor.getLong(idColumnIndex);

                    TravelModel travel = new TravelModel(id, nome, descricao);
                    travelList.add(travel);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        return travelList;
    }

    public TravelModelDB getTravelById(long travelId) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TravelsTable.TABLE_NAME +
                " WHERE " + TravelsTable.COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(travelId)};

        Cursor cursor = MyDatabase.rawQuery(query, selectionArgs);

        TravelModelDB travel = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int travelNameIndex = cursor.getColumnIndex(TravelsTable.COL_TRAVEL_NAME);
                int descriptionIndex = cursor.getColumnIndex(TravelsTable.COL_DESCRIPTION);
                int numberOfPeopleIndex = cursor.getColumnIndex(TravelsTable.COL_QUANTITY_OF_PEOPLE);
                int travelDurationIndex = cursor.getColumnIndex(TravelsTable.COL_TRAVEL_DURATION);
                int departureLocationIndex = cursor.getColumnIndex(TravelsTable.COL_DEPARTURE_LOCATION);
                int arrivalLocationIndex = cursor.getColumnIndex(TravelsTable.COL_ARRIVAL_LOCATION);
                int transportationModeIndex = cursor.getColumnIndex(TravelsTable.COL_TRANSPORTATION_MODE);

                String travelName = cursor.getString(travelNameIndex);
                String description = cursor.getString(descriptionIndex);
                int numberOfPeople = cursor.getInt(numberOfPeopleIndex);
                int travelDuration = cursor.getInt(travelDurationIndex);
                String departureLocation = cursor.getString(departureLocationIndex);
                String arrivalLocation = cursor.getString(arrivalLocationIndex);
                String transportationMode = cursor.getString(transportationModeIndex);

                travel = new TravelModelDB(travelName, description, numberOfPeople,
                        travelDuration, departureLocation, arrivalLocation, transportationMode);
            }

            cursor.close();
        }

        return travel;
    }

    public GasolineModelDB getGasolineById(long gasolineId) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + GasolineTable.TABLE_NAME +
                " WHERE " + GasolineTable.COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(gasolineId)};

        Cursor cursor = MyDatabase.rawQuery(query, selectionArgs);

        GasolineModelDB gasoline = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int travelIdIndex = cursor.getColumnIndex(GasolineTable.COL_TRAVEL_ID);
                int totalKmIndex = cursor.getColumnIndex(GasolineTable.COL_TOTAL_KM);
                int mediaKmLitresIndex = cursor.getColumnIndex(GasolineTable.COL_MEDIA_KM_LITRES);
                int averageCostPerLiterIndex = cursor.getColumnIndex(GasolineTable.COL_AVERAGE_COST_PER_LITER);
                int numberOfVehiclesIndex = cursor.getColumnIndex(GasolineTable.COL_NUMBER_OF_VEHICLES);
                int totalIndex = cursor.getColumnIndex(GasolineTable.COL_TOTAL);

                long travelId = cursor.getLong(travelIdIndex);
                double totalKm = cursor.getDouble(totalKmIndex);
                double mediaKmLitres = cursor.getDouble(mediaKmLitresIndex);
                double averageCostPerLiter = cursor.getDouble(averageCostPerLiterIndex);
                int numberOfVehicles = cursor.getInt(numberOfVehiclesIndex);
                double total = cursor.getDouble(totalIndex);

                gasoline = new GasolineModelDB(travelId, totalKm, mediaKmLitres, averageCostPerLiter, numberOfVehicles, total);
            }

            cursor.close();
        }

        return gasoline;
    }

    public AirfareModelDB getAirfareById(long airfareId) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + AirfareTable.TABLE_NAME +
                " WHERE " + AirfareTable.COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(airfareId)};

        Cursor cursor = MyDatabase.rawQuery(query, selectionArgs);

        AirfareModelDB airfare = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int travelIdIndex = cursor.getColumnIndex(AirfareTable.COL_TRAVEL_ID);
                int estimatedCostPerPersonIndex = cursor.getColumnIndex(AirfareTable.COL_ESTIMATED_COST_PER_PERSON);
                int vehicleRentalIndex = cursor.getColumnIndex(AirfareTable.COL_VEHICLE_RENTAL);
                int totalIndex = cursor.getColumnIndex(AirfareTable.COL_TOTAL);

                long travelId = cursor.getLong(travelIdIndex);
                double estimatedCostPerPerson = cursor.getDouble(estimatedCostPerPersonIndex);
                double vehicleRental = cursor.getDouble(vehicleRentalIndex);
                double total = cursor.getDouble(totalIndex);

                airfare = new AirfareModelDB(travelId, estimatedCostPerPerson, vehicleRental, total);
            }

            cursor.close();
        }

        return airfare;
    }

    public AccommodationModelDB getAccommodationById(long accommodationId) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + AccommodationTable.TABLE_NAME +
                " WHERE " + AccommodationTable.COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(accommodationId)};

        Cursor cursor = MyDatabase.rawQuery(query, selectionArgs);

        AccommodationModelDB accommodation = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int travelIdIndex = cursor.getColumnIndex(AccommodationTable.COL_TRAVEL_ID);
                int costPerNightIndex = cursor.getColumnIndex(AccommodationTable.COL_COST_PER_NIGHT);
                int totalOfNightsIndex = cursor.getColumnIndex(AccommodationTable.COL_TOTAL_OF_NIGHTS);
                int totalOfRoomsIndex = cursor.getColumnIndex(AccommodationTable.COL_TOTAL_OF_ROOMS);
                int totalIndex = cursor.getColumnIndex(AccommodationTable.COL_TOTAL);

                long travelId = cursor.getLong(travelIdIndex);
                double costPerNight = cursor.getDouble(costPerNightIndex);
                int totalOfNights = cursor.getInt(totalOfNightsIndex);
                int totalOfRooms = cursor.getInt(totalOfRoomsIndex);
                double total = cursor.getDouble(totalIndex);

                accommodation = new AccommodationModelDB(travelId, costPerNight, totalOfNights, totalOfRooms, total);
            }

            cursor.close();
        }

        return accommodation;
    }

    public MealModelDB getMealById(long mealId) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + MealsTable.TABLE_NAME +
                " WHERE " + MealsTable.COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(mealId)};

        Cursor cursor = MyDatabase.rawQuery(query, selectionArgs);

        MealModelDB meal = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int travelIdIndex = cursor.getColumnIndex(MealsTable.COL_TRAVEL_ID);
                int mealCostIndex = cursor.getColumnIndex(MealsTable.COL_MEAL_COST);
                int mealsPerDayIndex = cursor.getColumnIndex(MealsTable.COL_MEALS_PER_DAY);
                int totalIndex = cursor.getColumnIndex(MealsTable.COL_TOTAL);

                long travelId = cursor.getLong(travelIdIndex);
                double mealCost = cursor.getDouble(mealCostIndex);
                int mealsPerDay = cursor.getInt(mealsPerDayIndex);
                double total = cursor.getDouble(totalIndex);

                meal = new MealModelDB(travelId, mealCost, mealsPerDay, total);
            }

            cursor.close();
        }

        return meal;
    }

    public EntertainmentModelDB getEntertainmentById(long entertainmentId) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + EntertainmentTable.TABLE_NAME +
                " WHERE " + EntertainmentTable.COL_ID + " = ?";
        String[] selectionArgs = {String.valueOf(entertainmentId)};

        Cursor cursor = MyDatabase.rawQuery(query, selectionArgs);

        EntertainmentModelDB entertainment = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int travelIdIndex = cursor.getColumnIndex(EntertainmentTable.COL_TRAVEL_ID);
                int option1Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_1);
                int option2Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_2);
                int option3Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_3);
                int option4Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_4);
                int option5Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_5);
                int option6Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_6);
                int option7Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_7);
                int option8Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_8);
                int option9Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_9);
                int option10Index = cursor.getColumnIndex(EntertainmentTable.COL_OPTION_10);
                int totalIndex = cursor.getColumnIndex(EntertainmentTable.COL_TOTAL);

                long travelId = cursor.getLong(travelIdIndex);
                int option1 = cursor.getInt(option1Index);
                int option2 = cursor.getInt(option2Index);
                int option3 = cursor.getInt(option3Index);
                int option4 = cursor.getInt(option4Index);
                int option5 = cursor.getInt(option5Index);
                int option6 = cursor.getInt(option6Index);
                int option7 = cursor.getInt(option7Index);
                int option8 = cursor.getInt(option8Index);
                int option9 = cursor.getInt(option9Index);
                int option10 = cursor.getInt(option10Index);
                double total = cursor.getDouble(totalIndex);

                entertainment = new EntertainmentModelDB(
                        travelId, option1,
                        option2, option3,
                        option4, option5,
                        option6, option7,
                        option8, option9,
                        option10, total
                );
            }

            cursor.close();
        }

        return entertainment;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME + " WHERE "
                + UsersTable.COL_EMAIL + " = ? AND " + UsersTable.COL_PASSWORD
                + " = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public long insertTravel(TravelModelDB travel) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TravelsTable.COL_TRAVEL_NAME, travel.getTravelName());
        contentValues.put(TravelsTable.COL_DESCRIPTION, travel.getDescription());
        contentValues.put(TravelsTable.COL_QUANTITY_OF_PEOPLE, travel.getNumberOfPeople());
        contentValues.put(TravelsTable.COL_TRAVEL_DURATION, travel.getTravelDuration());
        contentValues.put(TravelsTable.COL_DEPARTURE_LOCATION, travel.getDepartureLocation());
        contentValues.put(TravelsTable.COL_ARRIVAL_LOCATION, travel.getArrivalLocation());
        contentValues.put(TravelsTable.COL_TRANSPORTATION_MODE, travel.getTransportationMode());

        long result = MyDatabase.insert(TravelsTable.TABLE_NAME, null, contentValues);

        return result;
    }

    public int updateTravel(TravelModelDB travel) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.i("aaaa", travel.getId() + "id");
        contentValues.put(TravelsTable.COL_ID, travel.getId());
        contentValues.put(TravelsTable.COL_TRAVEL_NAME, travel.getTravelName());
        contentValues.put(TravelsTable.COL_DESCRIPTION, travel.getDescription());
        contentValues.put(TravelsTable.COL_QUANTITY_OF_PEOPLE, travel.getNumberOfPeople());
        contentValues.put(TravelsTable.COL_TRAVEL_DURATION, travel.getTravelDuration());
        contentValues.put(TravelsTable.COL_DEPARTURE_LOCATION, travel.getDepartureLocation());
        contentValues.put(TravelsTable.COL_ARRIVAL_LOCATION, travel.getArrivalLocation());
        contentValues.put(TravelsTable.COL_TRANSPORTATION_MODE, travel.getTransportationMode());

        String whereClause = TravelsTable.COL_ID + " = ?";
        String[] whereArgs = { String.valueOf(travel.getId()) };

        int result = MyDatabase.update(TravelsTable.TABLE_NAME, contentValues, whereClause, whereArgs);
        return result;
    }

    public boolean insertGasoline(GasolineModelDB gasoline) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(GasolineTable.COL_TRAVEL_ID, gasoline.getTravelId());
        contentValues.put(GasolineTable.COL_TOTAL_KM, gasoline.getTotalKm());
        contentValues.put(GasolineTable.COL_MEDIA_KM_LITRES, gasoline.getAverageKmPerLiter());
        contentValues.put(GasolineTable.COL_AVERAGE_COST_PER_LITER, gasoline.getAverageCostPerLiter());
        contentValues.put(GasolineTable.COL_NUMBER_OF_VEHICLES, gasoline.getNumberOfVehicles());
        contentValues.put(GasolineTable.COL_TOTAL, gasoline.getTotal());

        long result = MyDatabase.insert(GasolineTable.TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public boolean updateGasoline(GasolineModelDB gasoline) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(GasolineTable.COL_TRAVEL_ID, gasoline.getTravelId());
        contentValues.put(GasolineTable.COL_TOTAL_KM, gasoline.getTotalKm());
        contentValues.put(GasolineTable.COL_MEDIA_KM_LITRES, gasoline.getAverageKmPerLiter());
        contentValues.put(GasolineTable.COL_AVERAGE_COST_PER_LITER, gasoline.getAverageCostPerLiter());
        contentValues.put(GasolineTable.COL_NUMBER_OF_VEHICLES, gasoline.getNumberOfVehicles());
        contentValues.put(GasolineTable.COL_TOTAL, gasoline.getTotal());

        String whereClause = AirfareTable.COL_TRAVEL_ID + " = ?";
        String[] whereArgs = { String.valueOf(gasoline.getTravelId()) };

        long result = MyDatabase.update(GasolineTable.TABLE_NAME, contentValues,  whereClause, whereArgs);
        return result != -1;
    }

    public boolean insertAirfare(AirfareModelDB airfare) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AirfareTable.COL_TRAVEL_ID, airfare.getTravelId());
        contentValues.put(AirfareTable.COL_ESTIMATED_COST_PER_PERSON, airfare.getEstimatedCostPerPerson());
        contentValues.put(AirfareTable.COL_VEHICLE_RENTAL, airfare.getVehicleRental());
        contentValues.put(AirfareTable.COL_TOTAL, airfare.getTotal());

        long result = MyDatabase.insert(AirfareTable.TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateAirfare(AirfareModelDB airfare) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AirfareTable.COL_TRAVEL_ID, airfare.getTravelId());
        contentValues.put(AirfareTable.COL_ESTIMATED_COST_PER_PERSON, airfare.getEstimatedCostPerPerson());
        contentValues.put(AirfareTable.COL_VEHICLE_RENTAL, airfare.getVehicleRental());
        contentValues.put(AirfareTable.COL_TOTAL, airfare.getTotal());

        String whereClause = AirfareTable.COL_TRAVEL_ID + " = ?";
        String[] whereArgs = { String.valueOf(airfare.getTravelId()) };

        long result = MyDatabase.update(AirfareTable.TABLE_NAME, contentValues,  whereClause, whereArgs);
        return result != -1;
    }

    public boolean insertMeal(MealModelDB meal) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MealsTable.COL_TRAVEL_ID, meal.getTravelId());
        contentValues.put(MealsTable.COL_MEAL_COST, meal.getMealCost());
        contentValues.put(MealsTable.COL_MEALS_PER_DAY, meal.getMealsPerDay());
        contentValues.put(MealsTable.COL_TOTAL, meal.getTotal());

        long result = MyDatabase.insert(MealsTable.TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateMeal(MealModelDB meal) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MealsTable.COL_TRAVEL_ID, meal.getTravelId());
        contentValues.put(MealsTable.COL_MEAL_COST, meal.getMealCost());
        contentValues.put(MealsTable.COL_MEALS_PER_DAY, meal.getMealsPerDay());
        contentValues.put(MealsTable.COL_TOTAL, meal.getTotal());
        String whereClause = AirfareTable.COL_TRAVEL_ID + " = ?";
        String[] whereArgs = { String.valueOf(meal.getTravelId()) };


        long result = MyDatabase.update(MealsTable.TABLE_NAME, contentValues,  whereClause, whereArgs);
        return result != -1;
    }

    public boolean insertAccommodation(AccommodationModelDB accommodation) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AccommodationTable.COL_TRAVEL_ID, accommodation.getTravelId());
        contentValues.put(AccommodationTable.COL_COST_PER_NIGHT, accommodation.getEstimatedCostPerPerson());
        contentValues.put(AccommodationTable.COL_TOTAL_OF_NIGHTS, accommodation.getTotalNights());
        contentValues.put(AccommodationTable.COL_TOTAL_OF_ROOMS, accommodation.getTotalRooms());
        contentValues.put(AccommodationTable.COL_TOTAL, accommodation.getTotal());

        long result = database.insert(AccommodationTable.TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateAccommodation(AccommodationModelDB accommodation) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AccommodationTable.COL_TRAVEL_ID, accommodation.getTravelId());
        contentValues.put(AccommodationTable.COL_COST_PER_NIGHT, accommodation.getEstimatedCostPerPerson());
        contentValues.put(AccommodationTable.COL_TOTAL_OF_NIGHTS, accommodation.getTotalNights());
        contentValues.put(AccommodationTable.COL_TOTAL_OF_ROOMS, accommodation.getTotalRooms());
        contentValues.put(AccommodationTable.COL_TOTAL, accommodation.getTotal());
        String whereClause = AirfareTable.COL_TRAVEL_ID + " = ?";
        String[] whereArgs = { String.valueOf(accommodation.getTravelId()) };

        long result = database.update(AccommodationTable.TABLE_NAME, contentValues,  whereClause, whereArgs);
        return result != -1;
    }

    public boolean insertEntertainment(EntertainmentModelDB entertainment) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EntertainmentTable.COL_TRAVEL_ID, entertainment.getTravelId());
        contentValues.put(EntertainmentTable.COL_OPTION_1, entertainment.getOption1());
        contentValues.put(EntertainmentTable.COL_OPTION_2, entertainment.getOption2());
        contentValues.put(EntertainmentTable.COL_OPTION_3, entertainment.getOption3());
        contentValues.put(EntertainmentTable.COL_OPTION_4, entertainment.getOption4());
        contentValues.put(EntertainmentTable.COL_OPTION_5, entertainment.getOption5());
        contentValues.put(EntertainmentTable.COL_OPTION_6, entertainment.getOption6());
        contentValues.put(EntertainmentTable.COL_OPTION_7, entertainment.getOption7());
        contentValues.put(EntertainmentTable.COL_OPTION_8, entertainment.getOption8());
        contentValues.put(EntertainmentTable.COL_OPTION_9, entertainment.getOption9());
        contentValues.put(EntertainmentTable.COL_OPTION_10, entertainment.getOption10());
        contentValues.put(EntertainmentTable.COL_TOTAL, entertainment.getTotal());

        long result = database.insert(EntertainmentTable.TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public boolean updateEntertainment(EntertainmentModelDB entertainment) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EntertainmentTable.COL_TRAVEL_ID, entertainment.getTravelId());
        contentValues.put(EntertainmentTable.COL_OPTION_1, entertainment.getOption1());
        contentValues.put(EntertainmentTable.COL_OPTION_2, entertainment.getOption2());
        contentValues.put(EntertainmentTable.COL_OPTION_3, entertainment.getOption3());
        contentValues.put(EntertainmentTable.COL_OPTION_4, entertainment.getOption4());
        contentValues.put(EntertainmentTable.COL_OPTION_5, entertainment.getOption5());
        contentValues.put(EntertainmentTable.COL_OPTION_6, entertainment.getOption6());
        contentValues.put(EntertainmentTable.COL_OPTION_7, entertainment.getOption7());
        contentValues.put(EntertainmentTable.COL_OPTION_8, entertainment.getOption8());
        contentValues.put(EntertainmentTable.COL_OPTION_9, entertainment.getOption9());
        contentValues.put(EntertainmentTable.COL_OPTION_10, entertainment.getOption10());
        contentValues.put(EntertainmentTable.COL_TOTAL, entertainment.getTotal());
        String whereClause = AirfareTable.COL_TRAVEL_ID + " = ?";
        String[] whereArgs = { String.valueOf(entertainment.getTravelId()) };

        long result = database.update(EntertainmentTable.TABLE_NAME, contentValues,  whereClause, whereArgs);
        return result != -1;
    }
}
