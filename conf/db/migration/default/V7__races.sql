CREATE TABLE races (
  id SERIAL NOT NULL,
  name VARCHAR(200) NOT NULL,
  race_date DATE NOT NULL,
  country_id INTEGER REFERENCES countries(id),
  url VARCHAR(4000),
  skriesim_id INTEGER,
  sportlat_id INTEGER,
  noskrien_id INTEGER,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX races_idx_skriesim_id ON races(skriesim_id);
CREATE UNIQUE INDEX races_idx_sportlat_id ON races(sportlat_id);
CREATE UNIQUE INDEX races_idx_noskrien_id ON races(noskrien_id);
CREATE INDEX races_idx_country_id ON races(country_id);