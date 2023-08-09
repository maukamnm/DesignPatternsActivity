package com.munoz.act3.dao;
import com.munoz.act3.beans.*;
import com.munoz.act3.service.JDBCConnection;
import jakarta.ejb.Stateless;

@Stateless
public class AlbumDAOImpl implements AlbumDAO {
    @Override
    public void addAlbum(Album album) {
        JDBCConnection connection = new JDBCConnection();
        connection.saveNewAlbum(album);
    }
}
