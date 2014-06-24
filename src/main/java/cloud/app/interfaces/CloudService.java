package cloud.app.interfaces;

import cloud.app.utils.CloudFile;

import java.io.IOException;

public interface CloudService {
	CloudServiceResponse downloadFile(String filePath) throws IOException;
	CloudServiceResponse uploadFile(CloudFile inputFIle, long length) throws IOException;
}
