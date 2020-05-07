package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.*;


public class KaiinDao {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/kaiindb";
	private static final String DB_USER = "postgres";
	private static final String PASSWORD = "password";
	private static final String JDBC_DRIVER = "org.postgresql.Driver";

	static {
		//unko
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e){
			System.out.println("JDBCドライバーのロードに失敗しました。");
			e.printStackTrace();
		}
	}


	//パスワード取得
	public static String getPass(String kaiincd) {
		final String sql = "select PASS from tkaiin where KAIIN_CD = ?";
		String password = null;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				ResultSet rSet = pStatement.executeQuery();

				if(rSet.next()) {
					password = rSet.getString("PASS");
				}else {
					password = null;  //変更済み　パスワードが見当たらなかったときpassにnullを設定
				}

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		//返り値
		return password;
	}


	//相違回数取得
	public static int getMissCount(String kaiincd) {
		final String sql = "select COUNT_LOGIN from tkaiin where KAIIN_CD = ?";
		int missCount = -1;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				ResultSet rSet = pStatement.executeQuery();

				if(rSet.next()) {
					missCount = rSet.getInt("COUNT_LOGIN");
				}

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		return missCount;
	}

	//相違回数加算
	public static int addMissCount(String kaiincd) {
		final String sql = "update tkaiin set COUNT_LOGIN = COUNT_LOGIN+1 where KAIIN_CD = ?";
		int rows = 0;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				rows = pStatement.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		return rows;

	}
	
	//会員情報取得
	public static Kaiin getKaiinBy(String kaiincd) {
		Kaiin kaiin = new Kaiin();
		final String sql = "select * from tkaiin,taccount where tkaiin.KAIIN_CD = ? and tkaiin.KAIIN_CD = taccount.KAIIN_CD";

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				ResultSet rSet = pStatement.executeQuery();

				if(rSet.next()) {
					kaiin.setKaiincd(kaiincd);
					kaiin.setKaiinname(rSet.getString("LAST_NAME") + rSet.getString("FIRST_NAME"));
					kaiin.setAccount(rSet.getInt("ACCOUNT"));
				}

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}


		return kaiin;
	}

	//相違回数クリア
	public static int clearMissCount(String kaiincd) {
		final String sql = "update tkaiin set COUNT_LOGIN = 0 where KAIIN_CD = ?";
		int rows = 0;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				rows = pStatement.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		return rows;

	}

	//最終時刻クリア
	public static int clearLastRequest(String kaiincd) {
		final String sql = "update tkaiin set TIME_LASTREQUEST = null where KAIIN_CD = ?";
		int rows = 0;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				rows = pStatement.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		return rows;
	}

	//最終時刻セット
	public static int setLastRequest(String kaiincd, Timestamp timestamp) {
		final String sql = "update tkaiin set TIME_LASTREQUEST = ? where KAIIN_CD = ?";
		int rows = 0;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setTimestamp(1, timestamp);
				pStatement.setString(2, kaiincd);

				//SQL実行
				rows = pStatement.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		return rows;

	}
	
	//最終時刻取得
	public static Timestamp getLastRequest(String kaiincd) {
		final String sql = "select * from tkaiin where KAIIN_CD = ?";
		Timestamp timestamp = null;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				ResultSet resultSet = pStatement.executeQuery();
				
				if(resultSet.next()) {
					timestamp = resultSet.getTimestamp("TIME_LASTREQUEST");
				}

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		return timestamp;

	}

	//パスワード変更
	public static int changePass(String kaiincd, String password) {
		final String sql = "update tkaiin set PASS = ? where KAIIN_CD = ?";
		int rows = 0;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, password);
				pStatement.setString(2, kaiincd);

				//SQL実行
				rows = pStatement.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		return rows;
	}



	//お気に入り登録確認
	public static boolean checkRegister(String kaiincd, String meigaracd) {
		final String sql = "select * from tfavorite where KAIIN_CD = ? and MEIGARA_CD = ?";
		boolean yes=false;
		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);
				pStatement.setString(2, meigaracd);
				

				//SQL実行
				ResultSet rSet = pStatement.executeQuery();

				if(rSet.next()) {
					yes=true;
				}else {
					yes=false;
				}
				
			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		//返り値
		return yes;
	}
	
	

	    
	


	//お気に入り登録
	public static int favRegister(String kaiincd, String meigaracd) {
		final String sql = "insert into tfavorite(KAIIN_CD,MEIGARA_CD,FAV_DATE) values(?,?,?)";
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		int rows = 0;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);
				pStatement.setString(2, meigaracd);
				pStatement.setTimestamp(3, timestamp);

				//SQL実行
				rows = pStatement.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		//返り値
		return rows;
	}

	//お気に入り一覧取得
	public static List<String> getRegisterList(String kaiincd) {
		List<String> list = new ArrayList<>();
		
		final String sql = "select * from tfavorite where KAIIN_CD = ?";

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				ResultSet rSet = pStatement.executeQuery();
				
				while(rSet.next()) {
					list.add(rSet.getString("MEIGARA_CD"));
				}


			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		//返り値
		return list;
	}
	

	//お気に入り削除
	public static int deleteFav(String kaiincd, String meigaracd) {
		final String sql = "delete from tfavorite where KAIIN_CD = ? and MEIGARA_CD = ?";
		int rows = 0;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);
				pStatement.setString(2, meigaracd);

				//SQL実行
				rows = pStatement.executeUpdate();

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		//返り値
		return rows;
	}
	
	//口座残高取得
		public static int getAccount(String kaiincd) {
			final String sql = "select * from taccount where kaiin_cd = ?";
			int account = 0;

			//データベース接続
			try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
				try(PreparedStatement pStatement = connection.prepareStatement(sql)){

					pStatement.setString(1, kaiincd);

					//SQL実行
					ResultSet resultSet = pStatement.executeQuery();
					
					if(resultSet.next()) {
						account = resultSet.getInt("ACCOUNT");
					}

				}
			} catch (SQLException e) {
				System.out.println("SQL:" + sql);
				e.printStackTrace();
			}

			//返り値
			return account;
		}
}
