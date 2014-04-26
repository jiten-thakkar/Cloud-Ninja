function authenticateDb(callback){
	console.log('here');
		
		var client = new Dropbox.Client({ key: "kxims84ljqwzxvp" });
		return client.authenticate(callback);
};
function showError(error) {
	  switch (error.status) {
	  case Dropbox.ApiError.INVALID_TOKEN:
	    authenticate();
	    break;

	  case Dropbox.ApiError.NOT_FOUND:
	    alert('File note found');
	    break;

	  case Dropbox.ApiError.OVER_QUOTA:
	    alert('Dropbox quota is full');
	    break;

	  case Dropbox.ApiError.RATE_LIMITED:
	    alert('Too much traffic. Please try again later.');
	    break;

	  case Dropbox.ApiError.NETWORK_ERROR:
	    alert('Network problem, try again.');
	    break;

	  case Dropbox.ApiError.INVALID_PARAM:
	  case Dropbox.ApiError.OAUTH_ERROR:
	  case Dropbox.ApiError.INVALID_METHOD:
	  default:
	    alert('Some technical error. Please refresh the page.');
	  }
	};