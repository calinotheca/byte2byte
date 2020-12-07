/**
 * 
 */

$('#sendFormButton').on('click', function()	{
	$('#sendForm').submit();
})

$('#findPhrase, #replacePhrase, #localDirectoryPat').on('click', function()	{
	$('#findPhrase').removeClass('field-error');
	$('#findPhraseError').remove();
	$('#replacePhrase').removeClass('field-error');
	$('#replacePhraseError').remove();
	$('#localDirectoryPath').removeClass('field-error');
	$('#localDirectoryPathError').remove();
})