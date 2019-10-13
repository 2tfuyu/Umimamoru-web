CREATE DATABASE umimamoru;

USE umimamoru;

CREATE TABLE flow_data (
  net int,
  loc int,
  time datetime,
  flow double,
  direction double,
  angle double,
  tobank double,
  primary key (net, loc, time)
);

CREATE TABLE net_data (
  net int,
  pref varchar(256),
  city varchar(256),
  ward varchar(256),
  beach varchar(256),
  lat double,
  lng double,
  primary key (net)
);

CREATE TABLE module_data (
  net int,
  loc int,
  pole int,
  lat double,
  lng double,
  angle double,
  primary key (net, loc)
);

CREATE TABLE pole_data (
  net int,
  loc int,
  lat double,
  lng double,
  primary key (net, loc)
);

CREATE TABLE range_data (
  net int,
  horizontal int,
  vertical int,
  primary key (net)
);

INSERT INTO net_data VALUES (0, "沖縄県", "名護市", "豊原", "シーグラスビーチ", 26.5120833, 128.0282313);

INSERT INTO pole_data VALUES (0, 5, 26.511755, 128.028338);
INSERT INTO pole_data VALUES (0, 6, 26.51168, 128.028475);
INSERT INTO pole_data VALUES (0, 7, 26.511676, 128.028586);
INSERT INTO pole_data VALUES (0, 8, 26.51166, 128.028697);

INSERT INTO module_data VALUES (0, 1, 5, 26.511621, 128.028338);
INSERT INTO module_data VALUES (0, 2, 6, 26.511614, 128.028475);
INSERT INTO module_data VALUES (0, 3, 7, 26.511605, 128.028586);
INSERT INTO module_data VALUES (0, 4, 8, 26.511601, 128.028697);

INSERT INTO flow_data VALUES (0, 1, '2019-09-26 22:05:00', 0.0, 180);
INSERT INTO flow_data VALUES (0, 2, '2019-09-26 22:05:00', 1.0, 180);
INSERT INTO flow_data VALUES (0, 3, '2019-09-26 22:05:00', 1.6, 180);
INSERT INTO flow_data VALUES (0, 4, '2019-09-26 22:05:00', 2.0, 180);
