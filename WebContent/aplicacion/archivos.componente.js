(function (aplicación, $) {

    let { Archivo, ServicioDeArchivos, Sppiner } = aplicación;
    let { archivoBase64 } = aplicación.Utilidades;
    
    let URI_BASE=`/administracion-de-archivos/aplicacion`;
    
    aplicación.ComponenteArchivos = {
        agregar() {
            Sppiner.Mostrar();
            let descriptorArchivo = $('#insert-file-data').prop('files')[0];

            archivoBase64(
                descriptorArchivo,
                contenidoArchivo => ServicioDeArchivos
                    .agregar(
                        new Archivo(descriptorArchivo, contenidoArchivo),
                        () => location.href=URI_BASE,
                        Sppiner.Ocultar ),
                Sppiner.Ocultar
            )
        },
        eliminar() {
            Sppiner.Mostrar();
            ServicioDeArchivos
            .eliminar(
                $('#delete-file-id').val(),
                () => location.href=URI_BASE,
                Sppiner.Ocultar );
        },
        consultarTodos() {
            ServicioDeArchivos.consultarTodos(
                lista => lista.forEach(archivo => this.monstrarArchivos(archivo)),
                error => console.log(error)
            );
        },
        monstrarArchivos: ({id, nombre, extension}) =>
            $('#select-file-container').append(`
              <tr class="table-active">
                <td>${id}</td>
                <td><a href="http://${location.hostname}/contenedor/${id}.${extension}">${nombre}</a></td>
                <td>${extension}</td>
              </tr>`),
        CargarComponente() {
            $('#agregar-archivo').click( () => this.agregar() );
            $('#eliminar-archivo').click( () => this.eliminar() );
            this.consultarTodos();
        }
    }
    
})(aplicación, jQuery);