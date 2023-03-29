#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER kyle;
    CREATE DATABASE asset_dev;
    GRANT ALL PRIVILEGES ON DATABASE asset_dev TO kyle;
    CREATE DATABASE asset;
    GRANT ALL PRIVILEGES ON DATABASE asset TO kyle;
EOSQL