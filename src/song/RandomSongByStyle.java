package song;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class RandomSongByStyle
 */
@WebServlet("/RandomSongByStyle")
public class RandomSongByStyle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandomSongByStyle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBaseUtil dataBaseUtil = DataBaseUtil.init();
		request.setCharacterEncoding("UTF-8");
		
		/**
		 * 需要接受参数：styleId
		 */
		String styleIdString = request.getParameter("styleId");
		if(styleIdString == null){
			styleIdString = "1";
		}
		int styleId = Integer.parseInt(styleIdString);
		ConnectionAndResultSet connectionAndResultSet = dataBaseUtil.SqlQuery("select * "
				               + "from song_catalog where StyleId = "+ styleId );
		ResultSet rSet = connectionAndResultSet.getResultSet();
		ArrayList<String> songsLinks = new ArrayList<String>();
		try {
			while(rSet.next()){
				
				songsLinks.add(rSet.getString("Link"));				
			}
			rSet.close();
			connectionAndResultSet.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String randomSongLink = songsLinks.get(getRandom(0, songsLinks.size() - 1));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<script type='text/javascript' src="
				+ randomSongLink +"></script>");
		printWriter.println(randomSongLink);
		
	}
	public int getRandom(int min, int max){
		
		return (int)(Math.random() * (max - min + 1 )) + min;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
