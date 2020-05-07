package sevlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dto.Order;
import service.SearchService;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class MeigaraListout
 */
@WebServlet("/meigara_listout")
public class MeigaraListoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeigaraListoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		
		String meigaracd = request.getParameter("meigara_cd");
		String meigaramei= request.getParameter("meigara_name");
		
		int checkvalue = SearchService.checkInput(meigaracd,meigaramei);
				
				switch(checkvalue){
				case 1:
					request.setAttribute("errorMessage","未入力の項目があります。" );
					request.getRequestDispatcher("search.jsp").forward(request, response);	
				case 2:
					request.setAttribute("errorMessage","銘柄コードは数字で入力してください。");
					request.getRequestDispatcher("search.jsp").forward(request, response);
				case 3:
					request.setAttribute("errorMessage","銘柄コードは4文字で入力してください。");
					request.getRequestDispatcher("search.jsp").forward(request, response);
				case 4:
					request.setAttribute("errorMessage","銘柄名に全角英数字、または記号が含まれています。");
					request.getRequestDispatcher("search.jsp").forward(request, response);
				case 5:
					request.setAttribute("errorMessage","銘柄名に半角カナが含まれています。" );
					request.getRequestDispatcher("search.jsp").forward(request, response);
				
				case 0:
				
				List<Order>result = SearchService.searchMeigara(meigaracd,meigaramei);
				request.setAttribute("result", result);
				request.getRequestDispatcher("search_result.jsp").forward(request, response);
				
				}
		
	}

}