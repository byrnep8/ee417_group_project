function getToken() {
    var jwtBearerToken = localStorage.getItem("token");
    return jwtBearerToken;
  }
  
function fetchWithToken(url, options) {
    const jwtBearerToken = getToken();

    if (jwtBearerToken) {
        options = options || {};
        options.headers = options.headers || {};
        options.headers.Authorization = `Bearer ${jwtBearerToken}`;
    }
    // console.log(options.headers);
	return fetch(url, options);
}
	
// Get the role stored in local storage, if logged in as an admin check validity of token, display the reservations list link
function check_role(){
	var local_role = localStorage.getItem("roles");
	var token_valid = false;
	console.log(local_role);
	// If local Role not present, keep option hidden
	if( local_role == "" || local_role == null ){
		// document.getElementById("admin").hidden = "hidden";
		token_valid = false;
	}
	else if( local_role == "ADMIN" ){
		// Sending Get request to check if token is valid if not set hidden as hidden
		fetchWithToken('http://localhost:8080/get/reservations')
		.then(response => response.json())
		.then(data => {
			console.log("Token is valid");
			// document.getElementById("admin").hidden = "";
			token_valid = true;
		});
		
	}

	if ( token_valid ){
		document.getElementById("admin").hidden = "";
	}
	else{
		document.getElementById("admin").hidden = "hidden";
	}
}

check_role();

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
  				url: "http://localhost:8080/post/reservations",
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
  					