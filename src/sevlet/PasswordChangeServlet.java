package sevlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Kaiin;
import service.AccountService;

/**
 * Servlet implementation class PasswordChangeServlet
 */
@WebServlet("/password_change")
public class PasswordChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordChangeServlet() {
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
		
		//画面入力値取得
		String oldPass = request.getParameter("old_password");
		String newPass1 = request.getParameter("new_password");
		String newPass2 = request.getParameter("new_password_2");
		
		//入力チェック
		int error = AccountService.checkInput(oldPass, newPass1, newPass2);
		if(error == 1) {
			request.setAttribute("errorMessage", "未入力の項目があります。");
			request.getRequestDispatcher("change_password.jsp").forward(request, response);
		}else if(error == 2) {
			request.setAttribute("errorMessage", "半角英数字で入力してください。");
			request.getRequestDispatcher("change_password.jsp").forward(request, response);
		}else if(error == 3) {
			request.setAttribute("errorMessage", "パスワードの再入力が間違っています。");
			request.getRequestDispatcher("change_password.jsp").forward(request, response);
		}
		
		
		//旧パスワード確認
		error = AccountService.checkOldPass(kaiin.getKaiincd(), oldPass);
		if(error == 1) {
			request.setAttribute("errorMessage", "旧パスワードが間違っています。");
			request.getRequestDispatcher("change_password.jsp").forward(request, response);
		}
		
		//パスワード更新実行
		error = AccountService.updatePass(kaiin.getKaiincd(), newPass1);
		if(error == 1) {
			request.setAttribute("errorTitle", "システムエラー");
			request.setAttribute("errorMessage", "システムエラーが発生しました。再度ログインをお願いします。");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		//画面遷移
		request.setAttribute("changeTitle", "パスワード");
		request.getRequestDispatcher("end_info_change.jsp");
		
		
	}

}
