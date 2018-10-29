/**
 * 
 */
      function onSignIn(googleUser) {
      var profile = googleUser.getBasicProfile();
      $(".g-signin2").css("display","none");
      $(".googledata").removeClass("hide");
      $(".googleform").removeClass("hide");
      $(".signoutbutton").removeClass("hide");
      $("#name").text(profile.getName());
      $('#emailid').attr('value',profile.getEmail());
      $('#googleusername').attr('value',profile.getName());
   }    
function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function() {
		alert("signout success");
		$(".g-signin2").css("display","block");
	    $(".googledata").addClass("hide");
	      $(".googleform").addClass("hide");
	      $(".signoutbutton").addClass("hide");
	});
}
function editalert(){
	alert("Please upload the file with same name to update!!!")
}
function ValidateSize(file) {
    var FileSize = file.files[0].size / 1024 / 1024; // in MB
    if (FileSize > 10) {
        alert('File size exceeds 10 MB');        
    } 
}
$('#contact_email').on('input', function() {
	var input=$(this);
	var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	var is_email=re.test(input.val());
	if(is_email){input.removeClass("invalid").addClass("valid");}
	else{input.removeClass("valid").addClass("invalid");}
});