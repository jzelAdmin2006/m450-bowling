CREATE TABLE Frame (
    ID INT PRIMARY KEY IDENTITY,
    GameID INT NOT NULL,
    FrameNumber INT CHECK (FrameNumber BETWEEN 1 AND 10) UNIQUE NOT NULL,
    FOREIGN KEY (GameID) REFERENCES Game(ID) ON DELETE CASCADE ON UPDATE CASCADE
);
