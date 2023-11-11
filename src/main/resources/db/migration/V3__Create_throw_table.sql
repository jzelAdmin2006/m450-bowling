CREATE TABLE throw
(
    id           INT PRIMARY KEY IDENTITY,
    frame_id     INT                                   NOT NULL,
    throw_number INT                                   NOT NULL UNIQUE,
    pins_hit     INT CHECK (pins_hit BETWEEN 0 AND 10) NOT NULL,
    FOREIGN KEY (frame_id) REFERENCES frame (id) ON DELETE CASCADE ON UPDATE CASCADE
);
