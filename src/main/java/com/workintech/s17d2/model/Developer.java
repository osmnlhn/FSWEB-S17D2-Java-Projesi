package com.workintech.s17d2.model;

public class Developer {
    private  int id;
    private String name;
    private Double salary;
    private Experince experince;

    public Developer(int id, String name, Double salary, Experince experince) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experince = experince;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Experince getExperince() {
        return experince;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setExperince(Experince experince) {
        this.experince = experince;
    }
}