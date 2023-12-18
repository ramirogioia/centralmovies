INSERT INTO platforms (name, description, is_online) VALUES
  ('NETFLIX', 'netflix', true),
  ('PRIME_VIDEO', 'amazon prime video', true),
  ('YOUTUBE', 'youtube', true),
  ('GOOGLE_PLAY', 'google play', true),
  ('DISNEY_PLUS', 'disney plus', true),
  ('APPLE_TV', 'apple tv', true),
  ('STAR_PLUS', 'star plus', true),
  ('CLARO', 'claro', true),
  ('MOVISTAR_PLAY', 'movistar play', true),
  ('DIRECT_TV', 'direct tv', true),
  ('FLOW', 'flow', true),
  ('HBO_MAX', 'hbo max', true),
  ('PARAMOUNT', 'paramount+', true);
  
INSERT INTO movies
(imdb_id, name, actors, year, ranking, image)
VALUES('tt0363771', 'The Chronicles of Narnia: The Lion, the Witch and the Wardrobe', 'Tilda Swinton, Georgie Henley', 2005, 1013, 'https://m.media-amazon.com/images/M/MV5BMTc0NTUwMTU5OV5BMl5BanBnXkFtZTcwNjAwNzQzMw@@._V1_.jpg');

SET @movieId = LAST_INSERT_ID();

INSERT INTO movies_platforms
(movie_id, platform_id, registered_date)
VALUES(@movieId, 5, CURRENT_TIMESTAMP);