# radiustool
RadiusTool


RADIUS:
INSERT INTO radcheck VALUES (1, 'evgeniy', 'Cleartext-Password', ':=', '123');
INSERT INTO radcheck VALUES ("", '2:100', 'Cleartext-Password', ':=', 'derparol');
INSERT INTO radcheck VALUES ("", '2:101', 'Cleartext-Password', ':=', 'derparol');
INSERT INTO radcheck VALUES ("", '2:102', 'Cleartext-Password', ':=', 'derparol');
INSERT INTO radcheck VALUES ("", '2:103', 'Cleartext-Password', ':=', 'derparol');
INSERT INTO radcheck VALUES ("", '2:104', 'Cleartext-Password', ':=', 'derparol');

INSERT INTO radcheck VALUES ("", '0002:0101', 'Cleartext-Password', ':=', 'derparol');

insert into radusergroup values ('2:100','default','1');
insert into radusergroup values ('2:101','default','1');
insert into radusergroup values ('2:102','default','1');
insert into radusergroup values ('2:103','default','1');
insert into radusergroup values ('2:104','default','1');


insert into radgroupreply values ('','default', 'Service-Type', '=' ,'Login-User');
insert into radgroupreply values ('','default', 'HW-Account-Info', '=' ,'AOpenGarden');
insert into radgroupreply values ('','default', 'HW-Account-Info', '+=' ,'AInternet');
insert into radgroupreply values ('','default', 'HW-Framed-Pool-Group', '=' ,'ipv4-public-group-01');
insert into radgroupreply values ('','default', 'Framed-IPv6-Pool', '=' ,'ipv6-pool-local-01');

INSERT INTO radreply VALUES (2, '200:20', 'Service-Type', '=', 'Login-User');
INSERT INTO radreply VALUES (3, '200:20', 'HW-Account-Info', '=', 'AOpenGarden');
INSERT INTO radreply VALUES (4, '200:20', 'HW-Account-Info', '+=', 'AInternet');
INSERT INTO radreply VALUES (5, '200:20', 'HW-Framed-Pool-Group', '=', 'public-group-01');

INSERT INTO radreply VALUES ("", '0002:0101', 'Service-Type', '=', 'Login-User');
INSERT INTO radreply VALUES ("", '0002:0101', 'HW-Account-Info', '=', 'Asp-landing-page');
INSERT INTO radreply VALUES ("", '0002:0101', 'HW-Account-Info', '+=', 'Asp-internet-acceess-10g-at-1g');
INSERT INTO radreply VALUES ("", '0002:0101', 'HW-Framed-Pool-Group', '=', 'ipv4-public-group-01');
INSERT INTO radreply VALUES ("", '0002:0101', 'Framed-IPv6-Pool', '=', 'ipv6-pool-local-01');


INSERT INTO radreply VALUES ("", 'sp-landing-page', 'HW-AVpair', '=', 'service:service-group=sg-landing-page priority 100');
INSERT INTO radreply VALUES ("", 'sp-landing-page', 'HW-AVpair', '+=', 'service:authentication-scheme=auth-scheme-none');
INSERT INTO radreply VALUES ("", 'sp-landing-page', 'HW-AVpair', '+=', 'service:accounting-scheme=acct-scheme-none');
INSERT INTO radreply VALUES ("", 'sp-landing-page', 'HW-AVpair', '+=', 'service:radius-server-group=rsg-bng-services');
INSERT INTO radreply VALUES ("", 'sp-landing-page', 'HW-Input-Committed-Information-Rate', '=', '1000000000');
INSERT INTO radreply VALUES ("", 'sp-landing-page', 'HW-Output-Committed-Information-Rate', '+=', '1000000000');
INSERT INTO radreply VALUES ("", 'sp-landing-page', 'HW-Priority', '=', '60');
INSERT INTO radreply VALUES ("", 'sp-landing-page', 'User-Password', '=', 'huaweinas');

