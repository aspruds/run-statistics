CREATE TABLE clubs (
    id SERIAL NOT NULL,
    name VARCHAR(200) NOT NULL,
    country_id INTEGER REFERENCES countries(id),
    title TEXT,
    description TEXT,
    full_description TEXT,
    skriesim_id INTEGER,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by INTEGER,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX clubs_idx_skriesim_id ON clubs(skriesim_id);
CREATE UNIQUE INDEX clubs_idx_name ON clubs(name);
CREATE INDEX clubs_idx_country_id ON clubs(country_id);