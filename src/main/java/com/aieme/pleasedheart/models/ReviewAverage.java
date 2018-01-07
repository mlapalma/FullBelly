package com.aieme.pleasedheart.models;

import java.sql.Date;

public class ReviewAverage {

    private Date startDate;
    private Date endDate;
    private Customer customer;
    private Restaurant restaurant;
    private float avgScoreService;
    private float avgScoreFood;
    private float avgScoreEnvironment;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public float getAvgScoreFood() {
        return avgScoreFood;
    }

    public void setAvgScoreFood(float avgScoreFood) {
        this.avgScoreFood = avgScoreFood;
    }

    public float getAvgScoreEnvironment() {
        return avgScoreEnvironment;
    }

    public void setAvgScoreEnvironment(float avgScoreEnvironment) {
        this.avgScoreEnvironment = avgScoreEnvironment;
    }
}
