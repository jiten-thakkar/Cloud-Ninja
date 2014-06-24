var CLIENT_ID = '811756332091-qd62pln0au6l3m7pbothev9d8l77pa7p.apps.googleusercontent.com';
var SCOPES = 'https://www.googleapis.com/auth/drive';

  function authoriseGD(callback) {
		gapi.auth.authorize(
				{'client_id': CLIENT_ID, 'scope': SCOPES, 'immediate': true},
				callback);
  }
