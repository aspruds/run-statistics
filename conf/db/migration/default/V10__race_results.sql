-- Classification Types

CREATE TABLE classification_types (
  id SERIAL NOT NULL,
  name VARCHAR(200) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX classification_types_idx_name ON venue_types(name);

INSERT INTO classification_types(name, created_at, updated_at) VALUES ('LM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('M', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('MK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('I', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('II', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('III', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('I j.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('II j.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('III j.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Race Results

CREATE TABLE race_results (
  id SERIAL NOT NULL,
  race_id BIGINT NOT NULL REFERENCES races(id),
  race_distance_id BIGINT NOT NULL REFERENCES race_distances(id),
  time BIGINT,
  distance DECIMAL(10,3),
  points SMALLINT,
  rank SMALLINT,
  rank_text VARCHAR(255),
  age_group_id BIGINT REFERENCES age_groups(id),
  classification_type_id BIGINT REFERENCES classification_types(id),
  wind DECIMAL(3,3),
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

CREATE INDEX race_results_idx_race_id ON race_results(race_id);
CREATE INDEX race_results_idx_race_distance_id ON race_results(race_distance_id);
CREATE INDEX race_results_idx_age_group_id ON race_results(age_group_id);
CREATE INDEX race_results_idx_classification_type_id ON race_results(classification_type_id);