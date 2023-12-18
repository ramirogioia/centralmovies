DROP TABLE IF EXISTS platforms;
DROP TABLE IF EXISTS search_cache;
DROP TABLE IF EXISTS movies_platforms;
DROP TABLE IF EXISTS movies;


CREATE TABLE movies (
  movie_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  imdb_id VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  actors VARCHAR(255),
  year INT,
  ranking INT,
  image VARCHAR(255)
);

CREATE TABLE platforms (
  platform_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  is_online BOOLEAN NOT NULL
);

CREATE TABLE movies_platforms (
  movie_id INT NOT NULL,
  platform_id INT NOT NULL,
  registered_date TIMESTAMP NOT NULL,
  PRIMARY KEY (movie_id, platform_id),
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
  FOREIGN KEY (platform_id) REFERENCES platforms(platform_id)
);

CREATE TABLE search_cache (
  id INT AUTO_INCREMENT PRIMARY KEY,
  query VARCHAR(255),
  movie_id INT NOT NULL,
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);