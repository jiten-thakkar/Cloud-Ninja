package cloud.app;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbox.core.*;

import java.io.*;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Servlet implementation class Initial
 */
public class Initial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String APP_KEY = "kxims84ljqwzxvp";
    final String APP_SECRET = "cj0yjlg7sa0y4nx";
    
    DbxWebAuthNoRedirect webAuth;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Initial() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
            "Cloud Ninja1/1.0", Locale.getDefault().toString());
        webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		//webAuth = new DbxWebAuth(config, appInfo, redirectUri, csrfTokenStore)
		String authorizeUrl = webAuth.start();
		System.out.println(authorizeUrl);
		Gson gson = new GsonBuilder().create();
		PrintWriter writer = response.getWriter();
		gson.toJson(authorizeUrl, writer);
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
