var CLIENT_ID = '811756332091-e7j905odjq9sbmsodlc2cksp4oqfb7qi.apps.googleusercontent.com';
var SCOPES = 'https://www.googleapis.com/auth/drive';

  function authoriseGD(callback) {
		gapi.auth.authorize(
				{'client_id': CLIENT_ID, 'scope': SCOPES, 'immediate': true},
				callback);
  }