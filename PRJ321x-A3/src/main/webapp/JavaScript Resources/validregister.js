/**
 * 
 */
 
 function validForm() {
	
	let uName = document.forms["regform"]["username"].value;
	let uMail = document.forms["regform"]["email"].value;
	let uPhone = document.forms["regform"]["userphone"].value;
	let uAddress = document.forms["regform"]["address"].value;
	let uPass = document.forms["regform"]["password"].value;
	let urePass = document.forms["regform"]["repassword"].value;
	
	if(uName == "" || uMail == "" || uPhone == "" || uAddress == "" || uPass == "" || urePass == "") {
		
		alert("do not leave information is emply");
		return false;
		
	}
	if(uPass != urePass) {
		
		alert("confirm pass does not match'");
		return false;
	}
	
}