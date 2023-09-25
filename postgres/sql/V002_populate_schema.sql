--! User Queries !--

insert into aerotool_platform.promptuary (login, password) values
    ('SC3076234', 'password');
insert into aerotool_platform.user (id, name, role, promptuary_login)
values (gen_random_uuid(), 'João de Boituva', 'ADMIN', 'SC3076234');

insert into aerotool_platform.promptuary (login, password)
    values ('SC3076235', 'senha');
insert into aerotool_platform.user (id, name, role, promptuary_login)
    values (gen_random_uuid(), 'Zé de Votuporanga', 'STUDENT', 'SC3076235');

insert into aerotool_platform.promptuary (login, password) values
    ('SC3076236', 'senha_forte');
insert into aerotool_platform.user (id, name, role, promptuary_login)
    values (gen_random_uuid(), 'Votuporanguinho', 'TEACHER', 'SC3076236');

--! Tool Queries !--

insert into aerotool_platform.tool (id, name, description, type)
    values (gen_random_uuid(), 'Martelo', 'Ferramenta para bater em coisas', 'COMMON');

insert into aerotool_platform.tool (id, name, description, type)
    values (gen_random_uuid(), 'Chave de fenda', 'Ferramenta para apertar parafusos', 'COMMON');

select * from aerotool_platform.tool;

--! Locate Queries !--

insert into aerotool_platform.locate (id, name, description)
    values (gen_random_uuid(), 'Sala 1', 'Sala de ferramentas');

insert into aerotool_platform.locate (id, name, description)
values (gen_random_uuid(), 'hangar 1', 'Hangar de ferramentas');

--! Tool Item Queries !--

insert into aerotool_platform.tool_item (id, patrimony, situation, locate_id, tool_id)
    values (gen_random_uuid(), '0001', 'LOST', (select id from aerotool_platform.locate where name = 'Sala 1'), (select id from aerotool_platform.tool where name = 'Martelo'));

insert into aerotool_platform.tool_item (id, patrimony, situation, locate_id, tool_id)
    values (gen_random_uuid(), '0002', 'FREE', (select id from aerotool_platform.locate where name = 'hangar 1'), (select id from aerotool_platform.tool where name = 'Chave de fenda'));

--! Request Queries !--

insert into aerotool_platform.request (id, request_date, user_id)
    values (gen_random_uuid(), '2023-06-09 07:45:00', (select id from aerotool_platform.user where name = 'Votuporanguinho'));

insert into aerotool_platform.request (id, request_date, user_id)
    values (gen_random_uuid(), '2023-09-17 15:30:00', (select id from aerotool_platform.user where name = 'Zé de Votuporanga'));

--! Line Request Queries !--

insert into aerotool_platform.line_request (id, expected_return_date, real_return_date, expected_withdrawal_date, real_withdrawal_date, request_situation, tool_item_id, request_id)
    values (gen_random_uuid(), '2023-06-09 09:45:00', '2023-06-09 08:45:00', '2023-06-08 07:45:00', '2023-06-08 07:40:00', 'ACCEPTED', (select id from aerotool_platform.tool_item where patrimony = '0001'), (select id from aerotool_platform.request where request_date = '2023-06-09 07:45:00'));

insert into aerotool_platform.line_request (id, expected_return_date, real_return_date, expected_withdrawal_date, real_withdrawal_date, request_situation, tool_item_id, request_id)
    values (gen_random_uuid(), '2023-09-17 17:30:00', null, '2023-09-17 15:30:00', null, 'REJECTED', (select id from aerotool_platform.tool_item where patrimony = '0002'), (select id from aerotool_platform.request where request_date = '2023-09-17 15:30:00'));

select * from aerotool_platform.line_request;

--! Event Queries !--

insert into aerotool_platform.event (id, description, event_date, event_type, event_situation, user_responsible_id, user_subject_id)
    values (gen_random_uuid(), 'Ferramenta perdida', '2023-06-09 08:45:00', 'ACTION_USERS', 'ACCEPTED', (select id from aerotool_platform.user where name = 'Votuporanguinho'), (select id from aerotool_platform.user where name = 'Zé de Votuporanga'));

insert into aerotool_platform.event (id, description, event_date, event_type, event_situation, user_responsible_id, user_subject_id)
    values (gen_random_uuid(), 'Ferramenta perdida', '2023-09-17 15:30:00', 'ACTION_USERS', 'REJECTED', (select id from aerotool_platform.user where name = 'Votuporanguinho'), (select id from aerotool_platform.user where name = 'Zé de Votuporanga'));