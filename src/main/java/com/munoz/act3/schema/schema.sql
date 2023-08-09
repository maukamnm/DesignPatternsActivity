CREATE TABLE design_patterns.Album (
                                       title VARCHAR(50) NOT NULL,
                                       artist VARCHAR(25) NOT NULL,
                                       year INT NOT NULL,
                                       PRIMARY KEY (title, artist, year)
);CREATE TABLE track (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(100) NOT NULL,
                         number INT NOT NULL,
                         album_id INT NOT NULL,
                         FOREIGN KEY (album_id) REFERENCES album(id)
  );