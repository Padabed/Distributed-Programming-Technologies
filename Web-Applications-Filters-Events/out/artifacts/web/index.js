$(document).ready(function(){ajax_post()});
$('#date').load(function() {
    ajax_post();
})

function callback(responseText){
    $("#date").html(responseText);
}

function ajax_post(){
    $.post("/web/msg.servlet",null,callback);
}
$(document).ready(function() {
    $("input").blur(function() {
        $form = $('#f1');
        $.get($form.attr('action'), $form.serialize(), function(responseText) {
            $('#result').text(responseText);
        });
        return false;
    });
});
setInterval(ajax_post,1000);
