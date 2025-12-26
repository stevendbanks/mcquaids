jQuery(document).ready(function($){
	

  	
  // Get current path and find target link
  var path = window.location.pathname;
  
  // Account for home page with empty path
  if ( path == '' ) {
    path = '/';
  }
      
  var target = $('nav a[href="'+path+'"]');
  
  console.log(path);
  // Add active class to target link
  target.addClass('active');
});

function checkAndSetSearchStartingLocationCookie(cookieValue) {
  const cookieName = "searchStartingLocation"; // Hardcoded cookie name
  // Check if the cookie exists
  if (document.cookie.indexOf(cookieName + "=") == -1) {
    // If the cookie doesn't exist, set a new cookie with a value
    document.cookie = cookieName + "=" + cookieValue;
  }
}  


function checkInput(input) {
    if (input.value) {
        input.classList.add('not-empty');
    } else {
        input.classList.remove('not-empty');
    }
}



