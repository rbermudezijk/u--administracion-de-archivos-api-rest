(function (aplicación) {
    let _url = `http://${location.hostname}:8080/administracion-de-archivos/rest-v1/archivos`;

    aplicación.ServicioDeArchivos = {
        peticion: (method, uriParams='', data={}) => ({
            url: `${_url}/${uriParams}`,
            method,
            dataType: 'json',
            ...['POST','PUT'].find(m=>m==method)
            ? {
                contentType: 'application/json',
                data: JSON.stringify(data),
            } : {}
        }),
        agregar (archivo, rs,rj) {
            $.ajax(this.peticion('POST', '', archivo))
            .done(d => rs(d))
            .fail(e => rj(e));
        },
        actualizar (id, archivo) {
            $.ajax(this.peticion('PUT',`id/${id}`, archivo))
            .done(d => console.log(d))
            .fail(e => console.log(e));
        },
        eliminar (id, rs, rj) {
            $.ajax(this.peticion('DELETE',`id/${id}`))
            .done(d => rs(d))
            .fail(e => rj(e));
        },
        consultar(id) {
            $.ajax(this.peticion('GET',`id/${id}`))
            .done(d => console.log(d))
            .fail(e => console.log(e));
        },
        consultarTodos (rs, rj) {
            $.ajax(this.peticion('GET'))
            .done(d => rs(d))
            .fail(e => rj(e));
        }
    }

})(aplicación);