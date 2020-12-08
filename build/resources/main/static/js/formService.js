/**
 * 
 */

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

/** Confirmation modal **/
var modal = document.getElementById("confirmationModal");
var btn = document.getElementById("sendFormButton");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

$('#btnNo').on('click', function()	{
    modal.style.display = "none";
})

$('#btnYes').on('click', function()	{
    modal.style.display = "none";
    $('#sendForm').submit();
})

$(document).ready(function() {

if (fileChangedAmount.length){
	var modalSummary = document.getElementById("summaryModal");
	modalSummary.style.display = "block";
}

$('#btnClose').on('click', function()	{
    modalSummary.style.display = "none";
})

});

