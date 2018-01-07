package com.aieme.pleasedheart.models;

import java.sql.Date;

public class ReviewAverage {

    private Date startDate;
    private Customer customer;
    private Restaurant restaurant;
    private float avgScoreService;
    private int avgScoreFood;
    private int avgScoreEnvironment;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public float getAvgScoreService() {
        return avgScoreService;
    }

    public void setAvgScoreService(float avgScoreService) {
        this.avgScoreService = avgScoreService;
    }

    public int getAvgScoreFood() {
        return avgScoreFood;
    }

    public void setAvgScoreFood(int avgScoreFood) {
        this.avgScoreFood = avgScoreFood;
    }

    public int getAvgScoreEnvironment() {
        return avgScoreEnvironment;
    }

    public void setAvgScoreEnvironment(int avgScoreEnvironment) {
        this.avgScoreEnvironment = avgScoreEnvironment;
    }
}
