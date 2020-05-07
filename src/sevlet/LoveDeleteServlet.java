package sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Kaiin;
import dto.Order;
import service.FavoriteService;

/**
 * Servlet implementation class LoveDeleteServlet
 */
@WebServlet("/love_delete")
public class LoveDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoveDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		//セッション取得
		HttpSession session = request.getSession();
		Kaiin kaiin = (Kaiin) session.getAttribute("Kaiin");
		
		//request取得
		String meigaracd = (String) request.getParameter("love");

		//削除実行
		int error = FavoriteService.deleteFav(kaiin.getKaiincd(), meigaracd);

		if(error == 1) {
			request.setAttribute("errorMessage", "システムエラーが発生しました。");
//			request.getRequestDispatcher("love.jsp").forward(request, response);
		}

		//画面遷移
		List<Order> list = FavoriteService.getOrderList(kaiin.getKaiincd());
		request.setAttribute("meigaraList", list);
		request.getRequestDispatcher("love.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
