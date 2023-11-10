CREATE FUNCTION get_frame_pins(@frameID INT)
RETURNS INT
AS
BEGIN
    RETURN (
                SELECT SUM(PinsHit)
                FROM Throw
                WHERE FrameID = @frameID
            )
END;
GO

CREATE FUNCTION get_first_throw_pins(@frameID INT)
RETURNS INT
AS
BEGIN
    RETURN (
                SELECT PinsHit
                FROM Throw
                WHERE FrameID = @frameID
                AND ThrowNumber = 1
            )
END;
GO

ALTER TABLE Throw
ADD CONSTRAINT ThrowNumberRule
    CHECK (
            ThrowNumber IN (1, 2)
            OR
            ThrowNumber = 3
            AND
            dbo.get_frame_pins(FrameID) - PinsHit >= 10
        );

ALTER TABLE Throw
ADD CONSTRAINT HitPinsRule
    CHECK (
            dbo.get_frame_pins(FrameID) BETWEEN 0 AND 10
            OR
            ThrowNumber = 3
            AND
            dbo.get_first_throw_pins(FrameID) = 10
        );
