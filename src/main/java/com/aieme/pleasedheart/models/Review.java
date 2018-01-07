package com.aieme.pleasedheart.models;

import java.sql.Date;

public class Review {

    private int id;
    private Date date;
    private Customer customer;
    private Restaurant restaurant;
    private int scoreService;
    private int scoreFood;
    private int scoreEnvironment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getScoreService() {
        return scoreService;
    }

    public void setScoreService(int scoreService) {
        this.scoreService = scoreService;
    }

    public int getScoreFood() {
        return scoreFood;
    }

    public void setScoreFood(int scoreFood) {
        this.scoreFood = scoreFood;
    }

    public int getScoreEnvironment() {
        return scoreEnvironment;
    }

    public void setScoreEnvironment(int scoreEnvironment) {
        this.scoreEnvironment = scoreEnvironment;
    }

}
