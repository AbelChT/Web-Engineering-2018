$(document).ready(function () {
    registerSearch();
    registerTemplate();
});

function registerSearch() {
    $("#publish").submit(function (ev) {
        event.preventDefault();
        // Enviar el tweet
        $.get($(this).attr('action'), {q: $("#q").val()}, function (data) {
            if(data){
                // Twitter no permite que se hagan dos peticiones en un período de tiempo muy corto
                setTimeout(function(){
                    // Recibir el timeline
                    $.get("timeline", function(data, status){
                        $("#resultsBlock").html(Mustache.render(template, data));
                    });
                }, 500);
            }
        });
    });
}

function registerTemplate() {
    template = $("#template").html();
    Mustache.parse(template);
}
