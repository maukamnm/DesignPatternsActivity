package business;

import javax.ejb.Local;

import beans.Album;
import util.TracksNotFoundException;

@Local
public interface MusicManagerInterface {
	
	public Album addAlbum(Album album) throws TracksNotFoundException;

}