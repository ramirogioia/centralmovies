INSERT IGNORE INTO platforms (name, description, stream_url, is_online) VALUES
  ('NETFLIX', 'Netflix', 'https://www.netflix.com/', true),
  ('PRIME_VIDEO', 'Amazon Prime Video', 'https://www.primevideo.com/', true),
  ('YOUTUBE', 'Youtube', 'https://www.youtube.com/', true),
  ('GOOGLE_PLAY', 'Google Play Películas', 'https://play.google.com/', true),
  ('DISNEY_PLUS', 'Disney+', 'https://www.disneyplus.com/', true),
  ('APPLE_TV', 'Apple TV', 'https://www.apple.com/', true),
  ('STAR_PLUS', 'Star+', 'https://www.starplus.com/', true),
  ('CLARO', 'Claro', 'https://www.clarovideo.com/', true),
  ('MOVISTAR_PLAY', 'Movistar Play', 'https://tv.movistar.com/', true),
  ('DIRECT_TV', 'Direct TV', 'https://www.directvgo.com/', true),
  ('FLOW', 'Flow', 'https://www.flow.com/', true),
  ('HBO_MAX', 'HBO Max', 'https://www.hbomax.com/', true),
  ('PARAMOUNT', 'Paramount+', 'https://www.paramountplus.com/', true);
  
/*INSERT INTO movies
(imdb_id, name, actors, year, ranking, image)
VALUES('tt0363771', 'The Chronicles of Narnia: The Lion, the Witch and the Wardrobe', 'Tilda Swinton, Georgie Henley', 2005, 1013, 'https://m.media-amazon.com/images/M/MV5BMTc0NTUwMTU5OV5BMl5BanBnXkFtZTcwNjAwNzQzMw@@._V1_.jpg');

SET @movieId = LAST_INSERT_ID();

INSERT INTO movies_platforms
(movie_id, platform_id, registered_date)
VALUES(@movieId, 5, CURRENT_TIMESTAMP);*/