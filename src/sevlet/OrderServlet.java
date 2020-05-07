package sevlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.*;
import service.OrderService;
import service.SearchService;

/**
 * Servlet implementation class Order
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String orderPrice = "";
		int error = 0;
		int orderTotal = 0;
		
		
		//セッション取得
		HttpSession session = request.getSession();
		Kaiin kaiin = (Kaiin) session.getAttribute("Kaiin");
		
		Order order_sub = new Order();
		
		String meigaracd = (String) request.getParameter("meigaracd");
		
		order_sub.setMeigaracd(meigaracd);
		
		Order order = SearchService.getDetail(order_sub);
		
		
		//画面入力値取得
		String orderNumber = request.getParameter("field1");
		String orderCondition = request.getParameter("ordercondition");
		if(orderCondition.equals("指値")) {
			orderPrice = request.getParameter("sashine");
		}
		String orderEx = request.getParameter("executioncondition");
		
		
		//入力チェック
		if(orderCondition.equals("指値")) {
			error = OrderService.checkInput(orderNumber, orderPrice);
		}else if (orderCondition.equals("成行")){
			error = OrderService.checkInput(orderNumber);
		}
			
		if(error != 0) {
			request.setAttribute("errorMessage", "1以上の半角数字を入力してください。");
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}
		
		
		//注文合計金額算出
		if(orderCondition.equals("指値")) {
			orderTotal = Integer.parseInt(orderPrice) * Integer.parseInt(orderNumber);
		}else if (orderCondition.equals("成行")){
			orderTotal = order.getStockprice() * Integer.parseInt(orderNumber);
		}
		
		
		//取引可否チェック
		error = OrderService.checkTrading(kaiin.getKaiincd(), orderTotal);
		if(error == 1) {
			request.setAttribute("errorMessage", "1日の取引上限金額3000万円を超えています。取引できません。");
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}else if(error == 2) {
			request.setAttribute("errorMessage", "取引額が口座残高を超えています。取引できません。");
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}
		
		//DTOにセット
		order.setOrdernumber(Integer.parseInt(orderNumber));
		order.setOrderprice(orderTotal);
		order.setOrdertype("買");
		order.setOrderstatus("注文中");
		order.setOrdercondition(orderCondition);
		order.setExecutioncondition(orderEx);
		
		
		//リクエストセット
		request.setAttribute("order", order);
		
		
		//画面遷移
		request.getRequestDispatcher("order2.jsp").forward(request, response);
		
	}

}
