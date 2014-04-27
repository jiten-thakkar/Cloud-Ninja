package cloud.app.services.googledrive;

import cloud.app.interfaces.CloudServiceResponse;
import cloud.app.utils.CloudFile;
import cloud.app.utils.ResponceCode;

public class GoogleDriveResponse implements CloudServiceResponse {
	public int status = 0;
	public String message = null;
	public CloudFile cloudFile= null;

	public void setResponse(ResponceCode res) {
		this.status = res.getCode();
		this.message = res.getMessage();
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setCloudFile(CloudFile cloudFile) {
		this.cloudFile = cloudFile;
	}

	@Override
	public CloudFile getCloudFile() {
		return cloudFile;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
