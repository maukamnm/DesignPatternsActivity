package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Album;
import business.MusicManager;
import util.TracksNotFoundException;

@ManagedBean
@ViewScoped
public class AlbumController
{
	public String onSubmit(Album album)
	{
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
        MusicManager manager = new MusicManager();
        try
        {
            album = manager.addAlbum(album);
        } 
        catch (TracksNotFoundException e)
        {
            System.out.println("Album is not found");
        }
        
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
        return "AddAlbumResponse.xhtml";
	}
}