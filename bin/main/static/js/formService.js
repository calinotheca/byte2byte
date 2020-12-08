/**
 * 
 */

$('#sendFormButton').on('click', function()	{
	$('#sendForm').submit();
})

$('#findPhrase, #replacePhrase, #localDirectoryPat, #extension').on('click', function()	{
	$('#findPhrase').removeClass('field-error');
	$('#findPhraseError').remove();
	$('#replacePhrase').removeClass('field-error');
	$('#replacePhraseError').remove();
	$('#findPhraseErrorWrongSign').remove();
	$('#replacePhraseErrorWrongSign').remove();
	$('#localDirectoryPath').removeClass('field-error');
	$('#localDirectoryPathError').remove();
})