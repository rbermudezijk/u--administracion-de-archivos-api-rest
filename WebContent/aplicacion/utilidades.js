(function (aplicación) {

    /** Private context. */
    aplicación.Utilidades = {
        archivoBase64 (archivo, rs, rj) {
            var fRead = new FileReader();
            fRead.onload  = evt => rs(evt.target.result);
            fRead.onerror = err => rj(err);
            try {
                fRead.readAsDataURL(archivo);
            } catch(e) {
                rj(e)
            }
        }
    };

})(aplicación);