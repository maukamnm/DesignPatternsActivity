package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Album;
import business.MusicManagerInterface;

@ManagedBean
@ViewScoped
public class AlbumController {
	@EJB
	MusicManagerInterface mgr;
	
	public String onFind(Album album) {
	
		FacesContext ctx = FacesContext.getCurrentInstance();
		Album found = null;
	    found = mgr.getAlbum(album);
	    
		ctx.getExternalContext().getRequestMap().put("album", found);
		ctx.getExternalContext().getRequestMap().put("trackCount", found.getNumberTracks());
		
		return "AddAlbumResponse.xhtml";
	}
	
	public String onSubmit(Album album) {
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		Album a = mgr.addAlbum(album);
		System.out.println("Printing----------");
		ctx.getExternalContext().getRequestMap().put("album", album);
		ctx.getExternalContext().getRequestMap().put("trackCount", album.getNumberTracks());
		
		return "AddAlbumResponse.xhtml";
	}
}