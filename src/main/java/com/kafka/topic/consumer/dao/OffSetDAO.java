package com.kafka.topic.consumer.dao;

import com.kafka.topic.consumer.model.ConsumerDTO;

import java.sql.*;

public class OffSetDAO {

    private static final String POPULATE_DEFAULT_OFFSET_SQL = "insert into partition_offsets (offset, topic, partition) values(1, ? , ?)";
    private static final String GET_OFFSET_SQL = "select offset from partition_offsets where topic_name=? and partition=?";
    private static final String UPDATE_PARTITION_OFFSET_SQL = "update partition_offsets set offset=? where topic_name=? and partition=?";
    private static final String POPULATE_REQUEST_SQL = "insert into transaction_data (transaction_id , transaction_data) values(?,?)";

    public long getOffsetFromDB(String topic, Integer partition) throws Exception{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        long offset = 0;
        try {
            con = getConnection();
            stmt = con.prepareStatement(GET_OFFSET_SQL);
            stmt.setString(1 , topic);
            stmt.setInt(2 , partition);
            rs = stmt.executeQuery();
            if (rs.next()) {
                offset = rs.getInt("offset");
            }else{
                populateDefaultOffset(topic , partition);
            }
        } catch (Exception e) {
            System.out.println("Exception in getOffsetFromDB");
            throw e;
        } finally {
            closeCursor(rs);
            closeStatement(stmt);
            closeConnection(con);
        }
        return offset;
    }

    /**
     * System will populate request details and update partition offset.
     * @param consumerDTO The {@link ConsumerDTO} instance.
     * @throws Exception
     */
    public void populateRequest(ConsumerDTO consumerDTO) throws Exception{

        Connection con = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);

            psInsert = con.prepareStatement(POPULATE_REQUEST_SQL);
            psInsert.setString(1, consumerDTO.getRecordKey());
            psInsert.setString(2, consumerDTO.getRecordvalue());

            psUpdate = con.prepareStatement(UPDATE_PARTITION_OFFSET_SQL);
            psUpdate.setLong(1, consumerDTO.getOffset());
            psUpdate.setString(2, consumerDTO.getTopic());
            psUpdate.setInt(3, consumerDTO.getPartition());

            psInsert.executeUpdate();
            psUpdate.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            System.out.println("Exception in saveAndCommit");
            System.out.println("Exception in getOffsetFromDB");
            throw e;
        }finally{
            closeStatement(psInsert);
            closeStatement(psUpdate);
            closeConnection(con);
        }
    }

    public void populateDefaultOffset(String topic , Integer partition) throws Exception{

        Connection con = null;
        PreparedStatement psInsert = null;
        try {
            con = getConnection();
            psInsert = con.prepareStatement(POPULATE_DEFAULT_OFFSET_SQL);
            psInsert.setString(2, topic);
            psInsert.setInt(3, partition);

            psInsert.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception in saveAndCommit");
            throw e;
        }finally{
            closeStatement(psInsert);
            closeConnection(con);
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "system", "mysql");
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        //return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
    }
    private void closeCursor(ResultSet rs) throws SQLException {
        if (null != rs) {
            rs.close();
        }
    }

    private void closeStatement(Statement statement) throws SQLException {
        if (null != statement) {
            statement.close();
        }
    }
    private void closeConnection(Connection conn) throws SQLException {
        if (null != conn) {
            conn.close();
        }
    }
}