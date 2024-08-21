package jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ConnectionPool {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	// 사용하지 않은 커넥션 즉, 초기 커넥션을 저장하는 변수
	private ArrayList<Connection> free;
	private ArrayList<Connection> used; // 사용중인 커넥션을 저장하는 변수
	private String url;
	private String user;
	private String password;
	private int initialCons; // 최초로 초기 커넥션수
	private int maxCons; // 최대 커넥션수
	private int numCons; // 총 Connection 수
	private static ConnectionPool cp; // 싱글톤 자르객체 참조변수가 private static 생성

	// 싱글톤 생성 (동기화 처리)
	public static ConnectionPool getInstance() {
		try {
			if (cp == null) {
				synchronized (ConnectionPool.class) {
					cp = new ConnectionPool();
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return cp;
	}

	// private 생성자
	private ConnectionPool() throws SQLException {
		/*
		 * 초기 커넥션 개수를 각각의 ArrayList에 자장할 수 있도록 초기 커넥션 수만큼 ArrayList 생성.
		 */

		String filePath = "D:/myProject/myjsp/teamfit/src/main/java/db.properties";
		Connection con = null;
		try {
			// db.properties로 db주소, 사용자명, 사용자 암호 로드하기
			Properties properties = new Properties();
			properties.load(new FileReader(filePath));
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			initialCons = Integer.parseInt(properties.getProperty("initialCons"));
			maxCons = Integer.parseInt(properties.getProperty("maxCons"));
			numCons = Integer.parseInt(properties.getProperty("numCons"));
			free = new ArrayList<Connection>(initialCons);
			used = new ArrayList<Connection>(initialCons);
			// initialCons 수만큼 Connection 생성(free)
			while (numCons < initialCons) {
				addConnection();
			}
		} catch (FileNotFoundException e) {
			System.out.println("db.properties 연결 실패");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("db.properties 연결 실패");
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	private void addConnection() {
		free.add(getNewConnection());
	}

	private Connection getNewConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("About to connect to " + con);
		++numCons; // 컨넥션 생성될 때 마다 숫자 증가
		return con;
	}

	/* free에 있는 커넥션을 used 로 옮기는 작업=>free--->used */
	public synchronized Connection getConnection() throws SQLException {
		/*
		 * free에 Connection이 없으면 maxCons만큼 Connection을 더 생성한다.
		 */
		if (free.isEmpty()) {
			while (numCons < maxCons) {
				addConnection();
			}
		}
		Connection _con;
		_con = free.get(free.size() - 1);
		free.remove(_con);
		used.add(_con);
		return _con;
	}

	/* used에 있는 커넥션을 free로 반납함. */
	public synchronized void releaseConnection(Connection _con) throws SQLException {
		boolean flag = false;
		if (used.contains(_con)) {
			used.remove(_con);
			numCons--;
			flag = true;
		} else {
			throw new SQLException("ConnectionPool" + "에 있지않네요!!");
		}
		try {
			if (flag) {
				free.add(_con);
				numCons++;
			} else {
				_con.close();
			}
		} catch (SQLException e) {
			try {
				_con.close(); // _con에 문제가 발생하면 닫아버린다
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}

	public void closeAll() {// 모든 Connection 자원을 반납함. // used에 있는 커넥션을 모두 삭제해 버림.
		for (int i = 0; i < used.size(); i++) {
			Connection _con = (Connection) used.get(i);
			used.remove(i--);
			try {
				_con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		// free에 있는 커넥션을 모두 삭제해 버림.
		for (int i = 0; i < free.size(); i++) {
			Connection _con = (Connection) free.get(i);
			free.remove(i--);
			try {
				_con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		numCons = 0;
	}
	
	public int getMaxCons() {
		return maxCons;
	}

	public int getNumCons() {
		return numCons;
	}
}
