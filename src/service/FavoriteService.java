package service;

import java.util.List;

import org.omg.CORBA.SystemException;

import dao.KaiinDao;
import dao.OrderDao;
import dto.Order;

public class FavoriteService {

	//登録チェック
	public static int checkRegister(String kaiincd, String meigaracd) {
		if(KaiinDao.checkRegister(kaiincd, meigaracd)) {
			return 1;
		} else {
			return 0;
		}
	}

	//登録実行 0:成功　1:エラー
	public static int favRegister(String kaiincd, String meigaracd) {
		int rows = KaiinDao.favRegister(kaiincd, meigaracd);
		if(rows == 1) {
			return 0;
		} else {
			return 1;
		}
	}

	//登録銘柄一覧取得
	public static List<Order> getOrderList(String kaiincd) {
		List<String> meigaraList = KaiinDao.getRegisterList(kaiincd);
		List<Order> list = OrderDao.getMeigaraList(meigaraList);
		return list;
	}
	
		
		
    

	//削除実行
	public static int deleteFav(String kaiincd, String meigaracd) {
		int rows = KaiinDao.deleteFav(kaiincd, meigaracd);
		if(rows == 1) {
			return 0;
		} else {
			return 1;
		}
	}
}
