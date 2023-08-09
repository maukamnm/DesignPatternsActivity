package com.munoz.act3.beans;



import jakarta.annotation.ManagedBean;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean("album")
@RequestScope
public class Album implements Serializable {

    @NotNull
    @Size(min = 5, max = 50)
    private String title;

    @NotNull
    @Size(min = 5, max = 25)
    private String artist;

    @Min(1920)
    @Max(2020)
    private int year;

    private List<Track> tracks;

    public Album() {
        title = "";
        artist = "";
        year = 0;
        tracks = new ArrayList<>();
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
