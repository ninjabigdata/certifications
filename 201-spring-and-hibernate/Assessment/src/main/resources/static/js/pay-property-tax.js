// URLS for REST API
const restAPIBaseURL = 'http://localhost:8080/';
const restAPIMasterData = restAPIBaseURL + 'master-data/';
const restAPIPropertyTax = restAPIBaseURL + 'property-tax/';

// Document on ready function
$(document).ready(function() {
	// Load master data from REST API
	getMasterData('#zone', 'zone');
	getMasterData('#category', 'category');

	// Bind on click event handler for 'Pay Tax' button
	$('#payTax').click(function() {
		submitTax()
	});

	// Bind on focus event handler for 'Total Tax Payable' input
	// field
	$('#taxPayable').focus(function() {
		calculatePropertyTax();
	});

	// Disable form submit
	$("#selfAssessmentForm").submit(function(e) {
		e.preventDefault();
	});

});

// Function to get master data
function getMasterData(dropdownId, urlRelativePath) {
	$.ajax({
		'url' : restAPIMasterData + urlRelativePath
	}).then(
			function(response) {
				if (response) {
					if (response.status === 'success') {
						$(dropdownId).empty().append(
								getDropdownOptions(response.response));
					}
				}
			});
}

// Function to construct the options from response
function getDropdownOptions(response) {
	let data = '<option>Select</option>';
	$.each(response, function(id, masterData) {
		data += '<option value="' + masterData.id + '">' + masterData.code
				+ '</option>'
	});

	return data;
}

// Function to submit self assessment of property tax
function submitTax() {
	if (validateSelfAssessmentForm()) {
		// Validate if tax is computed
		if (validateNumber($('#taxPayable').val())) {
			$("p[name='taxPayable']").text('');
			$.ajax({
				url : restAPIPropertyTax + 'payment',
				type : 'post',
				data : toJSONString(),
			    contentType: "application/json",
				success : function(response) {
					if (response.status === 'success') {
						alert('Property tax paid successfully');
						window.location.href = "/";
					} else {
						displayErrors(response.errors);
					}
				}
			})
		} else {
			$("p[name='taxPayable']")
					.text(
							'Total Tax Payable is not caculated. Please focus or click the Total Tax Payable input text field.');
		}
	}
}

// Function to invoke property tax calculation REST API
function calculatePropertyTax() {
	if (validateSelfAssessmentForm()) {
		$("p[name='taxPayable']").text('');
		$.ajax({
			url : restAPIPropertyTax + 'calculator',
			type : 'post',
			data : toJSONString(),
		    contentType: "application/json",
			success : function(response) {
				if (response.status === 'success') {
					$('#taxPayable').val(response.response);
				} else {
					displayErrors(response.errors);
				}
			}
		})
	}
}

// Function to validate self assessment of property tax form
function validateSelfAssessmentForm() {
	let isFormValid = true;

	// Validate assessment year
	if (validateYear($('#assessmentYear').val())) {
		$("p[name='assessmentYear']").text('');
	} else {
		isFormValid = false;
		$("p[name='assessmentYear']").text(
				'Assessment Year cannot be empty or entered year is invalid');
	}

	// Validate name of the owner
	if (validateText($('#ownerName').val())) {
		$("p[name='ownerName']").text('');
	} else {
		isFormValid = false;
		$("p[name='ownerName']").text('Name of the Owner cannot be empty');
	}

	// Validate email
	let email = $('#email').val();
	var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i
	if (email !== undefined && email.match(pattern)) {
		$("p[name='email']").text('');
	} else {
		isFormValid = false;
		$("p[name='email']").text(
				'Email cannot be empty or entered email is invalid');
	}

	// Validate name of the owner
	if (validateText($('#propertyAddress').val())) {
		$("p[name='propertyAddress']").text('');
	} else {
		isFormValid = false;
		$("p[name='propertyAddress']").text(
				'Address of Property cannot be empty');
	}

	// Validate zonal classification
	if (validateDropdown($('#zone').val())) {
		$("p[name='zone']").text('');
	} else {
		isFormValid = false;
		$("p[name='zone']").text('Select Zonal Classification');
	}

	// Validate description of the property
	if (validateDropdown($('#category').val())) {
		$("p[name='category']").text('');
	} else {
		isFormValid = false;
		$("p[name='category']").text('Select Description of the Property');
	}

	// Validate status
	if (validateDropdown($('#status').val())) {
		$("p[name='status']").text('');
	} else {
		isFormValid = false;
		$("p[name='status']").text('Select Status');
	}

	// Validate Building constructed year
	if (validateYear($('#buildingConstructedYear').val())) {
		$("p[name='buildingConstructedYear']").text('');
	} else {
		isFormValid = false;
		$("p[name='buildingConstructedYear']")
				.text(
						'Building constructed year cannot be empty or entered year is invalid');
	}

	// Validate build up area (Square feet)
	if (validateNumber($('#buildUpArea').val())) {
		$("p[name='buildUpArea']").text('');
	} else {
		isFormValid = false;
		$("p[name='buildUpArea']")
				.text(
						'Build up area (Square feet) cannot be empty or entered value is invalid');
	}

	return isFormValid;
}

// Function to validate year
function validateYear(year) {
	let isValidYear = true;
	let currentYear = new Date().getFullYear();
	let pattern = '^[0-9]{4}$';

	if (year === undefined || !year.match(pattern) || year > currentYear) {
		isValidYear = false;
	}

	return isValidYear;
}

// Function to validate text fields
function validateText(text) {
	return !(text === undefined || text.trim() === '');
}

// Function to validate dropdown
function validateDropdown(value) {
	return (value !== undefined && value.trim() !== '' && value.toUpperCase() !== 'SELECT')
}

// Function to validate number
function validateNumber(number) {
	return (number !== undefined && number.trim() !== '' && !isNaN(number));
}

// Function to get self assessment property tax form data as object
function toJSONString() {
	var obj = {};
	var elements = $('#selfAssessmentForm')[0]
			.querySelectorAll("input, select, textarea");
	for (var i = 0; i < elements.length; ++i) {
		var element = elements[i];
		var name = element.name;
		var value = element.value;

		if (name) {
			obj[name] = value;
		}
	}

	return JSON.stringify(obj);
}

// Function to display errors from REST API
function displayErrors(errors) {
	Object.keys(errors).forEach(function(key) {
		$("p[name='" + key + "']").text(errors[key]);
	});
}
