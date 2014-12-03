-- Venue Types

CREATE TABLE venue_types (
  id SERIAL NOT NULL,
  name VARCHAR(200) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX venue_types_idx_name ON venue_types(name);

INSERT INTO venue_types(name, created_at, updated_at) VALUES ('Cross Country', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO venue_types(name, created_at, updated_at) VALUES ('Indoor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO venue_types(name, created_at, updated_at) VALUES ('Stadium', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO venue_types(name, created_at, updated_at) VALUES ('Road', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Discipline Types

CREATE TABLE discipline_types (
  id SERIAL NOT NULL,
  name VARCHAR(200) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Running: Sprints', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Running: Middle-distance', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Running: Long-distance', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Running: Ultras', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Jumps', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Hurdles', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Race Walking', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Relays', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Throws', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Combined Events', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO discipline_types(name, created_at, updated_at) VALUES ('Other', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- Distance Types

CREATE TABLE distance_types (
  id SERIAL NOT NULL,
  name VARCHAR(200) NOT NULL,
  skriesim_name varchar(400),
  distance INTEGER,
  weight INTEGER,
  discipline_type_id BIGINT REFERENCES discipline_types(id),
  venue_type_id BIGINT REFERENCES venue_types(id),
  is_standard BOOLEAN,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

CREATE INDEX distance_types_idx_discipline_type_id ON distance_types(id);
CREATE INDEX distance_types_idx_venue_type_id ON venue_types(id);

-- Race Distances

CREATE TABLE race_distances (
  id SERIAL NOT NULL,
  race_id BIGINT NOT NULL REFERENCES races(id),
  name VARCHAR(200) NOT NULL,
  distance_type_id BIGINT REFERENCES distance_types(id),
  is_certified BOOLEAN,
  is_electronic_time BOOLEAN,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

CREATE INDEX race_distances_idx_distance_type_id ON distance_types(id);
CREATE INDEX race_distances_idx_race_id ON distance_types(id);