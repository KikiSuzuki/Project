package dao;

import util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
	DBUtil db=new DBUtil();
	
	public int executeUpdate(String sql, List<Object> params) throws SQLException {
		int row = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = db.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if(params != null && params.size() > 0) {
				for(int i=0;i<params.size();i++) {
					preparedStatement.setObject(i+1, params.get(i));
				}
			}
			row = preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, preparedStatement, connection);
		}
		return row;
	}

	public Object findSingleValue(String sql, List<Object> params) throws SQLException {
		Object object = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = db.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if(params!=null && params.size()>0) {
				for(int i=0;i<params.size();i++) {
					preparedStatement.setObject(i+1, params.get(i));
				}
			}
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				object = resultSet.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(resultSet, preparedStatement, connection);
		}
		return object;
	}

	public List queryRows(String sql, List<Object> params, Class cls) throws SQLException {
		List list = new ArrayList();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = db.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if(params!=null && params.size()>0) {
				for(int i=0;i<params.size();i++) {
					preparedStatement.setObject(i+1, params.get(i));
				}
			}
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while(resultSet.next()) {
				Object object = cls.newInstance();
				for(int i=1;i<=columnCount;i++) {
					String columnLabel = metaData.getColumnLabel(i);
					Field field = cls.getDeclaredField(columnLabel);
					String methodName = "set"+columnLabel.substring(0, 1).toUpperCase()+columnLabel.substring(1);
					Method method = cls.getDeclaredMethod(methodName, field.getType());
					method.invoke(object, resultSet.getObject(columnLabel));
				}
				list.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(resultSet, preparedStatement, connection);
		}
		return list;
	}

	public Object queryRow(String sql, List<Object> params, Class cls) throws SQLException {
		List list = queryRows(sql, params, cls);
		Object object = null;
		if (list !=null && list.size() >0) {
			object = list.get(0);
		}
		return object;
	}
}