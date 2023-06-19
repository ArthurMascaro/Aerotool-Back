CREATE SCHEMA aerotool_platform;

ALTER SCHEMA aerotool_platform OWNER TO "aerotool";

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