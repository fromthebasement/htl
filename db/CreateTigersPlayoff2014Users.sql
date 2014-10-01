START TRANSACTION;
BEGIN;

DROP PROCEDURE IF EXISTS CreateUser;

DELIMITER //

CREATE PROCEDURE CreateUser( _first_name varchar(50), _last_name varchar(50), _player_name varchar(255), _email varchar(255), _league varchar(255) )  
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

CALL CreateUser("Dan", "Arnold", "Darnold", "pezfiend@hotmail.com", "MLeague1");
CALL CreateUser("Adam", "Parks", "Herms", "adamwayneparks@gmail.com", "MLeague1");
CALL CreateUser("Chris", "Lecours", "Chode", "chrslssu17@yahoo.com", "MLeague1");
CALL CreateUser("Nick", "Sasack", "Wedge", "lssuteke201@hotmail.com", "MLeague1");
CALL CreateUser("Brian", "Nawrocki", "Nawrocki", "brian@tacklewarehouse.com", "MLeague1");
CALL CreateUser("Al", "Case", "Al", "shockers@hotmail.com", "MLeague1");
CALL CreateUser("Derek", "Weyend", "Derek", "dloc231@yahoo.com", "MLeague1");
CALL CreateUser("Matt", "Penridge", "Munchum", "matt1@htl.com", "MLeague1");
CALL CreateUser("Dan", "Lecours", "ChodesBrother", "dicknose19@yahoo.com", "MLeague1");
CALL CreateUser("Adam", "Poehlman", "Poehlman", "apoehlman@hotmail.com", "MLeague1");
CALL CreateUser("Kathy", "Penridge", "Kathy", "simi0@aol.com", "MLeague2");
CALL CreateUser("Marty", "Penridge", "Marty", "martyfeldspar@gmail.com", "MLeague2");
CALL CreateUser("Denise", "Williams", "Denise", "denise.williams@mckesson.com", "MLeague2");
CALL CreateUser("Jeff", "Pink", "Jeff", "jeffrey.e.pink@gmail.com", "MLeague2");
CALL CreateUser("Erik", "Ertzbischoff", "Erik", "ertz75@yahoo.com", "MLeague2");
CALL CreateUser("Nikki", "Ertzbischoff", "Nikki", "nikcleveland@yahoo.com", "MLeague2");
CALL CreateUser("Tony", "Pink", "Tony", "anthonyg.pink@gmail.com", "MLeague2");
CALL CreateUser("Matt", "Penridge", "Matt", "matt2@htl.com", "MLeague2");
CALL CreateUser("Jane", "Ertzbischoff", "Jane", "jane.ertzbischoff@mckesson.com", "MLeague2");
CALL CreateUser("Amy", "Ginn", "Amy", "amyhginn@gmail.com", "JLeague");
CALL CreateUser("Brian ", "Stanton", "Brian S", "brian.stanton@gmail.com", "JLeague");
CALL CreateUser("Ashley ", "Lawler", "Ashley", "aeglawler@gmail.com", "JLeague");
CALL CreateUser("Jon ", "Kopacz", "Jon", "JonKPhoto@gmail.com", "JLeague");
CALL CreateUser("Kent", "Morton", "Kent", "kent.morton@gmail.com", "JLeague");
CALL CreateUser("Tim", "Ginn", "Tim", "TimGinn@gmail.com", "JLeague");
CALL CreateUser("Pete", "Varga", "Pete", "pvarga007@yahoo.com", "JLeague");
CALL CreateUser("Ted", "Ginn", "Ted", "theodoreginn@gmail.com", "JLeague");
CALL CreateUser("Mickey", "Ginn", "Mickey", "MickeyTed@gmail.com", "JLeague");
CALL CreateUser("Brian ", "Ginn", "Brian G", "bginn53@sbcglobal.net", "JLeague");
CALL CreateUser("Jeff", "Ginn", "Jeff", "jeff@htl.com", "JLeague");

DROP PROCEDURE CreateUser;

COMMIT;
