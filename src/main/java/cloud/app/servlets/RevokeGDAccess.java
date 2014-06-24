package cloud.app.servlets;

import cloud.app.utils.ResponceCode;
import com.google.gson.stream.JsonWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Servlet implementation class RevokeGDAccess
 */
@WebServlet("/RevokeGDAccess")
public class RevokeGDAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String USER_AGENT = "Mozilla/5.0";
	private String GDAccessTokenRevokeURL = "https://accounts.google.com/o/oauth2/revoke?token=";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevokeGDAccess() {
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
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = String.valueOf(request.getParameter("gdtoken"));
		JsonWriter writer = new JsonWriter(response.getWriter());
		response.setContentType("text/json");
		try {
			if(token == null) {
				throw new Exception("Empty access token");
			}
			URL revokeURL = new URL(GDAccessTokenRevokeURL+token);
			HttpURLConnection conn = (HttpURLConnection)revokeURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.connect();
			int responseCode = conn.getResponseCode();
			if(responseCode != 200) {
				throw new Exception("Token revocation failed");
			}				
			writer.beginObject();
			writer.name("code").value(ResponceCode.SUCCESSFUL.getCode());
			writer.name("message").value(ResponceCode.SUCCESSFUL.getMessage());
			writer.endObject();
		} catch (Exception e) {
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
