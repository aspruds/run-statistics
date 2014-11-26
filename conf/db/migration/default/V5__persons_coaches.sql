CREATE TABLE persons_coaches (
    id SERIAL NOT NULL,
    person_id INTEGER REFERENCES persons(id),
    coach_id INTEGER REFERENCES persons(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by INTEGER,
    PRIMARY KEY (id)
);

CREATE INDEX persons_coaches_idx_person_id ON persons_coaches(person_id);
CREATE INDEX persons_coaches_idx_coach_id ON persons_coaches(coach_id);
CREATE UNIQUE INDEX persons_coaches_idx_person_coach ON persons_coaches(person_id, coach_id);