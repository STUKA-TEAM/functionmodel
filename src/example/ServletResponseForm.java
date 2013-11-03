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
		 * NOTICE: �ַ�������ǳ���Ҫ�������ĵĴ������ʹ��UTF-8����
		 *         1.tomcat������������UTF-8���루������Ѿ���conf/server.xml���ù��ˣ�
		 *         2.servlet����������UTF-8���룺
		 *         3.servlet�ظ�������UTF-8���룺
		 *         4.mysql���ݿ�ʹ��UTF-8���룺��mysql client������
		 *         
		 *         �����ͱ�֤
		 *         A.webҳ�治������ 
		 *         B.Servlet�ﴦ������Ĳ�������
		 *         C.����MySql�����ݲ�������
		 */
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("CustomerName");
		String tel = request.getParameter("CustomerTel");
		FormData formData = new FormData(name, tel);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print( "�յ������ݣ�"+"</br>"
				            +"�ͻ�������"+ name + "</br>" 
				            +"�ͻ��绰��" + tel
				         );
		
		
		
		
		
		
	}

}
