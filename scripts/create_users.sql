use `library_directory`;


INSERT INTO `users` 
VALUES 
('max','{bcrypt}$2a$10$R3FC68MNcXarDMOlPD7OHOJLL8gCFmmBwlmuyZbbNe5Qm1aXRrJl6',1),
('sebastian','{bcrypt}$2a$10$R3FC68MNcXarDMOlPD7OHOJLL8gCFmmBwlmuyZbbNe5Qm1aXRrJl6',1),
('kimi','{bcrypt}$2a$10$R3FC68MNcXarDMOlPD7OHOJLL8gCFmmBwlmuyZbbNe5Qm1aXRrJl6',1);


INSERT INTO `authorities` 
VALUES 
('max','ROLE_EMPLOYEE'),
('sebastian','ROLE_EMPLOYEE'),
('sebastian','ROLE_MANAGER'),
('kimi','ROLE_EMPLOYEE'),
('kimi','ROLE_MANAGER'),
('kimi','ROLE_ADMIN');