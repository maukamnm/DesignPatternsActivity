package business;

import beans.Album;
import beans.Track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;


@Stateless
@Local(TrackFinderInterface.class)
@Alternative
@LocalBean
public class TrackFinder1 implements TrackFinderInterface {
	HashMap<String, List<Track>> tracks;
    public TrackFinder1() {
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


    public List<Track> getTracks(Album album) {
		return StorageController.getMap().get(album);
}
}