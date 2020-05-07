package service;

import dao.KaiinDao;

public class AccountService {

	//入力チェック 0:問題なし　1:入力なし 2:半角英数以外　3:再入力ミス
	public static int checkInput(String oldPass, String newPass1, String newPass2) {
		if(oldPass.equals("") || newPass1.equals("") || newPass2.equals("")) {
			return 1;
		} else if(oldPass.matches("^[0-9a-zA-Z]+$") || newPass1.matches("^[0-9a-zA-Z]+$") || newPass2.matches("^[0-9a-zA-Z]+$")) {
			return 2;
		} else if(!newPass1.equals(newPass2)) {
			return 3;
		}
		return 0;
	}

	//旧パスワード確認 0:合致　1:相違
	public static int checkOldPass(String kaiincd, String oldPass) {
		String dbPass = KaiinDao.getPass(kaiincd);
		
		if(!oldPass.equals(dbPass)) {
			return 1;
		} else {
			return 0;
		}
	}


	//パスワード更新 0:成功　1:エラー
	public static int updatePass(String kaiincd, String newPass) {
		int rows = KaiinDao.changePass(kaiincd, newPass);
		
		if(rows == 1) {
			return 0;
		}else {
			return 1;
		}
	}
}
