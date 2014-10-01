START TRANSACTION;
BEGIN;

DROP PROCEDURE IF EXISTS CreateUser;

DELIMITER //

CREATE PROCEDURE CreateUser(_email varchar(255), _league varchar(255), _first_name varchar(50), _last_name varchar(50), _player_name varchar(255) )
BEGIN
INSERT INTO `htl`.`app_user` (
`account_expired`,
`account_locked`,
`credentials_expired`,
`email`,
`account_enabled`,
`first_name`,
`last_name`,
`password`,
`username`)
VALUES (
	false,
	false,
	false,
	_email,
	true,
	_first_name,
	_last_name,
	'$2a$10$CnQVJ9bsWBjMpeSKrrdDEeuIptZxXrwtI6CZ/OgtNxhIgpKxXeT9y',
	_email );

SELECT @userId := id FROM app_user where email = _email;

INSERT INTO user_role (user_id, role_id) VALUES( @userId, -2);

INSERT INTO player ( `name`, `user_id`, `score` ) VALUES ( _player_name, @userId, 0 );

SELECT @leagueId := id FROM league where name = _league;
SELECT @playerId := id FROM player where user_id = @userId;

INSERT INTO league_player( `league_id`, `player_id`, `commissioner` ) VALUES ( @leagueId, @playerId, false );

END//

DELIMITER  ;

CALL CreateUser("jeff15@htl.com", "JLeague", "Jeff15", "Ginn", "Jeff15" );

DROP PROCEDURE CreateUser;

COMMIT;
