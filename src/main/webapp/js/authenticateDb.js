
function authenticateDb(){
	console.log('here');
		
		var client = new Dropbox.Client({ key: "kxims84ljqwzxvp" });
		return client.authenticate(function(error, client) {
			  if (error) {
			    
			    return showError(error);
			  }
			  $("#unlink_db").click(function(){
				 client.signOut({mustInvalidate: true}, function(error){
					 if(error) {
						 alert("Couldn't unlink");
					 }
					 $("#unlink_db").hide();
					 alert('Unlinked');
					 parent.location='index.html';
				 });
			  });
			  $("#unlink_db").show();
			  //console.log(client.credentials().token);
			  //showFileList(client);
		});
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
	function showFileList(client) {
		client.readdir("/", function(error, entries) {
			  if (error) {
			    return showError(error);  // Something went wrong.
			  }

			  //alert("Your Dropbox contains " + entries.join(". "));
			  //var myApp = angular.module('myApp', ['angularTreeview']);
			});
	};