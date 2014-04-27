package cloud.app.interfaces;

import java.io.IOException;

import cloud.app.utils.CloudFile;

public interface CloudService {
	CloudServiceResponse downloadFile(String filePath) throws IOException;
	CloudServiceResponse uploadFile(CloudFile inputFIle, long length) throws IOException;
}
