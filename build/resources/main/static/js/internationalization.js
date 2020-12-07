/**
 * 
 */

$(document).ready(function() {
	$(".locales").change(function() {
		var selectedOption = $('input[name="locales"]:checked').val();
		if (selectedOption != '') {
			$.ajax({
				url: "?lang=" + selectedOption,
				context: document.body
			}).done(function() {
				location.reload();
			});


		}
	});
});