INSERT INTO radreply VALUES ("", 'sp-internet-acceess-10g-at-1g', 'HW-AVpair', '=', 'service:service-group=sg-internet priority 200');
INSERT INTO radreply VALUES ("", 'sp-internet-acceess-10g-at-1g', 'HW-AVpair', '+=', 'service:authentication-scheme=auth-scheme-radius');
INSERT INTO radreply VALUES ("", 'sp-internet-acceess-10g-at-1g', 'HW-AVpair', '+=', 'service:accounting-scheme=acct-scheme-radius');
INSERT INTO radreply VALUES ("", 'sp-internet-acceess-10g-at-1g', 'HW-AVpair', '+=', 'service:radius-server-group=rsg-bng-services');
INSERT INTO radreply VALUES ("", 'sp-internet-acceess-10g-at-1g', 'HW-Input-Committed-Information-Rate', '=', '1000000000');
INSERT INTO radreply VALUES ("", 'sp-internet-acceess-10g-at-1g', 'HW-Output-Committed-Information-Rate', '+=', '1000000000');
INSERT INTO radreply VALUES ("", 'sp-internet-acceess-10g-at-1g', 'User-Password', '=', 'huaweinas');


CHECK:
radtest lameuser hello 127.0.0.1 0 mysecret
radtest bob hello 127.0.0.1 0 mysecret
radtest evgeniy 123 127.0.0.1 0 mysecret

___________________________________________________________________________________________


/etc/freeradius/3.0/ - directory CONF
/usr/share/freeradius/ - dictionary


NOKIA:

