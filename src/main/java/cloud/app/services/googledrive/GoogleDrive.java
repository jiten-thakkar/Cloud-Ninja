package cloud.app.services.googledrive;

import cloud.app.interfaces.CloudService;
import cloud.app.interfaces.CloudServiceResponse;
import cloud.app.interfaces.Credentials;
import cloud.app.utils.CloudFile;
import cloud.app.utils.ResponceCode;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;

public class GoogleDrive implements CloudService {

	/*private static String CLIENT_ID = "811756332091-e7j905odjq9sbmsodlc2cksp4oqfb7qi.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "6eV-wSHNvKACn5HxiIFeq7b_";
    private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";*/
    private Drive service;
	
	public GoogleDrive(Credentials serviceCredentials) throws IOException {
		GoogleDriveCredentials gdcredentials = (GoogleDriveCredentials) serviceCredentials;
		HttpTransport httpTransport = new NetHttpTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
	    GoogleTokenResponse response = new GoogleTokenResponse().setAccessToken(serviceCredentials.getToken())
	    		                       .setExpiresInSeconds(Long.valueOf(gdcredentials.getExpiresIn()))
	    		                       .setScope(gdcredentials.getState());
	    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
	    service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
	}

	@Override
	public CloudServiceResponse downloadFile(String filePath) {
		return null;
	}

	@Override
	public CloudServiceResponse uploadFile(CloudFile inputFile, long length)
			throws IOException {		
		 File body = new File();
		 body.setTitle(inputFile.getTitle());
		 body.setDescription(inputFile.getDescription());
		 body.setMimeType(inputFile.getMimeType());
		
		 GoogleDriveResponse res = new GoogleDriveResponse();		 
		 ByteArrayContent inputStream = new ByteArrayContent(inputFile.getMimeType(), inputFile.getData());
		 File file = service.files().insert(body, inputStream).execute();
		 File uploadedFileMetadata = service.files().get(file.getId()).execute();
		 if(uploadedFileMetadata.getOriginalFilename().equals(inputFile.getTitle())) {
			 res.setResponse(ResponceCode.SUCCESSFUL);
		 } else {
			 res.setResponse(ResponceCode.FAILED);
		 }
		 return res;
	}

}
