CREATE TABLE IF NOT EXISTS spot (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS stamp_visit (
    id         BIGSERIAL PRIMARY KEY,
    user_id    VARCHAR(255) NOT NULL,
    spot_id    BIGINT       NOT NULL REFERENCES spot (id),
    created_at TIMESTAMP    NOT NULL,
    CONSTRAINT uk_user_spot UNIQUE (user_id, spot_id)
);
