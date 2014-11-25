CREATE TABLE perons_clubs (
    id SERIAL NOT NULL,
    person_id INTEGER REFERENCES persons(id),
    club_id INTEGER REFERENCES clubs(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by INTEGER,
    PRIMARY KEY (id)
);

CREATE INDEX persons_clubs_idx_person_id ON perons_clubs(person_id);
CREATE INDEX persons_clubs_idx_club_id ON perons_clubs(club_id);
CREATE UNIQUE INDEX persons_clubs_idx_club_person ON perons_clubs(person_id, club_id);