CREATE TABLE frame
(
    id           INT PRIMARY KEY IDENTITY,
    game_id      INT                                              NOT NULL,
    frame_number INT CHECK (frame_number BETWEEN 1 AND 10) UNIQUE NOT NULL,
    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE ON UPDATE CASCADE
);
