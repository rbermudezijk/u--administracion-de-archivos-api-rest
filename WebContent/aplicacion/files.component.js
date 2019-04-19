'use strict';
var app = app || {};

(function (app, jQuery, factory) {

    var include = [
        jQuery,
        app.FileModel,
        app.FilesAdminService
    ];
    app.FileComponent = factory.apply(null,include);

    app.run = app.FileComponent.init;
    
})(app, jQuery, function ($, FileModel, FilesAdminService) {
    
    function FileComponent () {
    	
    	/** Private context. */
        function file2Base64 (rs, rj, file) {
            console.log(arguments)
            var fRead = new FileReader();
            
            fRead.onload  = evt => rs(new FileModel(file, evt.target.result));
            fRead.onerror = err => {rj(err)};
            fRead.readAsDataURL(file);
        }
        
        function insert() {
            new Promise( (rs,rj) => {
            	$('.spinner-container').removeClass('spinner-container-no-show');
                file2Base64(rs, rj, $('#insert-file-data').prop('files')[0])
            })
            .then(fileObj => {
            	new Promise (
                  (rs,rj) => {
                      $('.spinner-container').addClass('spinner-container-no-show')
                      FilesAdminService.setFile(fileObj).insert(rs,rj)
                  }
            	)
            	.then(fileInserted => {
            		console.log('File inserted!');
            		location.href = '/proyecto-ejercicio/admin-files/';
            	})
            	.catch( e => {throw 'Error'} )
            	.finally(()=>$('.spinner-container').addClass('spinner-container-no-show'))
            })
            .catch(err => {
               console.log(err);
               $('.spinner-container').addClass('spinner-container-no-show')
            });
        }
        
        function drop() {
            new Promise( (rs,rj) => {
            	$('.spinner-container').removeClass('spinner-container-no-show');
                FilesAdminService.delete( $('#delete-file-id').val(), rs, rj );
            })
            .then( (fileDelete) => {
            	$('.spinner-container').addClass('spinner-container-no-show');
                console.log('File delete!')
                location.href = '/proyecto-ejercicio/admin-files/';
            })
            .catch(err => console.log(err))
            .finally(()=>$('.spinner-container').addClass('spinner-container-no-show'));
        }
        
        function selectAll() {
            new Promise( (rs,rj) => {
                FilesAdminService.selectAll(rs,rj);
            })
            .then( (fileList) => {
                fileList.forEach(file => pushFileRow(file))
            })
            .catch(err => console.log(err));
        }
        
        function pushFileRow (fDesc) {
            var templateRow = `
              <tr class="table-active">
                <td>${fDesc.id}</td>
                <td>${fDesc.name}</td>
                <td>${fDesc.extension}</td>
                <td>${fDesc.mimeType}</td>
              </tr>`;
            
            $('#select-file-container').append(templateRow);
        }
        
        /** Public context. */
        this.init = function () {
            $('#insert-file-action').click( () => insert() );
            $('#delete-file-action').click( () => drop() );
            selectAll();
        };
    }
    
    return new FileComponent();
    
});