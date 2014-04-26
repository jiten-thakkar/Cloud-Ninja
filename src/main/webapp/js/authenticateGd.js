var CLIENT_ID = '811756332091-e7j905odjq9sbmsodlc2cksp4oqfb7qi.apps.googleusercontent.com';
var SCOPES = 'https://www.googleapis.com/auth/drive';

  /**
   * Called when the client library is loaded to start the auth flow.
   */
  /*function handleClientLoad() {
    window.setTimeout(checkAuth, 1);
  }*/

  /**
   * Check if the current user has authorized the application.
   */
  function authoriseGD(callback) {
	  $(document).ready(function(){
		gapi.auth.authorize(
				{'client_id': CLIENT_ID, 'scope': SCOPES, 'immediate': true},
				callback);
	  });
  }

  /**
   * Called when authorization server replies.
   *
   * @param {Object} authResult Authorization result.
   */
  function handleAuthResult(authResult) {
    /*var authButton = document.getElementById('authorizeButton');
    var filePicker = document.getElementById('filePicker');
    authButton.style.display = 'none';
    filePicker.style.display = 'none';*/
    if (authResult && !authResult.error) {
      // Access token has been successfully retrieved, requests can be sent to the API.
      /*filePicker.style.display = 'block';
      filePicker.onchange = uploadFile;*/
      alert(authResult.access_token);
    } else if(authResult.error) {
      // No access token could be retrieved, show the button to start the authorization flow.
      //authButton.style.display = 'block';
      console.log(authResult.error.toString());
      /*gapi.auth.authorize(
              {'client_id': CLIENT_ID, 'scope': SCOPES, 'immediate': false},
              handleAuthResult);*/
      }
  }