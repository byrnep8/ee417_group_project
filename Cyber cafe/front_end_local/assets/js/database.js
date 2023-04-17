function getToken() {
    var jwtBearerToken = localStorage.getItem("token");
	//console.log(jwtBearerToken);
    return jwtBearerToken;
}
  
function fetchWithToken(url, options) {
    const jwtBearerToken = getToken();

    if (jwtBearerToken) {
        options = options || {};
        options.headers = options.headers || {};
        options.headers.Authorization = `Bearer ${jwtBearerToken}`;
    }
    console.log(options.headers);
	return fetch(url, options);
}

function compareStrings(s1, s2){
	// console.log(s1);
	// console.log(s2);
	if ( s1 == s2 ){
		return true;
	}
	else{
		return false;
	}
}
	
// Get the role stored in local storage, if logged in as an admin check validity of token, display the reservations list link
function check_role(){
	var local_role = localStorage.getItem("roles");
	if( local_role == null ){

	}
	else{
		local_role = local_role.replace(/['"]+/g, '');
	}
	var s1 = "ADMIN";
	let token_valid = null;
	console.log(local_role);
	// If local Role not present, keep option hidden
	if( compareStrings(local_role,  "") || compareStrings(local_role, null) ){
		// document.getElementById("admin").hidden = "hidden";
		token_valid = false;
		console.log(local_role);
	}
	else if( compareStrings(local_role,  s1) ){
		// Sending Get request to check if token is valid if not set hidden as hidden
		fetchWithToken('http://localhost:8080/get/reservations')
		.then(response => response.json())
		.then(data => {
			token_valid = true;
			console.log("Token is valid");
		})
		.catch(error =>
			token_valid = false
		);
		
	}
	else{
		token_valid = false;
	}

	while( token_valid == null ){
		wait(200);
		console.log(token_valid);
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
  					