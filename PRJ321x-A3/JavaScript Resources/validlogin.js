
/*Validation value from form name and password is not emplty */
function validateForm() {
	  let x = document.forms["myForm"]["fname"].value;
	  let y = document.forms["myForm"]["fpass"].value;
	  if (x == "" || y == "") {
	    alert("Name and password must be filled out");
	    return false;
	  }
	}