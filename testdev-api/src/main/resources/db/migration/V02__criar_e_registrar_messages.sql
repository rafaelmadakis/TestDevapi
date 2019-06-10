CREATE TABLE messages (
	id BIGINT(30) PRIMARY KEY AUTO_INCREMENT,
	conversation_Id VARCHAR(60) NOT NULL,
	timestamp VARCHAR(60) NOT NULL,
	de VARCHAR(60) NOT NULL,
	para VARCHAR(60) NOT NULL,
	text VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO messages (conversation_Id, timestamp, de, para, text ) VALUES ('7665ada8-3448-4acd-a1b7-d688e68fe9a1' , '2018-11-16T23:30:52.6917722Z' , '36b9f842-ee97-11e8-9443-0242ac120002', '16edd3b3-3f75-40df-af07-2a3813a79ce9', 'Oi! Como posso te ajudar?');