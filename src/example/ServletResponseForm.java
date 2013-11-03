package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletResponseForm
 */
@WebServlet("/ServletResponseForm")
public class ServletResponseForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletResponseForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * NOTICE: 字符集处理非常重要，对中文的处理必须使用UTF-8编码
		 *         1.tomcat服务器配置用UTF-8编码（这个我已经在conf/server.xml配置过了）
		 *         2.servlet接收请求用UTF-8编码：
		 *         3.servlet回复请求用UTF-8编码：
		 *         4.mysql数据库使用UTF-8编码：在mysql client里设置
		 *         5.eclipse里项目编码设置为UTF-8编码
		 *         
		 *         这样就保证
		 *         A.web页面不会乱码 
		 *         B.Servlet里处理的中文不会乱码
		 *         C.存入MySql的数据不会乱码
		 *         D.java代码里汉字不会乱码
		 */
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("CustomerName");
		String tel = request.getParameter("CustomerTel");
		FormData formData = new FormData(name, tel);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print( "收到表单数据："+"</br>"
				            +"客户姓名："+ name + "</br>" 
				            +"客户电话：" + tel
				         );
		
		
		
		
		
		
	}

}
