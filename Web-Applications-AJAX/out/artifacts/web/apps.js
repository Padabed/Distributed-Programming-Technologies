 $(document).ready(function() {
    $("input").blur(function() {
        $form = $('#calculator');
        $.post($form.attr('action'), $form.serialize(), function(responseText) {
            $('#result').text(responseText);
        });
        return false;
    });
});
 $(document).ready(function() {
     $("input").blur(function() {
         $form = $('#calculator2');
         $.get($form.attr('action'), $form.serialize(), function(responseText) {
             $('#result2').text(responseText);
         });
         return false;
     });
 });
