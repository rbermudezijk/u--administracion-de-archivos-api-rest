# Base de datos

## Estructura de la base de datos

La estructura cuenta con cuatro tablas que representan la *estructura de los archivos*.
Por otro lado, en el servidor de la base de datos **no se almacena el contenido de los archivos**.
Estos se colocan directamente sobre el sistema de archivos de un servidor HTTP para facilitar el
manejo de cache entre el servidor y el cliente web.

#### Tablas

```sql
    lca_llaves # Lista de permisos de acceso al API REST. Identifica a los dueños de los archivos.
    archivos   # Contiene el registro de los archivos utilizados
    etiquetas_de_grupos # Etiquetas que se utilizan para agrupar archivos
    grupos_de_archivos  # Guarda la configuración de grupos de archivos.
```

## Usuario de la Base de Datos
El script sql [Users](./users.sql) contiene un ejemplo de como crear un usuario para la base de datos
con los permisos necesarios para acceder a la base de datos de los archivos. Sin embargo, esta configuración 
es un mero ejemplo y para su configuración en un servidor para el funcionamiento final de la aplicación
considerese mejorar la seguridad en la configuración del usuario.

## Conexión de la Base de Datos con el servidor de aplicación

Para configurar la URL de acceso al servidor de la base de datos es necesario añadir las credenciales
establecidas para la conexión en archivo [app.properties](../WebContent/WEB-INF/classes/app.properties)
del la aplicación.