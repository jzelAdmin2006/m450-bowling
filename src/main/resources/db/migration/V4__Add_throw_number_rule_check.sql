CREATE FUNCTION get_frame_pins(@frame_id INT)
    RETURNS INT
AS
BEGIN
    RETURN (SELECT SUM(pins_hit)
            FROM throw
            WHERE frame_id = @frame_id)
END;
GO

CREATE FUNCTION get_first_throw_pins(@frame_id INT)
    RETURNS INT
AS
BEGIN
    RETURN (SELECT pins_hit
            FROM throw
            WHERE frame_id = @frame_id
              AND throw_number = 1)
END;
GO

ALTER TABLE throw
    ADD CONSTRAINT throw_number_rule
        CHECK (
                    throw_number = 1
                OR
                    throw_number = 2
                        AND
                    (dbo.get_first_throw_pins(frame_id) < 10 OR frame_id = 10)
                OR
                    throw_number = 3
                        AND
                    dbo.get_frame_pins(frame_id) - pins_hit >= 10
            );

ALTER TABLE throw
    ADD CONSTRAINT hit_pins_rule
        CHECK (
                dbo.get_frame_pins(frame_id) BETWEEN 0 AND 10
                OR
                throw_number = 3
                    AND
                dbo.get_first_throw_pins(frame_id) = 10
            );
