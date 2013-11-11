package song;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ConnectionAndResultSet;
import tools.DataBaseUtil;

/**
 * Servlet implementation class SelectStyle
 */
@WebServlet("/SelectStyle")
public class SelectStyle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStyle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		ConnectionAndResultSet connectionAndResultSet = dataBaseUtil.SqlQuery("select * from song_styles");
		HashMap<Integer, String> styleHashMap = new HashMap<>();
		ResultSet rSet = connectionAndResultSet.getResultSet();
		try {
			while(rSet.next()){
				styleHashMap.put(rSet.getInt("Id"), rSet.getString("Name"));				
			}
			rSet.close();
			connectionAndResultSet.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String viewString = new String();
		Iterator iterator = styleHashMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, String> entry = (Entry<Integer, String>)iterator.next();
			viewString = viewString + entry.getKey() + entry.getValue() + "<br/>";
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(viewString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
