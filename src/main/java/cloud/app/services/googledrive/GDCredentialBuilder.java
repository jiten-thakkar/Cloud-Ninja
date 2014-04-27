package cloud.app.services.googledrive;

public class GDCredentialBuilder {
	private String token = null;
	private String expiresIn = null;
	private String state = null;


	public GDCredentialBuilder setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	public GDCredentialBuilder setState(String state) {
		this.state = state;
		return this;
	}

	public GDCredentialBuilder setToken(String token) {
		this.token = token;
		return this;
	}
	
	public GoogleDriveCredentials build() {
		return new GoogleDriveCredentials(token, expiresIn, state);
	}
}
