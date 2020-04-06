$(document).ready(function() {
	$.ajax({
		'url' : "http://localhost:8080/conference/user"
	}).then(function(data) {
		$('.firstName').append(data.firstName);
		$('.lastName').append(data.lastName);
		$('.age').append(data.age);
	});
});