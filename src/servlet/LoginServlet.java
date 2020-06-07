package sevlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Kaiin;
import service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}



	/*

	・相違回数更新のメソッドがLoginServiceにない
	・会員IDがない場合のメッソドがLoginServiceにない
	・ログインミスは会員情報に紐付けられないから、参照も追加もできなくない？？

	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//画面入力値取得
		String userid = request.getParameter("kaiincd");
		String userpassword = request.getParameter("password");


		//入力チェック（フロント）
		int checkLoginValue = LoginService.checkInput(userid,userpassword);


		switch(checkLoginValue) {
		case 1:

			request.setAttribute("errorMessage","未入力の項目があります。" );
			request.getRequestDispatcher("login.jsp").forward(request, response);	

			return;

		case 2:

			request.setAttribute("errorMessage","半角英数字で入力してください。" );
			request.getRequestDispatcher("login.jsp").forward(request, response);	

			return;
		case 0:

			//入力チェック（バック）
			int checkLoginError = LoginService.checkLogin(userid, userpassword);
			/*
			 ・会員IDがない場合のチェック		
			 */
			switch(checkLoginError) {
			//IDが存在しない
			case 1:
				request.setAttribute("errorMessage","会員IDかパスワードが間違っています。" );
				request.getRequestDispatcher("login.jsp").forward(request, response);	

				return;

				//パスワードエラー
			case 2:
				request.setAttribute("errorMessage","会員IDかパスワードが間違っています。" );

				//相違回数取得
				int missCount = LoginService.checkMissCount(userid);

				//相違回数が4回になる場合
				if(missCount + 1 == 4) {
					request.setAttribute("errorTitle", "取引停止");
					request.setAttribute("errorMessage", "ログインを４回間違えたため、取引停止となります。以降ログインできず、取引もできません。ヘルプデスクにお問い合わせください。");

					//取引停止処理
					int error = LoginService.stopTrading(userid);

					request.getRequestDispatcher("error.jsp").forward(request, response);
					
					return;
				}

				//相違回数加算
				LoginService.addMissCount(userid);
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
				return;

			case 0:
				
				//相違回数をクリア
				int clearMiss = LoginService.clearMissCount(userid);


				Kaiin user = LoginService.getKaiinBy(userid);

				//セッション生成
				HttpSession session = request.getSession();

				//セッションに会員DTOセット
				session.setAttribute("Kaiin", user);

				//画面遷移
				request.getRequestDispatcher("home.jsp").forward(request, response);	
				return;

			}
		}
	}
}
