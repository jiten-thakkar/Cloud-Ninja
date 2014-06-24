package cloud.app.servlets;

import cloud.app.services.dropbox.Dropbox;
import cloud.app.services.dropbox.DropboxCredentials;
import cloud.app.services.dropbox.DropboxResponse;
import cloud.app.services.googledrive.GDCredentialBuilder;
import cloud.app.services.googledrive.GoogleDrive;
import cloud.app.services.googledrive.GoogleDriveCredentials;
import cloud.app.services.googledrive.GoogleDriveResponse;
import cloud.app.utils.ResponceCode;
import com.google.gson.stream.JsonWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DBtoGD
 */
@WebServlet("/DBtoGD")
public class DBtoGD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBtoGD() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = String.valueOf(request.getParameter("filePath"));
		String DBToken = String.valueOf(request.getParameter("dbtoken"));
		String GDToken = String.valueOf(request.getParameter("gdtoken"));
		String GDTokenExpiresIn = String.valueOf(request.getParameter("gdtokenExpiresIn"));
		String GDTokenState = String.valueOf(request.getParameter("gdtokenState"));
		
		if(path == null || DBToken == null || GDToken == null) {
			response.setStatus(ResponceCode.CORRUPT_INPUT_DATA.getCode());
		}
		
		DropboxCredentials dbCredentials = new DropboxCredentials();
		dbCredentials.setToken(DBToken);
		GoogleDriveCredentials gdCredentials = new GDCredentialBuilder().setToken(GDToken)
				                               .setExpiresIn(GDTokenExpiresIn)
				                               .setState(GDTokenState)
				                               .build();
		gdCredentials.setToken(GDToken);
		Dropbox db = new Dropbox(dbCredentials);
		GoogleDrive gd = new GoogleDrive(gdCredentials);
		JsonWriter writer = new JsonWriter(response.getWriter());
		response.setContentType("text/json");
		try {
			DropboxResponse dbRes = (DropboxResponse) db.downloadFile(path);
			if(dbRes.getStatus() != ResponceCode.SUCCESSFUL.getCode()) {
				throw new Exception(dbRes.getMessage());
			}
			GoogleDriveResponse gdRes = (GoogleDriveResponse) gd
					                    .uploadFile(dbRes.getCloudFile(), dbRes.getCloudFile().getSize());
			if(gdRes.getStatus() != ResponceCode.SUCCESSFUL.getCode()) {
				throw new Exception(dbRes.getMessage());
			}
			writer.beginObject();
			writer.name("code").value(ResponceCode.SUCCESSFUL.getCode());
			writer.name("message").value(ResponceCode.SUCCESSFUL.getMessage());
			writer.endObject();
			
		} catch (Exception e) {
			writer.beginObject();
			writer.beginObject();
			writer.name("code").value(ResponceCode.FAILED.getCode());
			writer.name("message").value(ResponceCode.FAILED.getMessage());
			writer.endObject();
			
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
}
