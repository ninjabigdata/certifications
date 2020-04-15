//Document on ready function
$(document).ready(function() {
	$.ajax({
		'url' : 'http://localhost:8080/property-tax/zonal-wise-report'
	}).then(
			function(response) {
				if (response) {
					let data = '';
					if (response.status === 'success') {
						$.each(response.response, function(id, zonal){
							let numberOfPropertyTypes = Object.keys(zonal.amountCollectedByPropertyType).length;
							let count = 0;
							data += '<tr><td style="text-align: center;" rowspan="' + numberOfPropertyTypes + '">' + zonal.zoneName + '</td>';
							Object.keys(zonal.amountCollectedByPropertyType).forEach(function(key) {
								data += '<td>' + key + '</td><td style="text-align: right;">' + zonal.amountCollectedByPropertyType[key].toLocaleString('us-US', { style: 'currency', currency: 'INR' }) + '</td></tr>';
								if (numberOfPropertyTypes !== count++) {
									data += '<tr>';
								}
							});
						});
					} else {
						data = '<tr><td colspan="3">' + response.errors.report + '</tr>';
					}
					$('#report-year').text(new Date().getFullYear());
					$('#report-container').empty().append(data);
				}
			});
});