INSERT INTO radcheck VALUES ("", '00:50:18:00:1e:05', 'Cleartext-Password', ':=', 'password');
INSERT INTO radreply VALUES ("", '00:50:18:00:1e:05', 'Alc-MSAP-Serv-ID', '=', '400');
INSERT INTO radreply VALUES ("", '00:50:18:00:1e:05', 'Alc-MSAP-Policy', '=', 'SUBM-MSAP-POL-01');
INSERT INTO radreply VALUES ("", '00:50:18:00:1e:05', 'Alc-MSAP-Interface', '=', 'grp102-1');
INSERT INTO radreply VALUES ("", '00:50:18:00:1e:05', 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
INSERT INTO radreply VALUES ("", '00:50:18:00:1e:05', 'Alc-Subsc-ID-Str', '=', 'user1');

client bng01-kyi01-mn       {

        ipaddr = 10.0.0.245
        proto = *
        secret = nokiabng
        require_message_authenticator = no
        nas_type = other
        limit {
                max_connections = 16
                lifetime = 0
                idle_timeout = 30
        }

}


client bng02-kyi01-mn       {

        ipaddr = 10.0.0.246
        proto = *
        secret = nokiabng
        require_message_authenticator = no
        nas_type = other
        limit {
                max_connections = 16
                lifetime = 0
                idle_timeout = 30
        }

}


INSERT INTO radcheck VALUES ("", 'KITR17-CGT3 Eth-Trunk12.1:20', 'Cleartext-Password', ':=', 'password');
INSERT INTO radreply VALUES ("", 'KITR17-CGT3 Eth-Trunk12.1:20', 'Alc-MSAP-Serv-ID', '=', '400');
INSERT INTO radreply VALUES ("", 'KITR17-CGT3 Eth-Trunk12.1:20', 'Alc-MSAP-Policy', '=', 'SUBM-MSAP-POL-01');
INSERT INTO radreply VALUES ("", 'KITR17-CGT3 Eth-Trunk12.1:20', 'Alc-MSAP-Interface', '=', 'grp103-1');
INSERT INTO radreply VALUES ("", 'KITR17-CGT3 Eth-Trunk12.1:20', 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
INSERT INTO radreply VALUES ("", 'KITR17-CGT3 Eth-Trunk12.1:20', 'Alc-Subsc-ID-Str', '=', 'user4.2');



INSERT INTO radcheck VALUES ("", '00:04:96:36:90:eb', 'Cleartext-Password', ':=', 'password');
INSERT INTO radreply VALUES ("", '00:04:96:36:90:eb', 'Alc-MSAP-Serv-ID', '=', '400');
INSERT INTO radreply VALUES ("", '00:04:96:36:90:eb', 'Alc-MSAP-Policy', '=', 'SUBM-MSAP-POL-01');
INSERT INTO radreply VALUES ("", '00:04:96:36:90:eb', 'Alc-MSAP-Interface', '=', 'grp102-1');
INSERT INTO radreply VALUES ("", '00:04:96:36:90:eb', 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
INSERT INTO radreply VALUES ("", '00:04:96:36:90:eb', 'Alc-Subsc-ID-Str', '=', 'user4.3.1');

INSERT INTO radcheck VALUES ("", 'd4:b1:10:b3:33:56', 'Cleartext-Password', ':=', 'password');
INSERT INTO radreply VALUES ("", 'd4:b1:10:b3:33:56', 'Alc-MSAP-Serv-ID', '=', '400');
INSERT INTO radreply VALUES ("", 'd4:b1:10:b3:33:56', 'Alc-MSAP-Policy', '=', 'SUBM-MSAP-POL-01');
INSERT INTO radreply VALUES ("", 'd4:b1:10:b3:33:56', 'Alc-MSAP-Interface', '=', 'grp102-1');
INSERT INTO radreply VALUES ("", 'd4:b1:10:b3:33:56', 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
INSERT INTO radreply VALUES ("", 'd4:b1:10:b3:33:56', 'Alc-Subsc-ID-Str', '=', 'user4.3.2');



INSERT INTO radcheck VALUES ("", '90:17:ac:b9:ea:9c', 'Cleartext-Password', ':=', 'password');
INSERT INTO radreply VALUES ("", '90:17:ac:b9:ea:9c', 'Alc-MSAP-Serv-ID', '=', '400');
INSERT INTO radreply VALUES ("", '90:17:ac:b9:ea:9c', 'Alc-MSAP-Policy', '=', 'SUBM-MSAP-POL-01');
INSERT INTO radreply VALUES ("", '90:17:ac:b9:ea:9c', 'Alc-MSAP-Interface', '=', 'grp102-1');
INSERT INTO radreply VALUES ("", '90:17:ac:b9:ea:9c', 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
INSERT INTO radreply VALUES ("", '90:17:ac:b9:ea:9c', 'Alc-Subsc-ID-Str', '=', 'user4.3.3');


INSERT INTO radcheck VALUES ("", '9c:eb:e8:3a:c5:aa', 'Cleartext-Password', ':=', 'password');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Alc-MSAP-Serv-ID', '=', '400');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Alc-MSAP-Policy', '=', 'SUBM-MSAP-POL-01');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Alc-MSAP-Interface', '=', 'grp102-1');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Alc-Subsc-ID-Str', '=', 'user4.10');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Alc-Subsc-Prof-Str', '=', 'SUB-LIFE');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Alc-SLA-Prof-Str', '=', 'SLA-10M');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Alc-Credit-Control-CategoryMap', '=', 'SUBM-CAT-LIFE-T5');
INSERT INTO radreply VALUES ("", '9c:eb:e8:3a:c5:aa', 'Alc-Credit-Control-Quota', '=', '0|5m|CAT48');

update radcheck set Alc-SLA-Prof-Str = 'SLA-DENY' where username = 'd4:b1:10:b3:33:56';
update radcheck set Alc-Subsc-ID-Str = 'user4.6' where username = 'd4:b1:10:b3:33:56';


SLA-1002 - 100 mb of traffic + shape QoS
SUB-LIFE - accounting destination IP+Port
SUB-01 - 1 MB
Alc-SLA-Prof-Str = "SLA-DENY" - no internet for user
SLA-10M - speed 10Mb

 echo "Acct-Session-Id = \""FFDF1B00001DDD5EE9DB00"\"", Alc-Credit-Control-CategoryMap = "SUBM-CAT-LIFE-Q1",Alc-Credit-Control-Quota = "100M|0|CAT46" | radclient -d /usr/share/freeradius/ -x  10.0.0.245:3799 -r 1 -t 2 coa nokiabng
 echo Alc-SLA-Prof-Str="SLA-LIFE-DEF",Acct-Session-Id="FFDF1B00001DED5EE9E5A4" | radclient -d /usr/share/freeradius/ -x  10.0.0.245:3799  -r 1 -t 2 coa nokiabng
 echo "Acct-Session-Id = \""<Acct-Session-Id>"\"" | radclient -d /usr/share/freeradius/ -x  10.0.0.245:3799 disconnect nokiabng
 
 
 INSERT INTO radcheck VALUES ("", 'vl-31-as02-kyi01-fix-port1', 'Cleartext-Password', ':=', 'password');
 INSERT INTO radreply VALUES ("", 'vl-31-as02-kyi01-fix-port1', 'Alc-MSAP-Serv-ID', '=', '400');
 INSERT INTO radreply VALUES ("", 'vl-31-as02-kyi01-fix-port1', 'Alc-MSAP-Policy', '=', 'SUBM-MSAP-POL-01');
 INSERT INTO radreply VALUES ("", 'vl-31-as02-kyi01-fix-port1', 'Alc-MSAP-Interface', '=', 'grp104-1');
 INSERT INTO radreply VALUES ("", 'vl-31-as02-kyi01-fix-port1', 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
 INSERT INTO radreply VALUES ("", 'vl-31-as02-kyi01-fix-port1', 'Alc-Subsc-ID-Str', '=', 'as02-kyi01-fix-[port1][vl-31]');

 
 
 
echo Alc-SLA-Prof-Str="SLA-1002",Acct-Session-Id="FFDF1B000023E45EECA067" | radclient -d /usr/share/freeradius/ -x  10.0.0.245:3799  -r 1 -t 2 coa nokiabng
echo Acct-Session-Id = "FFDF1B0000007F5EF35C1C" | radclient -d /usr/share/freeradius/ -x  10.0.0.245:3799 disconnect nokiabng 


echo Alc-SLA-Prof-Str = "SLA-DENY",Acct-Session-Id="FFDF1B000000BF5EF4B8D3" | radclient -d /usr/share/freeradius/ -x  10.0.0.245:3799  -r 1 -t 2 coa nokiabng
 
 
 
 FFDF1B000024595EF0A167
 
 
 
 
 
 
 
 -----------------  !!!! (working) !!!!  -------------
DELIMITER //
CREATE PROCEDURE `setSubscriberProfile` (IN user VARCHAR(64), IN profile INT)
COMMENT 'to bar FixedInternet use "0" as profile value, use "1" to unbar'
BEGIN

DECLARE sessionid VARCHAR(64);

CASE
WHEN profile = 0 THEN
UPDATE radius.radreply SET value = 'SLA-REDIRECT' WHERE username = user and attribute = 'Alc-SLA-Prof-Str';
WHEN profile = 1 THEN
UPDATE radius.radreply SET value = 'SLA-ALLOW-500M' WHERE username = user and attribute = 'Alc-SLA-Prof-Str';
ELSE
UPDATE radius.radreply SET value = 'SLA-ALLOW-500M' WHERE username = user and attribute = 'Alc-SLA-Prof-Str';
END CASE;

SELECT acctsessionid INTO sessionid FROM radius.radacct WHERE username = user ORDER BY radacctid DESC LIMIT 1;

IF sessionid IS NOT NULL THEN
INSERT INTO radius.provisioning (username, profile, session_id) VALUES (user, profile, sessionid);
ELSE
INSERT INTO radius.provisioning (username, profile, session_id) VALUES (user, profile, 'notactive');
END IF;

END;
//
DELIMITER ;


-----------------------------------------------------------

DELIMITER //
CREATE PROCEDURE `createBarredSubscriber` (IN user VARCHAR(64))
COMMENT 'New user creation with SLA-REDIRECT as starting status'
BEGIN

INSERT INTO radcheck VALUES ("", user, 'Cleartext-Password', ':=', 'password');
INSERT INTO radreply VALUES ("", user, 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
INSERT INTO radreply VALUES ("", user, 'Alc-Subsc-ID-Str', '=', user);
INSERT INTO radreply VALUES ("", user, 'Alc-Subsc-Prof-Str', '=', 'SUB-LIFE');
INSERT INTO radreply VALUES ("", user, 'Alc-SLA-Prof-Str', '=', 'SLA-REDIRECT');


END;
//
DELIMITER ;

------------------------------------------------------------------------




create table `provisioning`  (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(64) NOT NULL,
    `profile` INT UNSIGNED NOT NULL,
    `session_id` VARCHAR(64) NOT NULL,
    `provisioning_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`,`provisioning_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
PARTITION BY LIST (DAY(provisioning_time)) (
    PARTITION p00 VALUES IN  (0,1) ,
    PARTITION p02 VALUES IN  (2,3) ,
    PARTITION p04 VALUES IN  (4,5) ,
    PARTITION p06 VALUES IN  (6,7) ,
    PARTITION p08 VALUES IN  (8,9) ,
    PARTITION p10 VALUES IN  (10,11),
    PARTITION p12 VALUES IN  (12,13),
    PARTITION p14 VALUES IN  (14,15),
    PARTITION p16 VALUES IN  (16,17),
    PARTITION p18 VALUES IN  (18,19),
    PARTITION p20 VALUES IN  (20,21),
    PARTITION p22 VALUES IN  (22,23),
    PARTITION p24 VALUES IN  (24,25),
    PARTITION p26 VALUES IN  (26,27),
    PARTITION p28 VALUES IN  (28,29),
    PARTITION p30 VALUES IN  (30,31)
);

------------------------------------

create user provuser@localhost identified by 'Pr0v!s!0n';
grant execute on procedure radius.setSubscriberProfile to provuser@localhost;
grant select,insert,update on radius.radreply to provuser@localhost;
grant select,insert,update on radius.radacct to provuser@localhost;
grant select,insert,update on radius.provisioning to provuser@localhost;


create user provuser@10.1.60.49 identified by 'Pr0v!s!0n';
grant execute on procedure setSubscriberProfile to provuser@10.1.60.49;
grant update on radius.radreply to provuser@10.1.60.49;
grant select on radius.radacct to provuser@10.1.60.49;
grant insert on radius.provisioning to provuser@10.1.60.49;



create user provuser@10.1.24.219 identified by 'Pr0v!s!0n';
grant execute on procedure setSubscriberProfile to provuser@10.1.24.219;
grant update on radius.radreply to provuser@10.1.24.219;
grant select on radius.radacct to provuser@10.1.24.219;
grant insert on radius.provisioning to provuser@10.1.24.219;




create user 'radtool'@'%' identified by 'r@d!us';
grant select on radius.provisioning to 'radtool'@'%';


--------------clarify parameters-----------------------------


'Alc-MSAP-Serv-ID', '=', '400' - internet service identifier (VRF internet , users) , 400 default
'Alc-MSAP-Policy', '=', 'SUBM-MSAP-POL-01' - constant, how to name subscriber (SAP:pw-2:20)
'Alc-MSAP-Interface', '=', 'grp102-1' - какие юзера на каком свитче (ребята скажут)
'Framed-Pool', '=', 'DHCP-POOL-V4-400-1' - constant
'Alc-Subsc-ID-Str', '=', 'user4.10' - user details (any info we want), eather MSISDN
'Alc-Subsc-Prof-Str', '=', 'SUB-LIFE' - constant (auth , acc parameters, IP:port of radius)
'Alc-SLA-Prof-Str', '=', 'SLA-10M'
'Alc-Credit-Control-CategoryMap', '=', 'SUBM-CAT-LIFE-T5'
'Alc-Credit-Control-Quota', '=', '0|5m|CAT48'


---------------------------------------------------------------

MariaDB [radius]> select * from radreply where username = '9c:eb:e8:3a:c5:a0';
+----+-------------------+--------------------+----+--------------------+
| id | username          | attribute          | op | value              |
+----+-------------------+--------------------+----+--------------------+
| 65 | 9c:eb:e8:3a:c5:a0 | Framed-Pool        | =  | DHCP-POOL-V4-400-1 |
| 66 | 9c:eb:e8:3a:c5:a0 | Alc-Subsc-ID-Str   | =  | user4.5            |
| 67 | 9c:eb:e8:3a:c5:a0 | Alc-Subsc-Prof-Str | =  | SUB-LIFE           |
| 68 | 9c:eb:e8:3a:c5:a0 | Alc-SLA-Prof-Str   | =  | SLA-ALLOW-500M     |
+----+-------------------+--------------------+----+--------------------+

5 * * * * /home/jay/radtool/radtoolwrapper.sh >/dev/null 2>&1


INSERT INTO radcheck VALUES ("", 'vid-2-ki0177-as03-fix-port2', 'Cleartext-Password', ':=', 'password');
INSERT INTO radreply VALUES ("", 'vid-2-ki0177-as03-fix-port2', 'Framed-Pool', '=', 'DHCP-POOL-V4-400-1');
INSERT INTO radreply VALUES ("", 'vid-2-ki0177-as03-fix-port2', 'Alc-Subsc-ID-Str', '=', 'vid-2-ki0177-as03-fix-port2');
INSERT INTO radreply VALUES ("", 'vid-2-ki0177-as03-fix-port2', 'Alc-Subsc-Prof-Str', '=', 'SUB-LIFE');
INSERT INTO radreply VALUES ("", 'vid-2-ki0177-as03-fix-port2', 'Alc-SLA-Prof-Str', '=', 'SLA-ALLOW-500M');
