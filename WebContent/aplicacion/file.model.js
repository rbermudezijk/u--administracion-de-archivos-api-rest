'use strict';
var app = app || {};

(function (factory) {
    
    app.FileModel = factory();
    
})(function () {
    
    return function FileModel (fileObj, fileB64) {
        var fileData = fileObj.name.split('.'),
            fileContent = fileB64.split(':')[1].split(',');
        
        this.name      = fileData[0];
        this.extension = fileData[1];
        this.mimeType  = fileContent[0];
        this.content   = fileContent[1];
        console.log(this);
    };
});