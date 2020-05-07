//package sevlet;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import dto.Kaiin;
//import service.LoginService;
//
///**
// * Servlet Filter implementation class SessionTimeoutServlet
// */
//@WebFilter("/*")
//public class SessionTimeoutServlet implements Filter {
//
//	/**
//	 * Default constructor. 
//	 */
//	public SessionTimeoutServlet() {
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		// place your code here
//
////		int error = 0;
////
////		//セッション取得
////		HttpSession session = ((HttpServletRequest)request).getSession();
////
////		if(!session.isNew()) {
////			Kaiin kaiin = (Kaiin)session.getAttribute("Kaiin");
////
////			//セッションタイムアウトチェック
////			error = LoginService.checkSessionTimeout(kaiin.getKaiincd());
////			if(error == 1) {
////				request.setAttribute("errorTitle", "セッションタイムアウト");
////				request.setAttribute("errorMessage", "１０分間リクエストがなかったため、ログアウトしました。");
////
////				//最終時刻クリア
////				int error2 = LoginService.clearLastRequest(kaiin.getKaiincd());
////
////				//セッション破棄
////				session.invalidate();
////
////				request.getRequestDispatcher("error.jsp").forward(request, response);
////			}
////
////			//最終時刻セット
////			error = LoginService.setLastRequest(kaiin.getKaiincd());
////
////
////		}
//
//		chain.doFilter(request, response);
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//
//}
