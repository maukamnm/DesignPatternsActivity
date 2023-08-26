package business;


import util.AlbumNotFoundException;
import util.TracksNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import beans.Album;
import data.DataInterface;

/**
 * Session Bean implementation class MusicManager
 */
@Stateless
@Local(MusicManagerInterface.class)
@Local(Cache.class)
@LocalBean
public class MusicManager implements MusicManagerInterface {
	@EJB
    DataInterface<Album> dao;
    @Inject
	TrackFinderInterface tf;
	
    public MusicManager() {}

    @Override
    public Album addAlbum(Album album) {
    	Album created = this.dao.create(album);
    	if(created.getTracks().size() == 0) throw new TracksNotFoundException("No tracks found");
		return created;
    }
    @Override
	public Album getAlbum(Album album) {
		Album found = this.dao.findBy(album);
		if(found == null) throw new AlbumNotFoundException("Could not find album ");
		return found;
	} 
}