package ru.mars.JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCTestHabr {
	public static void main(String[] args) {

        Connection connection = null;
        //URL � ���� ������� �� ���������:������������://[�����]:[�����_����]/[��] � ������_��������
        String url = "jdbc:postgresql://127.0.0.1:5432/test";
        String name = "user"; //��� ������������ ��
        String password = "123456";  //������
        try {
            //��������� �������
            Class.forName("org.postgresql.Driver");
            System.out.println("������� ���������");
            //������ ����������
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("���������� �����������");
            //��� ������������� SQL �������� ���������� 3 ���� ��������:
            //1.Statement: ������������ ��� ������� ������� ��� ����������
            Statement statement = null;

            statement = connection.createStatement();
            //�������� ������
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM users where id >2 and id <10");
            //result ��� ��������� �� ������ ������ � �������
            //����� ������� ������ �� ����� ������������ 
            //����� next() , � ������� �������� ��������� � ���������� ��������
            System.out.println("������� statement");
            while (result1.next()) {
                System.out.println("����� � ������� #" + result1.getRow()
                        + "\t ����� � ���� #" + result1.getInt("id")
                        + "\t" + result1.getString("username"));
            }
            // �������� ������
            statement.executeUpdate(
                    "INSERT INTO users(username) values('name')");
            //�������� ������
            statement.executeUpdate(
                    "UPDATE users SET username = 'admin' where id = 1");
            
            //2.PreparedStatement: �������������� ����������� �������, 
            //������� ����� ��������� ������� ���������
            PreparedStatement preparedStatement = null;
            // ? - ����� ������� ������ �������
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users where id > ? and id < ?");
            //������������� � ������ ������� �������� ������������ ����
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 10);
            //��������� ������
            ResultSet result2 = preparedStatement.executeQuery();
            
            System.out.println("������� PreparedStatement");
            while (result2.next()) {
                System.out.println("����� � ������� #" + result2.getRow()
                        + "\t ����� � ���� #" + result2.getInt("id")
                        + "\t" + result2.getString("username"));
            }
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(username) values(?)");
            preparedStatement.setString(1, "user_name");
            //����� ��������� �������� ��� ����������
            //����� �������� ����� ������� � UPDATE
            preparedStatement.executeUpdate();
            
            //3.CallableStatement: ������������ ��� ������ �������� �������,
            // ������� ����� ��������� ������� � �������� ���������
            CallableStatement callableStatement = null;
            //�������� ������� myFunc (�������� � ��)
            callableStatement = connection.prepareCall(
                    " { call myfunc(?,?) } ");
            //����� ������� ���������
            callableStatement.setString(1, "Dima");
            callableStatement.setString(2, "Alex");
            //��������� ������
            ResultSet result3 = callableStatement.executeQuery();
            //���� CallableStatement ���������� ��������� �������� ResultSet,
            //�� ����� �������� ������ � ����� � ������� ������ next
            //� ���� ������� ���������� ���� ������
            result3.next();
            System.out.println(result3.getString("MESSAGE"));
            //���� ������� ��������� ��� ���������, �� ������������ ����� executeUpdate()

        } catch (Exception ex) {
            //������� �������� �������� ���������
            Logger.getLogger(JDBCTestHabr.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCTestHabr.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
