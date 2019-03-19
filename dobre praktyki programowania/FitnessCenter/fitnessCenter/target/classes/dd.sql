--
-- Plik wygenerowany przez SQLiteStudio v3.2.1 dnia pon. mar 18 21:33:03 2019
--
-- Użyte kodowanie tekstu: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Tabela: activity
CREATE TABLE activity (
    activity_id               INTEGER      PRIMARY KEY AUTOINCREMENT,
    name                      VARCHAR (30),
    type                      VARCHAR (30),
    leader                    VARCHAR (30),
    place                     TEXT,
    number_of_free_places     INT (3),
    number_of_occupied_places INT (3),
    price                     INT (4),
    time                      VARCHAR (15),
    earnings                  INT (6),
    day                       VARCHAR (7) 
);

INSERT INTO activity (
                         activity_id,
                         name,
                         type,
                         leader,
                         place,
                         number_of_free_places,
                         number_of_occupied_places,
                         price,
                         time,
                         earnings,
                         day
                     )
                     VALUES (
                         1,
                         'Morning fitness',
                         'Fitness',
                         'Mack',
                         'Room 4',
                         13,
                         2,
                         60,
                         '8:00',
                         NULL,
                         'MON'
                     );

INSERT INTO activity (
                         activity_id,
                         name,
                         type,
                         leader,
                         place,
                         number_of_free_places,
                         number_of_occupied_places,
                         price,
                         time,
                         earnings,
                         day
                     )
                     VALUES (
                         2,
                         'Evening fitness',
                         'Fitness',
                         'Eve',
                         'Room 4',
                         14,
                         1,
                         70,
                         '18:00',
                         NULL,
                         'TUE'
                     );


-- Tabela: administrator
CREATE TABLE administrator (
    administrator_id INTEGER      PRIMARY KEY AUTOINCREMENT,
    first_name       VARCHAR (15),
    last_name        VARCHAR (20),
    login            VARCHAR (20),
    password         VARCHAR (20) 
);

INSERT INTO administrator (
                              administrator_id,
                              first_name,
                              last_name,
                              login,
                              password
                          )
                          VALUES (
                              1,
                              'admin',
                              'admin',
                              'admin',
                              'admin'
                          );

INSERT INTO administrator (
                              administrator_id,
                              first_name,
                              last_name,
                              login,
                              password
                          )
                          VALUES (
                              2,
                              'Karol',
                              'Masluch',
                              'Login',
                              'Password'
                          );


-- Tabela: customer
CREATE TABLE customer (
    customer_id      INTEGER      PRIMARY KEY AUTOINCREMENT,
    first_name       VARCHAR (15),
    last_name        VARCHAR (20),
    login            VARCHAR (20),
    password         VARCHAR (20),
    telephone_number INT (12),
    funds            INT (6) 
);

INSERT INTO customer (
                         customer_id,
                         first_name,
                         last_name,
                         login,
                         password,
                         telephone_number,
                         funds
                     )
                     VALUES (
                         1,
                         'Eve',
                         'Seven',
                         'seven',
                         'seven',
                         999888777,
                         2000
                     );

INSERT INTO customer (
                         customer_id,
                         first_name,
                         last_name,
                         login,
                         password,
                         telephone_number,
                         funds
                     )
                     VALUES (
                         2,
                         'Natalie',
                         'Not',
                         'not',
                         'not',
                         444555666,
                         1000
                     );

INSERT INTO customer (
                         customer_id,
                         first_name,
                         last_name,
                         login,
                         password,
                         telephone_number,
                         funds
                     )
                     VALUES (
                         3,
                         'Josh',
                         'Belt',
                         'belt',
                         'belt',
                         666777555,
                         1500
                     );


-- Tabela: selected_activities
CREATE TABLE selected_activities (
    customer_id INT (11) REFERENCES customer (customer_id),
    activity_id INT (11) REFERENCES activity (activity_id) 
);

INSERT INTO selected_activities (
                                    customer_id,
                                    activity_id
                                )
                                VALUES (
                                    3,
                                    2
                                );

INSERT INTO selected_activities (
                                    customer_id,
                                    activity_id
                                )
                                VALUES (
                                    2,
                                    1
                                );

INSERT INTO selected_activities (
                                    customer_id,
                                    activity_id
                                )
                                VALUES (
                                    1,
                                    1
                                );


-- Wyzwalacz: number_of_participants
CREATE TRIGGER number_of_participants
         AFTER UPDATE OF number_of_occupied_places
            ON activity
      FOR EACH ROW
BEGIN
    UPDATE activity
       SET number_of_free_places = 15 - new.number_of_occupied_places;
END;


-- Wyzwalacz: number_of_participants1
CREATE TRIGGER number_of_participants1
        BEFORE UPDATE OF number_of_free_places
            ON activity
      FOR EACH ROW
BEGIN
    UPDATE activity
       SET number_of_free_places = 0
     WHERE new.number_of_free_places < 0;
END;


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
