CREATE ROLE "aerotool" WITH SUPERUSER;
CREATE USER "aerotool-ro" WITH PASSWORD 'aerotool-ro' IN ROLE "aerotool";
CREATE USER "aerotool-dml" WITH PASSWORD 'aerotool-dml' IN ROLE "aerotool";
CREATE USER "aerotool-app" WITH PASSWORD 'aerotool-app' IN ROLE "aerotool";

ALTER USER "aerotool-app" SET search_path = public, aerotool;

CREATE DATABASE "aerotool" WITH OWNER "aerotool-app";