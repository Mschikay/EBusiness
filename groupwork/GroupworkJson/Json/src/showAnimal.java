import com.google.gson.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class showAnimal
 */
@WebServlet("/showAnimal")
public class showAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showAnimal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.getWriter().write("Hello World");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get Json data from client
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
		String name = data.get("name").getAsString();
		String state = data.get("state").getAsString();
	
		// process to select a response
		String species = new String();
		String food = new String();
		switch(name) {
		case "Meowsy":
			species = "Cat";
			switch(state) {
			case "likes":
				food = "fish";
				break;
			case "dislikes":
				food = "ham";
				break;
			};
			break;
		case "Barky":
			species = "Dog";
			switch(state) {
			case "likes":
				food = "bones";
				break;
			case "dislikes":
				food = "tuna";
				break;
			};
			break;
		case "Purrpaws" :
			species = "Cat";
			switch(state) {
			case "likes":
				food = "mice";
				break;
			case "dislikes":
				food = "cookie";
				break;
			};
			break;
		}
		
		// send response
		Animal toSend = new Animal(species, food);
	    String json = new Gson().toJson(toSend);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}
}
