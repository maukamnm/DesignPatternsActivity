package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import beans.Album;
import beans.Track;
import util.TracksNotFoundException;

@Stateless
@Local(MusicManagerInterface.class)
@LocalBean
public class MusicManager implements MusicManagerInterface {
	HashMap<String, List<Track>> tracks;

	public MusicManager() {
		tracks = new HashMap<String, List<Track>>();
		List<Track> tracks1 = new ArrayList<Track>();
		tracks1.add(new Track("Man in the Mirror", 1));
		tracks1.add(new Track("Electric sunrise", 2));
		tracks1.add(new Track("GOAT", 3));
		tracks1.add(new Track("This is it", 4));
		tracks1.add(new Track("Flyin high", 5));
		tracks1.add(new Track("What is new", 6));
		tracks1.add(new Track("Crosty", 7));
		tracks1.add(new Track("Saucy", 8));
		tracks1.add(new Track("New levels", 9));
		tracks.put("The random ones", tracks1);
	}
	private List<Track> getTracks(Album album) {
		String key = album.getArtist() + " - " + album.getTitle() + " - " + album.getYear();
		if (tracks.containsKey(key)) {
			return tracks.get(key);
		} else {
			return new ArrayList<Track>();
		}
	}
	@Override
	public Album addAlbum(Album album) throws TracksNotFoundException {
		album.setTracks(getTracks(album));
		if (album.getNumberTracks() == 0)
			throw new TracksNotFoundException();
		return album;
	}


}