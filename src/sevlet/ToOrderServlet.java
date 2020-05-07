package sevlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Order;
import service.SearchService;

/**
 * Servlet implementation class ToOrder
 */
@WebServlet("/to_order")
public class ToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//リクエスト取得
		String meigaracd = (String) request.getParameter("meigara_cd");
		
		//DTOにセット
		Order order=new Order();
		order.setMeigaracd(meigaracd);
		
		//銘柄詳細取得
		Order order2 =SearchService.getDetail(order);
		
		//リクエストにセット
		request.setAttribute("result", order2);
		
		//画面遷移
		request.getRequestDispatcher("order.jsp").forward(request, response);
	}

}
