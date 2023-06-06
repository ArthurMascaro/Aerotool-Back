CREATE SCHEMA aerotool_platform;

ALTER SCHEMA aerotool_platform OWNER TO "aerotool";

CREATE TYPE aerotool_platform.toolType as ENUM (
    'PROPERTY',
    'COMMON'
);

ALTER TYPE aerotool_platform.toolType OWNER TO "aerotool";

CREATE TABLE aerotool_platform.tool (
    id uuid not null,
    name varchar(255) not null,
    description varchar(255),
    type aerotool_platform.toolType not null
);

ALTER TABLE aerotool_platform.tool OWNER TO "aerotool";

ALTER TABLE aerotool_platform.tool
    ADD CONSTRAINT tool_pk PRIMARY KEY (id);

CREATE TYPE aerotool_platform.toolSituation as ENUM (
    'BROKEN',
    'LOST',
    'BUSY',
    'FREE'
);

ALTER TYPE aerotool_platform.toolSituation OWNER TO "aerotool";

CREATE TABLE aerotool_platform.locate (
    id uuid not null,
    name varchar(255) not null,
    description varchar(255)
);

ALTER TABLE aerotool_platform.locate OWNER TO "aerotool";

ALTER TABLE aerotool_platform.locate
    ADD CONSTRAINT locate_pk PRIMARY KEY (id);

CREATE TABLE aerotool_platform.toolItem (
    id uuid not null,
    patrimony varchar(255) not null,
    situation aerotool_platform.toolSituation not null,
    locate_id uuid not null,
    tool_id uuid not null
);

ALTER TABLE aerotool_platform.toolItem OWNER TO "aerotool";

ALTER TABLE aerotool_platform.toolItem
    ADD CONSTRAINT toolItem_pk PRIMARY KEY (id);

ALTER TABLE aerotool_platform.toolItem
    ADD CONSTRAINT toolItem_locate_id_fkey FOREIGN KEY (locate_id)
        REFERENCES aerotool_platform.locate(id) ON DELETE CASCADE;

ALTER TABLE aerotool_platform.toolItem
    ADD CONSTRAINT toolItem_tool_id_fkey FOREIGN KEY (tool_id)
        REFERENCES aerotool_platform.tool(id) ON DELETE CASCADE;