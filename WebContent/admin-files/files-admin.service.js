'use strict';
var app = app || {};

(function (app, jQuery, factory) {
    
    app.FilesAdminService = factory(jQuery);
    
})(app, jQuery, function ($) {
    
    function FilesAdminService () {
        
        this.url = 'http://localhost:8080/microservicio-control-de-archivos/rest-v1/admin-files';
        this.file = undefined;
        
        this.setFile = function (fileObj) {
            this.file = fileObj;
            return this;
        };
        
        this.ajaxConf = function (method, uriParams) {
            uriParams = uriParams || '';
            
            var ajaxConfiguration = {
                   url: `${this.url}/${uriParams}`,
                method: method,
              dataType: 'json'
            };

            var httpBodyData = ['POST','PUT','DELETE'];
            if (httpBodyData.find(m => m==method)
             && typeof this.file!= 'undefined') {
                ajaxConfiguration.contentType = 'application/json';
                ajaxConfiguration.data = JSON.stringify(this.file);
            }
            
            return ajaxConfiguration;
        }
        
        this.insert = function (rs,rj) {
            $.ajax(this.ajaxConf('POST'))
            .done(d => rs(d))
            .fail(e => rj(e));
        };
        
        this.update = function (id) {
            $.ajax(this.ajaxConf('PUT',`id/${id}`))
            .done(d => console.log(d))
            .fail(e => console.log(e));
        };
        
        this.delete = function (id, rs, rj) {
            $.ajax(this.ajaxConf('DELETE',`id/${id}`))
            .done(d => rs(d))
            .fail(e => rj(e));
        };
        
        this.select = function (id) {
            $.ajax(this.ajaxConf('GET',`id/${id}`))
            .done(d => console.log(d))
            .fail(e => console.log(e));
        };
        
        this.selectAll = function (rs, rj) {
            $.ajax(this.ajaxConf('GET'))
            .done(d => rs(d))
            .fail(e => rj(e));
        }
    }
    
    return new FilesAdminService();
});