/**
 * Form Service
 */

/** Remove alert notification after click field */

$('#findPhrase, #replacePhrase, #localDirectoryPat, #extension').on('click', function() {
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

// Close if click anywhere
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

// Button No
$('#btnNo').on('click', function() {
	modal.style.display = "none";
})

// Button Yes
$('#btnYes').on('click', function() {
	modal.style.display = "none";
	$('#sendForm').submit();
})

// Modal after replace.
$(document).ready(function() {

	if (fileChangedAmount.length) {
		var modalSummary = document.getElementById("summaryModal");
		modalSummary.style.display = "block";
	}

	// Button Close
	$('#btnClose').on('click', function() {
		modalSummary.style.display = "none";
	})
});