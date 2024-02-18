DROP USER if exists 'librarian'@'localhost';


CREATE USER 'librarian'@'localhost' 
IDENTIFIED BY 'librarian';

GRANT ALL PRIVILEGES ON * . * TO 'librarian'@'localhost';