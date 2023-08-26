package business;

import beans.Album;
import beans.Track;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class TrackFinder2
 */
@Stateless
@Local(TrackFinderInterface.class)
@LocalBean
public class TrackFinder2 implements TrackFinderInterface {

    public TrackFinder2() {
    }

    public List<Track> getTracks(Album album) {
			return null;
    }

}