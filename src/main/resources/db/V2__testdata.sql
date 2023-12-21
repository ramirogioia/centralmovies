INSERT INTO platforms (name, description, is_online) VALUES
  ('NETFLIX', 'Netflix', true),
  ('PRIME_VIDEO', 'Amazon Prime Video', true),
  ('YOUTUBE', 'Youtube', true),
  ('GOOGLE_PLAY', 'Google Play Películas', true),
  ('DISNEY_PLUS', 'Disney Plus', true),
  ('APPLE_TV', 'Apple TV', true),
  ('STAR_PLUS', 'Star Plus', true),
  ('CLARO', 'Claro', true),
  ('MOVISTAR_PLAY', 'Movistar Play', true),
  ('DIRECT_TV', 'Direct TV', true),
  ('FLOW', 'Flow', true),
  ('HBO_MAX', 'Hbo Max', true),
  ('PARAMOUNT', 'Paramount +', true);
  
/*INSERT INTO movies
(imdb_id, name, actors, year, ranking, image)
VALUES('tt0363771', 'The Chronicles of Narnia: The Lion, the Witch and the Wardrobe', 'Tilda Swinton, Georgie Henley', 2005, 1013, 'https://m.media-amazon.com/images/M/MV5BMTc0NTUwMTU5OV5BMl5BanBnXkFtZTcwNjAwNzQzMw@@._V1_.jpg');

SET @movieId = LAST_INSERT_ID();

INSERT INTO movies_platforms
(movie_id, platform_id, registered_date)
VALUES(@movieId, 5, CURRENT_TIMESTAMP);*/