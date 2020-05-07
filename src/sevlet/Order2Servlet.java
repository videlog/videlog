package sevlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Kaiin;
import dto.Order;
import service.OrderService;
import service.SearchService;

/**
 * Servlet implementation class Order2Servlet
 */
@WebServlet("/order_2")
public class Order2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Order2Servlet() {
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

		//セッション取得
		HttpSession session = request.getSession();
		Kaiin kaiin = (Kaiin) session.getAttribute("Kaiin");
		
		//リクエスト取得
		Order order = new Order();
		order.setMeigaracd((String) request.getParameter("meigara_cd"));
		
		Order order2 = SearchService.getDetail(order);
		
		order2.setOrdercondition(request.getParameter("order_condition"));
		order2.setOrdernumber(Integer.parseInt(request.getParameter("order_number")));
		order2.setOrderprice(Integer.parseInt(request.getParameter("order_price")));
		order2.setExecutioncondition(request.getParameter("execution_condition"));
		order2.setKaiincd(kaiin.getKaiincd());
		
		
		//取引可否チェック
		int error = OrderService.checkTrading(kaiin.getKaiincd(), order.getOrderprice());
		if(error == 1) {
			request.setAttribute("errorMessage", "1日の取引上限金額3000万円を超えています。取引できません。");
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}else if(error == 2) {
			request.setAttribute("errorMessage", "取引額が口座残高を超えています。取引できません。");
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}
		
		//登録実行
		String orderNumber = OrderService.setOrder(order2);
		if(orderNumber == "") {
			request.setAttribute("errorMessage", "取引実行に失敗しました。");
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}
		
		//リクエストセット
		request.setAttribute("order_number", orderNumber);
		
		//画面遷移
		request.getRequestDispatcher("end_order.jsp").forward(request, response);
	}

}
