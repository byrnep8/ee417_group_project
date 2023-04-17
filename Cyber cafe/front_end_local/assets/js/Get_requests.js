
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

//When this function is called it gets all of the reservation data from the backend and populates the table with it
function getReservations() {
  fetchWithToken('http://localhost:8080/get/reservations')
    .then(response => response.json())
    .then(data => {
      
      const registrationData = document.getElementById('registration-data');
      registrationData.innerHTML = ''; // Clear the previous contents
      data.forEach(registration => {
        // Create table row for each registration
        const row = document.createElement('tr');
        //Format the date to be show as (dd/mm/yy)
        const date = new Date(`${registration.year}-${registration.month}-${registration.day}`);
        const formattedDate = date.toLocaleDateString('en-GB', { day: '2-digit', month: '2-digit', year: '2-digit' });
        row.innerHTML = `
          <td>${formattedDate}</td>
          <td>${registration.timeOfDay}</td>
          <td>${registration.firstName}</td>
          <td>${registration.lastName}</td>
          <td>${registration.email}</td>
          <td>${registration.phoneNum}</td>
          <td>${registration.numberPeople}</td>
          <td>${registration.message}</td>
        `;
        registrationData.appendChild(row);
      });
      // Get the table and rows
      const table = document.getElementById('registration-table');
      const rows = Array.from(table.getElementsByTagName('tr'));
      // Remove the header row from the list of rows to sort
      rows.shift();
      // Clear the table rows
      rows.forEach(row => table.removeChild(row));
      // Sort the rows by date
      rows.sort((a, b) => {
        const dateA = new Date(a.cells[0].textContent);
        const dateB = new Date(b.cells[0].textContent);
        return dateA - dateB;
      });
      // Add the sorted rows back to the table
      rows.forEach(row => table.appendChild(row));
    })
    .catch(error => console.error(error));
}

//Function to get the data that has been filtered using filterForm(). This is called in the filterForm function. Works same as above
function getFilteredReservations(){
  fetchWithToken('http://localhost:8080/get/reservations_specified')
    .then(response => response.json())
    .then(data => {
      const registrationData = document.getElementById('registration-data');
      // Clear the previous contents of the table
      registrationData.innerHTML = '';
      // Iterate through the JSON data and create table rows for each registration
      data.forEach(registration => {
        const row = document.createElement('tr');
        const date = new Date(`${registration.year}-${registration.month}-${registration.day}`);
        const formattedDate = date.toLocaleDateString('en-GB', { day: '2-digit', month: '2-digit', year: '2-digit' });

        row.innerHTML = `
          <td>${formattedDate}</td>
          <td>${registration.timeOfDay}</td>
          <td>${registration.firstName}</td>
          <td>${registration.lastName}</td>
          <td>${registration.email}</td>
          <td>${registration.phoneNum}</td>
          <td>${registration.numberPeople}</td>
          <td>${registration.message}</td>
        `;
        registrationData.appendChild(row);
      });
      // Get the table and rows
      const table = document.getElementById('registration-table');
      const rows = Array.from(table.getElementsByTagName('tr'));
      // Remove the header row from the list of rows to sort
      rows.shift();
      // Clear the table rows
      rows.forEach(row => table.removeChild(row));
      // Sort the rows by date
      rows.sort((a, b) => {
        const dateA = new Date(a.cells[0].textContent);
        const dateB = new Date(b.cells[0].textContent);
        return dateA - dateB;
      });
      // Add the sorted rows back to the table
      rows.forEach(row => table.appendChild(row));
    })
    .catch(error => console.error(error));
}

//Function to filter the form. It takes in the requested filters and posts them to the backend fot filtering
function filterForm() {
        // Get values from form inputs
        const timeOfDay = document.getElementById("timeOfDay").value;
        const day = parseInt(document.getElementById("day").value);
        const month = parseInt(document.getElementById("month").value);
        const year = parseInt(document.getElementById("year").value);

        // Create JSON object
        const reservationData = {
          timeOfDay: timeOfDay,
          day: day,
          month: month,
          year: year
        };

        // Send reservationData as JSON to server endpoint using POST method
        fetchWithToken("http://localhost:8080/post/reservations_info", {
          method: "POST",
          body: JSON.stringify(reservationData),
          headers: {
            "Content-type": "application/json; charset=UTF-8"
          }
        })
          .then(response => {
            console.log("Reservation data sent successfully!");
          })
          .catch(error => {
            console.error("Error sending reservation data:", error);
          });
          getFilteredReservations();
      }
      
//All funcitons below are used for form validation  
    
// get the input field elements
const dayInput = document.getElementById("day");
const monthInput = document.getElementById("month");
const yearInput = document.getElementById("year");

// add an event listener for the "input" event on the day input field
dayInput.addEventListener("input", function(event) {
	const dayValue = event.target.value;

    // if the day value is not a number or is outside the range of 0 to 31, set the value to 0
    if (isNaN(dayValue) || dayValue < 0 || dayValue > 31) {
      event.target.value = 0;
    }
  });

  // add an event listener for the "input" event on the month input field
  monthInput.addEventListener("input", function(event) {
    const monthValue = event.target.value;

    // if the month value is not a number or is outside the range of 0 to 12, set the value to 0
    if (isNaN(monthValue) || monthValue < 0 || monthValue > 12) {
      event.target.value = 0;
    }
  });

  // add an event listener for the "input" event on the year input field
  yearInput.addEventListener("input", function(event) {
    const yearValue = event.target.value;

    // if the year value is not 0, 2023, or 2024, set the value to 0
    if (yearValue !== "0" && yearValue !== "2023" && yearValue !== "2024") {
      event.target.value = 0;
    }
  });