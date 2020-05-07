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
import service.FavoriteService;
import service.SearchService;

/**
 * Servlet implementation class LoveAddServlet
 */
@WebServlet("/love_add")
public class LoveAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoveAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String meigaracd = (String) request.getParameter("love");
        Order order=new Order();
		
		order.setMeigaracd(meigaracd);
		
		//詳細取得
		Order order2 =SearchService.getDetail(order);
		request.setAttribute("result", order2);


		//登録チェック
		int error = FavoriteService.checkRegister(kaiin.getKaiincd(), meigaracd);
		if(error == 1) {
			request.setAttribute("message", "お気に入り登録済みです。");
			
		}else {

			//登録実行
			int rows = FavoriteService.favRegister(kaiin.getKaiincd(), meigaracd);
			if(rows == 1) {
				request.setAttribute("message", "システムエラーが発生しました。");
//				request.getRequestDispatcher("info.jsp").forward(request, response);
			}else {

				//画面遷移
				request.setAttribute("message", "お気に入り登録を行いました。");
//				request.getRequestDispatcher("info.jsp").forward(request, response);

			}
			
		}
		request.getRequestDispatcher("info.jsp").forward(request, response);
	}
	
}
