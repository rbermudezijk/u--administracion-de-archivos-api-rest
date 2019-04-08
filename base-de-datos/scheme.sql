CREATE DATABASE IF NOT EXISTS mu_control_de_archivos;
use mu_control_de_archivos;

CREATE TABLE IF NOT EXISTS lca_llaves(
    id_llave INT UNSIGNED AUTO_INCREMENT,
    llave VARCHAR(256) UNIQUE,
    fecha_insercion DATETIME
        DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id_llave)
) Engine = InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE archivos(
    id_archivo INT UNSIGNED AUTO_INCREMENT,
    id_llave INT UNSIGNED,
    nombre varchar(100) NOT NULL,
    extension varchar(20) NOT NULL,
    tipo_mime varchar(100) NOT NULL,
    PRIMARY KEY(id_archivo),
    CONSTRAINT propietario_de_archivos
        FOREIGN KEY(id_llave)
        REFERENCES lca_llaves(id_llave)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) Engine = InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE grupos(
    id_grupo INT UNSIGNED AUTO_INCREMENT,
    id_llave INT UNSIGNED,
    nombre VARCHAR(100),
    fecha_insercion DATETIME
        DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME 
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id_grupo),
    CONSTRAINT propietario_de_grupo
        FOREIGN KEY(id_llave)
        REFERENCES lca_llaves(id_llave)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) Engine = InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE grupos_de_archivos(
    id_grupo INT UNSIGNED,
    id_archivo INT UNSIGNED,
    fecha_insercion DATETIME
        DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME 
        DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id_grupo, id_archivo),
    CONSTRAINT grupo
        FOREIGN KEY(id_grupo)
        REFERENCES grupos(id_grupo)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT miembro_del_grupo
        FOREIGN KEY(id_archivo)
        REFERENCES archivos(id_archivo)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) Engine = InnoDB DEFAULT CHARSET='utf8';