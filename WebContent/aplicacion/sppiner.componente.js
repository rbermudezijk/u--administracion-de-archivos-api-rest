(function (aplicación, $) {
    document.querySelector('body').innerHTML += `
    <div class="spinner-container spinner-container-no-show">
      <div class="spinner">
        <div class="double-bounce1"></div>
        <div class="double-bounce2"></div>
      </div>
    </div>`;

    aplicación.Sppiner = {
        Mostrar: () => $('.spinner-container')
                       .removeClass('spinner-container-no-show'),
        Ocultar: () => $('.spinner-container')
                       .addClass('spinner-container-no-show')
    }
})(aplicación, jQuery);