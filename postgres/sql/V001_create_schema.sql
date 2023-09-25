CREATE SCHEMA aerotool_platform;

ALTER SCHEMA aerotool_platform OWNER TO "aerotool";

CREATE TABLE aerotool_platform.promptuary (
    login varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);

CREATE TYPE aerotool_platform.role AS ENUM (
    'ADMIN',
    'TEACHER',
    'STUDENT'
);

CREATE TABLE aerotool_platform.user (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    role aerotool_platform.role NOT NULL,
    promptuary_login aerotool_platform.promptuary NOT NULL
);

CREATE TYPE aerotool_platform.tool_type as ENUM (
    'PROPERTY',
    'COMMON'
);

ALTER TYPE aerotool_platform.tool_type OWNER TO "aerotool";

CREATE TABLE aerotool_platform.tool (
    id uuid not null,
    name varchar(255) not null,
    description varchar(255),
    type aerotool_platform.tool_type not null
);

ALTER TABLE aerotool_platform.tool OWNER TO "aerotool";

ALTER TABLE aerotool_platform.tool
    ADD CONSTRAINT tool_pk PRIMARY KEY (id);

CREATE TYPE aerotool_platform.tool_situation as ENUM (
    'BROKEN',
    'LOST',
    'BUSY',
    'FREE'
);

ALTER TYPE aerotool_platform.tool_situation OWNER TO "aerotool";

CREATE TABLE aerotool_platform.locate (
    id uuid not null,
    name varchar(255) not null,
    description varchar(255)
);

ALTER TABLE aerotool_platform.locate OWNER TO "aerotool";

ALTER TABLE aerotool_platform.locate
    ADD CONSTRAINT locate_pk PRIMARY KEY (id);

CREATE TABLE aerotool_platform.tool_item (
    id uuid not null,
    patrimony varchar(255) not null,
    situation aerotool_platform.tool_situation not null,
    locate_id uuid not null,
    tool_id uuid not null
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

CREATE TYPE aerotool_platform.request_situation as ENUM (
    'WAITING',
    'ACCEPTED',
    'REJECTED'
);

CREATE TABLE aerotool_platform.line_request (
    id uuid NOT NULL,
    expected_return_date timestamp NOT NULL,
    real_return_date timestamp,
    expected_withdrawal_date timestamp NOT NULL,
    real_withdrawal_date timestamp,
    request_situation aerotool_platform.request_situation,
    tool_item_id aerotool_platform.tool_item NOT NULL
);

ALTER TABLE aerotool_platform.line_request OWNER TO "aerotool";

ALTER TABLE aerotool_platform.line_request
    ADD CONSTRAINT lineRequest_pk PRIMARY KEY (id);

ALTER TABLE aerotool_platform.line_request
    ADD CONSTRAINT lineRequest_tool_item_id_fkey FOREIGN KEY (tool_item_id)
        REFERENCES aerotool_platform.tool_item(id) ON DELETE CASCADE;
