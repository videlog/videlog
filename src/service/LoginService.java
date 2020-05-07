package service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import dao.KaiinDao;
import dto.Kaiin;

public class LoginService {

	private static final String stopTradingPassword = "team16";

	//入力チェック 0:問題なし　1:未入力　2:半角英数以外
	public static int checkInput(String kaiincd, String password) {

		if(kaiincd.equals("") || password.equals("")) {
			return 1;
		} else if(!kaiincd.matches("^[0-9a-zA-Z]+$") || !password.matches("^[0-9a-zA-Z]+$")) {
			return 2;
		}

		return 0;
	}

	//ログインチェック 0:問題なし　1:IDが存在しない　２：パスワードエラー
	public static int checkLogin(String kaiincd, String password) {

		String checkPass = KaiinDao.getPass(kaiincd);

		if(checkPass == null) {
			return 1;
		} else if(!checkPass.equals(password)) {
			return 2;
		} else {
			return 0;
		}
	}

	//相違回数チェック
	public static int checkMissCount(String kaiincd) {
		return KaiinDao.getMissCount(kaiincd);
	}
	//相違回数加算
	public static int addMissCount(String kaiincd) {
		int error = KaiinDao.addMissCount(kaiincd);
		if(error == 0) {
			return 1;
		}else {
			return 0;
		}
	}

	//会員情報取得
	public static Kaiin getKaiinBy(String kaiincd) {
		return KaiinDao.getKaiinBy(kaiincd);
	}

	//相違回数クリア 0:問題なし　1:エラー
	public static int clearMissCount(String kaiincd) {
		int rows = KaiinDao.clearMissCount(kaiincd);

		if(rows == 0) {
			return 1;
		}else {
			return 0;
		}
	}

	//取引停止処理 0:問題なし　1:エラー
	public static int stopTrading(String kaiincd) {

		int rows = KaiinDao.changePass(kaiincd, stopTradingPassword);

		if(rows == 0) {
			return 1;
		}else {
			return 0;
		}
	}

	//リクエスト最終時刻クリア
	public static int clearLastRequest(String kaiincd) {
		int rows = KaiinDao.clearLastRequest(kaiincd);

		if(rows == 0) {
			return 1;
		}else {
			return 0;
		}
	}


	//リクエスト最終時刻セット
	public static int setLastRequest(String kaiincd) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		int rows = KaiinDao.setLastRequest(kaiincd, timestamp);

		if(rows == 0) {
			return 1;
		}else {
			return 0;
		}
	}


	//セッションタイムアウトチェック
	public static int checkSessionTimeout(String kaiincd) {
		//現時刻取得
		LocalDateTime nowTime = LocalDateTime.now();

		//最終リクエスト時刻取得
		LocalDateTime lastReqTime = KaiinDao.getLastRequest(kaiincd).toLocalDateTime();

		//チェック
		Duration duration = Duration.between(nowTime, lastReqTime);
		if(duration.getSeconds() > 600) {
			return 1;
		}

		return 0;
	}

}
