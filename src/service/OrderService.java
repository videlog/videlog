package service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import dao.KaiinDao;
import dao.OrderDao;
import dto.Order;

public class OrderService {

	//入力チェック　成行の場合 0:問題なし　1:入力なし 2:半角数値以外　３：0入力
	public static int checkInput(String orderNumber) {
		
		if(orderNumber.equals("")) {
			return 1;
		}else if(!orderNumber.matches("^[0-9]+$")) {
			return 2;
		}else if(Integer.parseInt(orderNumber) == 0) {
			return 3;
		}else {
			return 0;
		}
		
	}

	//入力チェック　指値の場合 0:問題なし　1:入力なし　2:半角数値以外　3:0入力
	public static int checkInput(String orderNumber, String orderAmount) {
		
		if(orderNumber.equals("") || orderAmount.equals("")) {
			return 1;
		}else if(!orderNumber.matches("^[0-9]+$") || !orderAmount.matches("^[0-9]+$")) {
			return 2;
		}else if(Integer.parseInt(orderNumber) == 0 || Integer.parseInt(orderAmount) == 0) {
			return 3;
		}else {
			return 0;
		}
		
	}

	//取引可否チェック 0:問題なし　1:一日の上限　2:口座
	public static int checkTrading(String kaiincd, int orderTotal) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		int total = OrderDao.getOrderTotal(kaiincd, timestamp);
		
		if((total + orderTotal) > 30000000) {
			return 1;
		}
		
		int account = KaiinDao.getAccount(kaiincd);
		
		if(orderTotal > account) {
			return 2;
		}
		
		return 0;
	}


	//取引登録 受付番号を返す
	public static String setOrder(Order order) {
		String ordercd = OrderDao.setOrder(order);
		return ordercd;
	}

	//注文一覧取得
	public static List<Order> getOrderList(String kaiincd) {
		List<Order> list = OrderDao.getOrderList(kaiincd);
		return list;
	}


	//注文詳細取得
	public static Order getOrderDetail(String orderCd) {
		Order order = OrderDao.getOrderBy(orderCd);
		return order;
	}
}
