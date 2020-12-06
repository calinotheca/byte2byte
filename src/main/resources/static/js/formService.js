/**
 * 
 */

$('#sendFormButton').on('click', function()	{
	$('#sendForm').submit();
})

$('#findPhrase, #replacePhrase').on('click', function()	{
	$('#findPhrase').removeClass('field-error');
	$('#findPhraseError').remove();
	$('#replacePhrase').removeClass('field-error');
	$('#replacePhraseError').remove();

})