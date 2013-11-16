package weather;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ConnectionAndResultSet;
import tools.DataBaseUtil;

/**
 * Servlet implementation class RecordedCity
 */
@WebServlet("/RecordedCity")
public class RecordedCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordedCity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String openID = request.getParameter("OpenID");
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		ConnectionAndResultSet connectionAndResultSet = dataBaseUtil.SqlQuery("select * "
				                 + "from weather_city where UserOpenID ='" + openID + "'");		
		ResultSet resultSet = connectionAndResultSet.getResultSet();
		ArrayList<String> cityArrayList = new ArrayList<String>();
		try {
			while(resultSet.next()){
				cityArrayList.add(resultSet.getString("City"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		response.setContentType("text/html; charset=UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
