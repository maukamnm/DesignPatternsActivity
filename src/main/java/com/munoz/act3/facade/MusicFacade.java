package com.munoz.act3.facade;

import com.munoz.act3.beans.*;
import com.munoz.act3.business.MusicManagerInterface;
import com.munoz.act3.util.TracksNotFoundException;
import jakarta.ejb.EJB;

public class MusicFacade {
    @EJB
    private MusicManagerInterface musicManager;

    public Album addAlbumWithTracks(Album album) throws TracksNotFoundException {
        try {
            Album updatedAlbum = musicManager.addAlbum(album);
            return updatedAlbum;
        } catch (TracksNotFoundException e) {
            throw e;
        }
    }
}
