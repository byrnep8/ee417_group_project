//Function to post the data inputted in the form to the submit-team endpoint running on the Spring server
$(document).ready(function() {
	$("#form-submit").click(function(e) { //listens for the form to be submitted
  		e.preventDefault();

            // Split the date string into an array of substrings
			console.log($("#date").val());
            // Convert each substring to an integer
            const day = $("#date").val().substring(0,2); 
            console.log(day);
            const month = $("#date").val().substring(3,5);
            console.log(month);
            const year = $("#date").val().substring(6,10);
            console.log(year);
  							
  			var formData = {  							//Formats the inputs as json
  				firstName: $("#firstName").val(),
  				lastName: $("#lastName").val(),
  				email: $("#email").val(),
  				phoneNum: $("#phone").val(),
  				numberPeople: $("#number-guests").val(),
  				timeOfDay: $("#time").val(),
  				day: day,
  				month: month,
  				year: year,
  				message: $("#message").val()
  				};
  							
  			//stringify's the JSONm data and POST;'s it to the submit-team endpoint to nbe stored
  			var jsonData = JSON.stringify(formData);
  			console.log(jsonData);
  			$.ajax({
  				url: "http://http://34.244.173.87:8080/KlassyCafe/post/reservations",
  				type: "POST",
  				data: jsonData,
  				contentType: "application/json; charset=utf-8",
  				dataType: "json",
  				success: function(response) {
  					console.log(response);
  				},
  				error: function(xhr, status, error) {
  					console.log(error);
  				}
  			});
  		});
  						
  	
  });
  					