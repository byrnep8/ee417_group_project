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
		fetchWithToken('http://34.244.173.87:8080/KlassyCafe/get/reservations')
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