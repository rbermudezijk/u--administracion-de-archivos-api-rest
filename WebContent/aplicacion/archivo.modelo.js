(function (aplicación) {

    aplicación.Archivo =  function (descriptor, contenidoB64) {
        console.log(arguments)
        var descripción = descriptor.name.split('.'),
            contenido   = contenidoB64.split(':')[1].split(',');
        
        this.nombre    = descripción[0];
        this.idLlave   = 1;
        this.idCarpeta = 1;
        this.extension = descripción[1];
        this.tipoMIME  = contenido[0];
        this.contenido = contenido[1];
        console.log(this)
    };

})(aplicación);