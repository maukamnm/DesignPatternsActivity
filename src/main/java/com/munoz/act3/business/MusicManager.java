package com.munoz.act3.business;

import com.munoz.act3.beans.*;
import com.munoz.act3.beans.Track;
import com.munoz.act3.dao.AlbumDAOImpl;
import com.munoz.act3.util.TracksNotFoundException;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class MusicManager implements MusicManagerInterface {
    private Map<String, List<Track>> trackInfo;

    public MusicManager() {
        trackInfo = new HashMap<>();
        // Initialize trackInfo with some sample data
        List<Track> tracks1 = new ArrayList<>();
        tracks1.add(new Track("Track 1", 1));
        tracks1.add(new Track("Track 2", 2));
        trackInfo.put("Artist1_Album1_2022", tracks1);

        List<Track> tracks2 = new ArrayList<>();
        tracks2.add(new Track("Track 1", 1));
        tracks2.add(new Track("Track 2", 2));
        tracks2.add(new Track("Track 3", 3));
        trackInfo.put("Artist2_Album2_2023", tracks2);
    }

    @Override
    public Album addAlbum(Album model) throws TracksNotFoundException {
        String key = model.getArtist() + "_" + model.getTitle() + "_" + model.getYear();
        List<Track> tracks = getTracks(key);
        model.setTracks(tracks);
        AlbumDAOImpl daoImpl = new AlbumDAOImpl();
        daoImpl.addAlbum(model);
        return model;
    }

    private List<Track> getTracks(String key) throws TracksNotFoundException {
        if (trackInfo.containsKey(key)) {
            return trackInfo.get(key);
        } else {
            throw new TracksNotFoundException("No tracks found for the album.");
        }
    }
}
