package cloud.app.services.dropbox;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import cloud.app.interfaces.CloudService;
import cloud.app.interfaces.CloudServiceResponse;
import cloud.app.interfaces.Credentials;
import cloud.app.utils.CloudFile;
import cloud.app.utils.CloudFileBuilder;
import cloud.app.utils.ResponceCode;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

public class Dropbox implements CloudService {
	
	private DbxClient client;

	public Dropbox(Credentials serviceCredentials) {
		DbxRequestConfig config = new DbxRequestConfig(
	            "Cloud Ninja1/1.0", Locale.getDefault().toString());
		client = new DbxClient(config, serviceCredentials.getToken());
	}

	@Override
	public CloudServiceResponse downloadFile(String filePath) throws IOException {
		DropboxResponse response = new DropboxResponse();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
		    DbxEntry.File downloadedFile = client.getFile(filePath, null,
		        outputStream);
		    CloudFile file = new CloudFileBuilder().setData(outputStream.toByteArray())
		    												 .setTitle(downloadedFile.name)
		    												 .setSize(downloadedFile.numBytes).build();
		    response.setResponse(ResponceCode.SUCCESSFUL);
		    response.setCloudFile(file);
		    System.out.println("Metadata: " + downloadedFile.toString());
		    return response;
		} catch (DbxException | IOException e) {
			e.printStackTrace();
			response.setStatus(ResponceCode.FAILED.getCode());
			response.setMessage(ResponceCode.FAILED.getMessage());
			return response;
		} finally {
			outputStream.close();
		}
	}

	@Override
	public CloudServiceResponse uploadFile(CloudFile inputFile, long length) throws IOException {
		DropboxResponse response = new DropboxResponse();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(inputFile.getData());
		try {
		    DbxEntry.File uploadedFile = client.uploadFile("/magnum-opus.txt",
		        DbxWriteMode.add(), length, inputStream);
		    System.out.println("Uploaded: " + uploadedFile.toString());
		    response.setResponse(ResponceCode.SUCCESSFUL);
		    return response;
		} catch (DbxException | IOException e) {
			e.printStackTrace();
			response.setResponse(ResponceCode.FAILED);
			return response;
		} finally {
		    inputStream.close();
		}
	}

}
