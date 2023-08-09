package com.munoz.act3.beans;

import jakarta.annotation.ManagedBean;
import org.springframework.web.context.annotation.RequestScope;

@ManagedBean("album")
@RequestScope
public class Track {
    private String title;
    private int number;

    public Track() {
        title = "";
        number = 0;
    }

    public Track(String title, int number) {
        this.title = title;
        this.number = number;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
