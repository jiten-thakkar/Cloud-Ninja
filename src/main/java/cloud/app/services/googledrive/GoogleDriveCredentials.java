package cloud.app.services.googledrive;

import cloud.app.interfaces.Credentials;

public class GoogleDriveCredentials implements Credentials {
	private String token = null;
	private String expiresIn = null;
	private String state = null;

	public String getExpiresIn() {
		return expiresIn;
	}

	public GoogleDriveCredentials(String token, String expiresIn, String state) {
		super();
		this.token = token;
		this.expiresIn = expiresIn;
		this.state = state;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
