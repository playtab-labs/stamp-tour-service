ALTER TABLE spot
    ALTER COLUMN name TYPE JSONB USING jsonb_build_object('ko', name);

ALTER TABLE spot
    ALTER COLUMN description TYPE JSONB USING CASE
        WHEN description IS NULL THEN NULL
        ELSE jsonb_build_object('ko', description)
    END;
