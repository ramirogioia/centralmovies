IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'tu_esquema' AND table_name = 'flyway_schema_history') THEN
    DROP TABLE tu_esquema.flyway_schema_history;
END IF;

CREATE TABLE IF NOT EXISTS movies (
  movie_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  imdb_id VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  actors VARCHAR(255),
  year INT,
  ranking INT,
  image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS platforms (
  platform_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  stream_url VARCHAR(255) NOT NULL,
  is_online BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS movies_platforms (
  movie_id INT NOT NULL,
  platform_id INT NOT NULL,
  registered_date TIMESTAMP NOT NULL,
  PRIMARY KEY (movie_id, platform_id),
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
  FOREIGN KEY (platform_id) REFERENCES platforms(platform_id)
);

CREATE TABLE IF NOT EXISTS search_cache (
  id INT AUTO_INCREMENT PRIMARY KEY,
  query VARCHAR(255),
  movie_id INT NOT NULL,
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);