import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;


public class Register extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		super.doPost(req, resp);
		
		String u = req.getParameter("username");
		String p = req.getParameter("password");
		
		UserBeam user = new UserBeam();
		user.setUsername(u);
		user.setPassword(p);
		
		RegisterImp rimp = new RegisterImp();
		int i = rimp.registerUser(user);
		
		if (i>0) {
			System.out.println("Register successful");
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("REGISTER", "SUCCESS");
				PrintWriter pw = resp.getWriter();
				pw.write(jsonObject.toString());
				pw.print(jsonObject.toString());
				
				System.out.println("REGISTER successful" + jsonObject.toString());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
