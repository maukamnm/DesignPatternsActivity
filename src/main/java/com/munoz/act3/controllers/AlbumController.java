package com.munoz.act3.controllers;

import com.munoz.act3.beans.*;
import com.munoz.act3.dao.AlbumDAO;
import com.munoz.act3.dao.AlbumDAOImpl;
import com.munoz.act3.facade.MusicFacade;
import com.munoz.act3.util.TracksNotFoundException;
import jakarta.annotation.ManagedBean;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean("albumController")
@RequestScope
public class AlbumController {
    private MusicFacade musicFacade;
    private AlbumDAO albumDAO;
    public AlbumController() {
        musicFacade = new MusicFacade();
    }

    public String onSubmit(Album album) {
        try {
            Album updatedAlbum = musicFacade.addAlbumWithTracks(album);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", updatedAlbum);
            return "AddAlbumResponse.xhtml";
        } catch (TracksNotFoundException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tracks not found", "No tracks found for the album");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null; // Or navigate to an error page
        }
    }
}
