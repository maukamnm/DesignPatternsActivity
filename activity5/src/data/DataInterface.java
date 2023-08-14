package data;

import beans.Album;

public interface DataInterface {
	Album create(Album album);
	Album findBy(Album album);
}
