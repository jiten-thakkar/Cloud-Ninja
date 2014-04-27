package cloud.app.interfaces;

import cloud.app.utils.CloudFile;

public interface CloudServiceResponse {
	public int getStatus();
	public String getMessage();
	public CloudFile getCloudFile();
}
