package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.Album;
import beans.Track;
import util.DatabaseException;
import util.data.DataAccessInterface;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public abstract class MusicDataService implements DataAccessInterface<Album>
{
    public MusicDataService() 
    {
    }
	public boolean create(Album album)
	{
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3360/designPatterns";
		String username = "root";
		String password = "root";
		
		try 
		{
			conn = DriverManager.getConnection(url, username, password);

			String sql1 = String.format("INSERT INTO  ALBUM(TITLE, ARTIST, YEAR) VALUES('%s', '%s', %d)", album.getTitle(), album.getArtist(), album.getYear());
			Statement stmt1 = conn.createStatement();
			stmt1.executeUpdate(sql1);
			String sql2= "SELECT LAST_INSERT_ID() AS LAST_ID FROM ALBUM";
			ResultSet rs = stmt1.executeQuery(sql2);
			rs.next();
			String albumId = rs.getString("LAST_ID");
			rs.close();
			stmt1.close();
			Statement stmt2 = conn.createStatement();
			for(Track track : album.getTracks())
			{
				String sql3 = String.format("INSERT INTO TRACK(ALBUM_ID, TITLE, NUMBER) VALUES(%d, '%s', %d)", Integer.valueOf(albumId), track.getName(), track.getNumber());
				stmt2.executeUpdate(sql3);
			}
			stmt2.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		finally {
			if(conn != null)
			{
				try 
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
		}
		return true;
	}
	
	@Override
	public Album findById(int id)
	{
		return null;
	}

    public List<Album> findAll() 
    {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3360/designPatterns";
		String username = "root";
		String password = "root";
		
		List<Album> albums = new ArrayList<Album>();
		try 
		{
			conn = DriverManager.getConnection(url, username, password);
			
			String sql1 = "SELECT * FROM ALBUM";
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while(rs1.next())
			{
				Album album = new Album(rs1.getString("TITLE"), rs1.getString("ARTIST"), rs1.getInt("YEAR"));
				
				List<Track> tracks = new ArrayList<Track>();
				String sql2 = "SELECT * FROM TRACK WHERE ALBUM_ID = " + rs1.getInt("ID");
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt1.executeQuery(sql2);
				while(rs2.next())
				{
					tracks.add(new Track(rs2.getString("TITLE"), rs2.getInt("NUMBER")));
				}
				rs2.close();
				stmt2.close();
				
				album.setTracks(tracks);
				albums.add(album);
			}
			
			rs1.close();
			stmt1.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		finally
		{
			if(conn != null)
			{
				try 
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
		}
		
		return albums;
    }

	public Album findBy(Album album)
	{
		Connection conn = null;
		String url = "jdbc:mysql://localhost:8889/music";
		String username = "root";
		String password = "root";
		try {
			String sql1 = String.format("SELECT * FROM ALBUM WHERE TITLE='%S' AND ARTIST='%S' AND YEAR=%d",
					album.getTitle(), album.getArtist(), album.getYear());
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if (!rs1.next()) {
				rs1.close();
				stmt1.close();
				return null;
			}

			album.setTitle(rs1.getString("TITLE"));
			album.setArtist(rs1.getString("ARTIST"));
			album.setYear(rs1.getInt("YEAR"));
				
			List<Track> tracks = new ArrayList<Track>();
			String sql2 = "SELECT * FROM TRACK WHERE ALBUM_ID = " + rs1.getInt("ID");
			Statement stmt2 = conn.createStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);
			while(rs2.next())
			{
				tracks.add(new Track(rs2.getString("TITLE"), rs2.getInt("NUMBER")));
			}				
			album.setTracks(tracks);
			rs2.close();
			stmt2.close();
			rs1.close();
			stmt1.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		finally
		{
			if(conn != null)
			{
				try 
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
		}
		return album;
	}

	public boolean update(Album order)
	{
		return true;
	}
	
	public boolean delete(Album order)
	{
		return false;
	}
}