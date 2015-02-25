CREATE USER gymStatus@'localhost' IDENTIFIED BY 'gymStatus';

CREATE DATABASE ChustaSoft;

USE ChustaSoft;

GRANT ALL ON chustasoft.* TO gymStatus@'localhost' IDENTIFIED BY 'gymStatus';