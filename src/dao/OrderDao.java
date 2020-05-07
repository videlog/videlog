package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.*;

public class OrderDao {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/orderdb";
	private static final String DB_USER = "postgres";
	private static final String PASSWORD = "password";
	private static final String JDBC_DRIVER = "org.postgresql.Driver";

	static {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e){
			System.out.println("JDBCドライバーのロードに失敗しました。");
			e.printStackTrace();
		}
	}


	//検索結果取得
	public static List<Order> getSearchList(String meigaracd, String meigaramei) {
		List<Order> list = new ArrayList<>();
		final String sql = "select * from tmeigara tm, tshosai ts,tbusiness tb, tmarket tk "
				+ "where tm.MEIGARA_CD = ts.MEIGARA_CD and tm.MARKET_CD = tk.MARKET_CD and "
				+ "tm.BUSINESS_CD = tb.BUSINESS_CD and tm.MEIGARA_CD like ? and tm.MEIGARA_NAME like ?";
		

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, "%" + meigaracd + "%");
				pStatement.setString(2, "%" + meigaramei + "%");
				
				//SQL実行
				ResultSet rSet = pStatement.executeQuery();
				
				while(rSet.next()) {
					Order order = new Order();
					order.setMeigaracd(rSet.getString("MEIGARA_CD"));
					order.setMeigaraname(rSet.getString("MEIGARA_NAME"));
					order.setMarketname(rSet.getString("MARKET_NAME"));
					order.setBusinessname(rSet.getString("BUSINESS_NAME"));
					order.setStockprice(rSet.getInt("STOCK_PRICE"));
					list.add(order);
				}

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}
		
		return list;
	}

	//銘柄詳細取得
	public static Order getMeigaraBy(Order order) {
		final String sql = "select * from tmeigara tm, tshosai ts,tbusiness tb, tmarket tk where tm.MEIGARA_CD = ts.MEIGARA_CD and tm.MARKET_CD = tk.MARKET_CD and tm.BUSINESS_CD = tb.BUSINESS_CD and tm.MEIGARA_CD = ?";
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){
				
				pStatement.setString(1, order.getMeigaracd());
				System.out.println(pStatement.toString());
				//SQL実行
				ResultSet rSet = pStatement.executeQuery();
				
				if(rSet.next()) {
					order.setMeigaraname(rSet.getString("MEIGARA_NAME"));
					order.setMarketname(rSet.getString("MARKET_NAME"));
					order.setBusinessname(rSet.getString("BUSINESS_NAME"));
					order.setStockprice(rSet.getInt("STOCK_PRICE"));
					order.setStartprice(rSet.getString("START_PRICE"));
					order.setHighprice(rSet.getString("HIGH_PRICE"));
					order.setLowprice(rSet.getString("LOW_PRICE"));
					order.setUrivalue(rSet.getString("URI_VALUE"));
					order.setKaivalue(rSet.getString("KAI_VALUE"));
					order.setYearhigh(rSet.getString("YEAR_HIGH"));
					order.setYearlow(rSet.getString("YEAR_LOW"));
					order.setMarketname(rSet.getString("MARKET_NAME"));
					order.setBusinessname(rSet.getString("BUSINESS_NAME"));
					order.setMeigaraname(rSet.getString("MEIGARA_NAME"));
					order.setUnit(rSet.getString("UNIT"));
				}
				
			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}
		
		return order;
	}


	public static List<Order> getMeigaraList(List<String> meigaraList) {
		List<Order> list = new ArrayList<>();
		
	    final String sql = "select * from tmeigara tm, tshosai ts,tbusiness tb, tmarket tk where tm.MEIGARA_CD = ts.MEIGARA_CD and tm.MARKET_CD = tk.MARKET_CD and tm.BUSINESS_CD = tb.BUSINESS_CD and tm.MEIGARA_CD = ?";
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){
				
				for(int i = 0; i < meigaraList.size(); i++) {
					pStatement.setString(1, meigaraList.get(i));
					
					ResultSet rSet = pStatement.executeQuery();
					
					if(rSet.next()) {
						Order order = new Order();
						order.setMeigaracd(rSet.getString("MEIGARA_CD"));
						order.setMeigaraname(rSet.getString("MEIGARA_NAME"));
						order.setMarketname(rSet.getString("MARKET_NAME"));
						order.setBusinessname(rSet.getString("BUSINESS_NAME"));
						order.setStockprice(rSet.getInt("STOCK_PRICE"));
						list.add(order);
					}
				}
			

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}
		return list;
	}
	
	
	


	//取引登録
	public static String setOrder(Order order) {
		final String sql = "insert into torder(KAIIN_CD,MEIGARA_CD,ORDER_NUMBER,ORDER_PRICE,ORDER_TYPE,"
				+ "ORDER_STATUS,ORDER_CONDITION,EXECUTION_CONDITION,ORDER_DATE) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		final String sql2 = "update torder set ORDER_CD = ? where AUTO_ID = ?";
		
		int rows = 0;
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		Integer gkey = null;
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		String datePrint = sdf1.format(date);
		
		String ordercd = "";

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

				pStatement.setString(1, order.getKaiincd());
				pStatement.setString(2, order.getMeigaracd());
				pStatement.setInt(3, order.getOrdernumber());
				pStatement.setInt(4, order.getOrderprice());
				pStatement.setString(5, order.getOrdertype());
				pStatement.setString(6, order.getOrderstatus());
				pStatement.setString(7, order.getOrdercondition());
				pStatement.setString(8, order.getExecutioncondition());
				pStatement.setTimestamp(9, timestamp);

				//SQL実行
				rows = pStatement.executeUpdate();
				ResultSet rSet = pStatement.getGeneratedKeys();
				
				if(rSet.next()) {
					gkey = (int) rSet.getLong("AUTO_ID");
				}
			}
			
			if(gkey != null) {
				try(PreparedStatement pStatement = connection.prepareStatement(sql2)){
					int lenghth = String.valueOf(gkey).length();
					String number = "";
					
					for(int i = 0; i < (6 - lenghth); i++) {
						number += "0";
					}
					number += String.valueOf(gkey);
					
					
					ordercd = datePrint + "-" + number;
					pStatement.setString(1, ordercd);
					pStatement.setInt(2, gkey);
					
					rows = pStatement.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}
		
		if(rows == 0) {
			return null;
		}else {
			return ordercd;
		}
	}

	//注文一覧取得
	public static List<Order> getOrderList(String kaiincd) {
		List<Order> list = new ArrayList<>();
		final String sql = "select * from tmeigara tm,torder td,tshosai ts where tm.MEIGARA_CD = td.MEIGARA_CD and tm.MEIGARA_CD = ts.MEIGARA_CD and td.KAIIN_CD = ?";
			
		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);
				System.out.println(pStatement.toString());
				//SQL実行
				ResultSet rSet = pStatement.executeQuery();
				
				while(rSet.next()) {
					Order order = new Order();
					order.setOrdercd(rSet.getString("ORDER_CD"));
					order.setMeigaracd(rSet.getString("MEIGARA_CD"));
					order.setMeigaraname(rSet.getString("MEIGARA_NAME"));
					order.setOrdernumber(rSet.getInt("ORDER_NUMBER"));
					order.setOrderprice(rSet.getInt("ORDER_PRICE"));
					order.setOrderdate(rSet.getTimestamp("ORDER_DATE"));
					order.setOrdertype(rSet.getString("ORDER_TYPE"));
					order.setOrderstatus(rSet.getString("ORDER_STATUS"));
					order.setStockprice(rSet.getInt("STOCK_PRICE"));
					list.add(order);
				}

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}
		
		return list;
	}

	//注文詳細取得
	public static Order getOrderBy(String ordercd) {
		Order order = new Order();
		final String sql = "select * from torder td, tshosai ts,tmeigara tm, tmarket tk, tbusiness tb"
				+ " where tm.MEIGARA_CD = td.MEIGARA_CD and td.MEIGARA_CD = ts.MEIGARA_CD "
				+ "and tb.BUSINESS_CD = tm.BUSINESS_CD and tk.MARKET_CD = tm.MARKET_CD and ORDER_CD = ?";
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){
				
				pStatement.setString(1, ordercd);
				
				//SQL実行
				ResultSet rSet = pStatement.executeQuery();
				
				if(rSet.next()) {
					order.setOrdercd(ordercd);
					order.setMeigaracd(rSet.getString("MEIGARA_CD"));
					order.setMeigaraname(rSet.getString("MEIGARA_NAME"));
					order.setOrdernumber(rSet.getInt("ORDER_NUMBER"));
					order.setOrderprice(rSet.getInt("ORDER_PRICE"));
					order.setOrderdate(rSet.getTimestamp("ORDER_DATE"));
					order.setOrdertype(rSet.getString("ORDER_TYPE"));
					order.setOrderstatus(rSet.getString("ORDER_STATUS"));
					order.setStockprice(rSet.getInt("STOCK_PRICE"));
					order.setStartprice(rSet.getString("START_PRICE"));
					order.setHighprice(rSet.getString("HIGH_PRICE"));
					order.setLowprice(rSet.getString("LOW_PRICE"));
					order.setUrivalue(rSet.getString("URI_VALUE"));
					order.setKaivalue(rSet.getString("KAI_VALUE"));
					order.setYearhigh(rSet.getString("YEAR_HIGH"));
					order.setYearlow(rSet.getString("YEAR_LOW"));
					order.setMarketname(rSet.getString("MARKET_NAME"));
					order.setBusinessname(rSet.getString("BUSINESS_NAME"));
				}
				
			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}
		
		
		return order;
	}

	//取引合計取得
	public static int getOrderTotal(String kaiincd, Timestamp timestamp) {
		final String sql = "select * from torder where KAIIN_CD = ?";
		
		LocalDateTime localDateTime = timestamp.toLocalDateTime();
		String date1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		int orderTotal = 0;
		Timestamp timestamp2;

		//データベース接続
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD)){
			try(PreparedStatement pStatement = connection.prepareStatement(sql)){

				pStatement.setString(1, kaiincd);

				//SQL実行
				ResultSet rSet = pStatement.executeQuery();

				while(rSet.next()) {
					timestamp2 = rSet.getTimestamp("ORDER_DATE");
					
					LocalDateTime localDateTime2 = timestamp2.toLocalDateTime();
					String date2 = localDateTime2.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
					
					if(date1.equals(date2)) {
						orderTotal += rSet.getInt("ORDER_PRICE");
					}
					
					
				}

			}
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
		}

		//返り値
		return orderTotal;
	}
//    public static void main(String[] args) {
//    	Order testorder = new Order();
//    	testorder.setMeigaracd("1301");
//    	Order test=getMeigaraBy(testorder);
//    	System.out.println(test.toString());
////    	test.stream().forEach(o -> {System.out.println(o.toString());});
//    }
}

