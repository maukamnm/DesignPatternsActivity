package business;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Album;
import beans.ResponseDataModel;
import util.AlbumNotFoundException;

@Path("/music")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class RestService {
	@EJB
	MusicManagerInterface service;

	@GET
	@Path("/getalbumx/{title}/{artist}/{year}")
	@Produces(MediaType.APPLICATION_XML)
	public ResponseDataModel getAlbumsAsXml(@PathParam("title") String title, @PathParam("artist") String artist, @PathParam("year") int year) {
		try {
		Album album =  service.getAlbum(new Album(title, artist, year));
		return new ResponseDataModel(0, "", album);
		} catch(AlbumNotFoundException e) {
			return new ResponseDataModel(404, "Could not find the album with filters specified.", null);
		} 
	}	
	@GET
	@Path("/getalbumj/{title}/{artist}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDataModel getAlbumsAsJson(@PathParam("title") String title, @PathParam("artist") String artist, @PathParam("year") int year) {
		Album album =  service.getAlbum(new Album(title, artist, year));
		return new ResponseDataModel(0, "", album); 
	}

}