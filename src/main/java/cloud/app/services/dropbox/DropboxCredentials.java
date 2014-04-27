package cloud.app.services.dropbox;

import cloud.app.interfaces.Credentials;

public class DropboxCredentials implements Credentials {
	private String token = null;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
