package restaurant.system.controller.dto;

import java.sql.Date;

public class SupplyEntityDto {
    private int quantity;
    private Date date;
    private int sold;
    private int leftover;
    private int other;

    // Constructors, getters, and setters


    public SupplyEntityDto() {
    }

    public SupplyEntityDto(int quantity, Date date, int sold, int leftover, int other) {
        this.quantity = quantity;
        this.date = date;
        this.sold = sold;
        this.leftover = leftover;
        this.other = other;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getLeftover() {
        return leftover;
    }

    public void setLeftover(int leftover) {
        this.leftover = leftover;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
}
