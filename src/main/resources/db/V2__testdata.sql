INSERT INTO platforms (name, is_online) VALUES
  ('NETFLIX', true),
  ('PRIME VIDEO', true),
  ('YOUTUBE', true),
  ('GOOGLE PLAY', true),
  ('DISNEY PLUS', true),
  ('APPLE TV', true),
  ('STAR PLUS', true),
  ('CLARO', true),
  ('MOVISTAR_PLAY', true),
  ('DIRECT TV', true),
  ('FLOW', true),
  ('HBO MAX', true);
  
INSERT INTO movies
(movie_id, imdb_id, name, actors, `year`, ranking, image)
VALUES(0363771321, 'tt0363771', 'The Chronicles of Narnia: The Lion, the Witch and the Wardrobe', 'Tilda Swinton, Georgie Henley', 2005, 1013, 'https://m.media-amazon.com/images/M/MV5BMTc0NTUwMTU5OV5BMl5BanBnXkFtZTcwNjAwNzQzMw@@._V1_.jpg');

INSERT INTO movies_platforms
(movie_id, platform_id, registered_date)
VALUES(0363771321, 5, CURRENT_TIMESTAMP);