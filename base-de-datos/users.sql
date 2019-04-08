CREATE USER 'mu-control-de-archivos'@localhost
IDENTIFIED BY 'mu-control-de-archivos-1234';

GRANT ALL PRIVILEGES ON microservicio_control_de_archivos.*
TO 'mu-control-de-archivos'@localhost;