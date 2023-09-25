CREATE SCHEMA aerotool_platform;

ALTER SCHEMA aerotool_platform OWNER TO "aerotool";

DROP TYPE IF EXISTS aerotool_platform.promptuary CASCADE;

CREATE TABLE aerotool_platform.promptuary (
    login varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);

ALTER TABLE aerotool_platform.promptuary OWNER TO "aerotool";

ALTER TABLE aerotool_platform.promptuary
    ADD CONSTRAINT promptuary_pk PRIMARY KEY (login);

DROP TYPE IF EXISTS aerotool_platform.role CASCADE;

CREATE TYPE aerotool_platform.role AS ENUM (
    'ADMIN',
    'TEACHER',
    'STUDENT'
);

ALTER TYPE aerotool_platform.role OWNER TO "aerotool";

DROP TABLE IF EXISTS aerotool_platform.user CASCADE;

CREATE TABLE aerotool_platform.user (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    role aerotool_platform.role NOT NULL,
    promptuary_login aerotool_platform.promptuary NOT NULL
);

ALTER TABLE aerotool_platform.user OWNER TO "aerotool";

ALTER TABLE aerotool_platform.user
    ADD CONSTRAINT user_pk PRIMARY KEY (id);

DROP TABLE IF EXISTS aerotool_platform.tool_type CASCADE;

CREATE TYPE aerotool_platform.tool_type as ENUM (
    'PROPERTY',
    'COMMON'
);

ALTER TYPE aerotool_platform.tool_type OWNER TO "aerotool";

DROP TABLE IF EXISTS aerotool_platform.tool CASCADE;

CREATE TABLE aerotool_platform.tool (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255),
    type aerotool_platform.tool_type NOT NULL
);

ALTER TABLE aerotool_platform.tool OWNER TO "aerotool";

ALTER TABLE aerotool_platform.tool
    ADD CONSTRAINT tool_pk PRIMARY KEY (id);

DROP TYPE IF EXISTS aerotool_platform.tool_situation CASCADE;

CREATE TYPE aerotool_platform.tool_situation as ENUM (
    'BROKEN',
    'LOST',
    'BUSY',
    'FREE'
);

ALTER TYPE aerotool_platform.tool_situation OWNER TO "aerotool";

DROP TABLE IF EXISTS aerotool_platform.locate CASCADE;

CREATE TABLE aerotool_platform.locate (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255)
);

ALTER TABLE aerotool_platform.locate OWNER TO "aerotool";

ALTER TABLE aerotool_platform.locate
    ADD CONSTRAINT locate_pk PRIMARY KEY (id);

DROP TABLE IF EXISTS aerotool_platform.tool_item CASCADE;

CREATE TABLE aerotool_platform.tool_item (
    id uuid NOT NULL,
    patrimony varchar(255) NOT NULL,
    situation aerotool_platform.tool_situation NOT NULL,
    locate_id uuid NOT NULL,
    tool_id uuid NOT NULL
);

ALTER TABLE aerotool_platform.tool_item OWNER TO "aerotool";

ALTER TABLE aerotool_platform.tool_item
    ADD CONSTRAINT toolItem_pk PRIMARY KEY (id);

ALTER TABLE aerotool_platform.tool_item
    ADD CONSTRAINT toolItem_locate_id_fkey FOREIGN KEY (locate_id)
        REFERENCES aerotool_platform.locate(id) ON DELETE CASCADE;

ALTER TABLE aerotool_platform.tool_item
    ADD CONSTRAINT toolItem_tool_id_fkey FOREIGN KEY (tool_id)
        REFERENCES aerotool_platform.tool(id) ON DELETE CASCADE;

DROP TABLE IF EXISTS aerotool_platform.request CASCADE;

CREATE TABLE aerotool_platform.request (
    id uuid NOT NULL,
    request_date timestamp NOT NULL,
    user_id uuid NOT NULL
);

ALTER TABLE aerotool_platform.request OWNER TO "aerotool";

ALTER TABLE aerotool_platform.request
    ADD CONSTRAINT request_pk PRIMARY KEY (id);

ALTER TABLE aerotool_platform.request
    ADD CONSTRAINT request_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES aerotool_platform.user(id) ON DELETE CASCADE;

DROP TYPE IF EXISTS aerotool_platform.request_situation CASCADE;

CREATE TYPE aerotool_platform.request_situation as ENUM (
    'WAITING',
    'ACCEPTED',
    'REJECTED'
);

ALTER TYPE aerotool_platform.request_situation OWNER TO "aerotool";

DROP TABLE IF EXISTS aerotool_platform.line_request CASCADE;

CREATE TABLE aerotool_platform.line_request (
    id uuid NOT NULL,
    expected_return_date timestamp NOT NULL,
    real_return_date timestamp,
    expected_withdrawal_date timestamp NOT NULL,
    real_withdrawal_date timestamp,
    request_situation aerotool_platform.request_situation NOT NULL,
    tool_item_id uuid NOT NULL,
    request_id uuid NOT NULL
);

ALTER TABLE aerotool_platform.line_request OWNER TO "aerotool";

ALTER TABLE aerotool_platform.line_request
    ADD CONSTRAINT lineRequest_pk PRIMARY KEY (id);

ALTER TABLE aerotool_platform.line_request
    ADD CONSTRAINT lineRequest_tool_item_id_fkey FOREIGN KEY (tool_item_id)
        REFERENCES aerotool_platform.tool_item(id) ON DELETE CASCADE;

ALTER TABLE aerotool_platform.line_request
    ADD CONSTRAINT lineRequest_request_id_fkey FOREIGN KEY (request_id)
        REFERENCES aerotool_platform.request(id) ON DELETE CASCADE;

DROP TYPE IF EXISTS aerotool_platform.event_situation CASCADE;

CREATE TYPE aerotool_platform.event_type as ENUM (
    'NOTIFICATION',
    'SYSTEM_LOG',
    'ACTION_USERS'
);

ALTER TYPE aerotool_platform.event_type OWNER TO "aerotool";

DROP TYPE IF EXISTS aerotool_platform.event_situation CASCADE;

CREATE TYPE aerotool_platform.event_situation as ENUM (
    'SENT',
    'VISUALIZED',
    'NOT_VISUALIZED',
    'ACCEPTED',
    'REJECTED'
);

ALTER TYPE aerotool_platform.event_situation OWNER TO "aerotool";

DROP TABLE IF EXISTS aerotool_platform.event CASCADE;

CREATE TABLE aerotool_platform.event (
    id uuid NOT NULL,
    description varchar(255),
    event_date timestamp NOT NULL,
    event_type aerotool_platform.event_type NOT NULL,
    event_situation aerotool_platform.event_situation NOT NULL,
    user_responsible_id uuid NOT NULL,
    user_subject_id uuid NOT NULL
);

ALTER TABLE aerotool_platform.event OWNER TO "aerotool";

ALTER TABLE aerotool_platform.event
    ADD CONSTRAINT event_pk PRIMARY KEY (id);

ALTER TABLE aerotool_platform.event
    ADD CONSTRAINT event_user_id_fkey FOREIGN KEY (user_responsible_id)
        REFERENCES aerotool_platform.user(id) ON DELETE CASCADE;

ALTER TABLE aerotool_platform.event
    ADD CONSTRAINT event_user_subject_id_fkey FOREIGN KEY (user_subject_id)
        REFERENCES aerotool_platform.user(id) ON DELETE CASCADE;