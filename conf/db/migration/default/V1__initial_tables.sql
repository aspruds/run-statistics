CREATE TABLE countries(
    code CHAR(2) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(code)
);

CREATE TABLE url_cache (
    id SERIAL NOT NULL,
    url VARCHAR(4096) NOT NULL,
    content TEXT NOT NULL,
    date_visited TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX url_cache_idx_url ON url_cache(url);