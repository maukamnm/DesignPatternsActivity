package business;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import beans.Album;

@Startup;
@Singleton;

public class Cache {
	
	public void init() {
		System.out.println("The init method");
	}
	
	public Album getObject(Album album) {
		System.out.println("get Album method");
	}
	
	public void putObject(Album album) {
		System.out.println("put Album method");
	}

}