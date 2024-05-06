package models;

public class EntertainmentModelDB {
    private long id;
    private long travelId;
    private int option1;
    private int option2;
    private int option3;
    private int option4;
    private int option5;
    private int option6;
    private int option7;
    private int option8;
    private int option9;
    private int option10;
    private double total;

    public EntertainmentModelDB() {
    }

    public EntertainmentModelDB(
            long travelId,
            int option1,
            int option2,
            int option3,
            int option4,
            int option5,
            int option6,
            int option7,
            int option8,
            int option9,
            int option10,
            double total
    ) {
        this.travelId = travelId;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.option7 = option7;
        this.option8 = option8;
        this.option9 = option9;
        this.option10 = option10;
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

    public int getOption1() {
        return option1;
    }

    public void setOption1(int option1) {
        this.option1 = option1;
    }

    public int getOption2() {
        return option2;
    }

    public void setOption2(int option2) {
        this.option2 = option2;
    }

    public int getOption3() {
        return option3;
    }

    public void setOption3(int option3) {
        this.option3 = option3;
    }

    public int getOption4() {
        return option4;
    }

    public void setOption4(int option4) {
        this.option4 = option4;
    }

    public int getOption5() {
        return option5;
    }

    public void setOption5(int option5) {
        this.option5 = option5;
    }

    public int getOption6() {
        return option6;
    }

    public void setOption6(int option6) {
        this.option6 = option6;
    }

    public int getOption7() {
        return option7;
    }

    public void setOption7(int option7) {
        this.option7 = option7;
    }

    public int getOption8() {
        return option8;
    }

    public void setOption8(int option8) {
        this.option8 = option8;
    }

    public int getOption9() {
        return option9;
    }

    public void setOption9(int option9) {
        this.option9 = option9;
    }

    public int getOption10() {
        return option10;
    }

    public void setOption10(int option10) {
        this.option10 = option10;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
