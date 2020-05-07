package service;

import java.util.List;

import dao.OrderDao;
import dto.Order;

public class SearchService {

	//入力チェック
	public static int checkInput(String meigaracd, String meigaramei) {
		if(meigaracd.equals("") && meigaramei.equals("")) {
			//両方未入力
			return 1;

		} else if (!meigaracd.equals("") && !meigaracd.matches("^[0-9]+$")) {
			return 2;
			
		} else if (!meigaracd.equals("") && meigaracd.length() != 4) {
			return 3;
			
		} else if(meigaramei.equals("") && (meigaramei.matches("^[０-９ａ-ｚＡ-Ｚ]+$")|| 
						meigaramei.matches("-@+;:#$%&"))) {    //
			return 4;
			
		} else if(meigaramei.equals("") && meigaramei.matches("^[｡-ﾟ+]+$")) {
			return 5;
			
		}


		return 0;
	}

	//検索
	public static List<Order> searchMeigara(String meigaracd, String meigaramei) {
		List<Order> list = OrderDao.getSearchList(meigaracd, meigaramei);
		return list;
	}

	//銘柄詳細取得
	public static Order getDetail(Order order) {
		Order order2 = OrderDao.getMeigaraBy(order);
		return order2;
	}
}
