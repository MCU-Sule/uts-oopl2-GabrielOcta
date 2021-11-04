package com.example.squiddemo.dao;

import com.example.squiddemo.entity.Player;
import com.example.squiddemo.util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class PlayerDao implements DaoInterface<Player>{
    @Override
    public ObservableList<Player> showAllData() {
        ObservableList<Player> players = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,nama,umur,keahlian FROM player";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String nama = res.getString("nama");
                int umur = res.getInt("umur");
                String keahlian = res.getString("keahlian");
                Player player = new Player(id,nama,umur,keahlian);
                players.add(player);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return players;
    }

    @Override
    public int addData(Player data) {
        int result = 0;
        try {
            String query = "INSERT INTO player (id,nama,umur,keahlian) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getNama());
            ps.setInt(3,data.getUmur());
            ps.setString(4, data.getKeahlian());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Player data) {
        int result=0;
        try {
            String query = "DELETE FROM player WHERE id = ?";
            Connection conn = JDBCConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,data.getId());
            result = ps.executeUpdate();
            if (result != 0){
                conn.commit();
            }else {
                conn.rollback();
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Player data) {
        int result=0;
        try {
            String query = "UPDATE player set nama = ?,umur = ?, keahlian = ? WHERE id = ?";
            Connection conn = JDBCConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, data.getNama());
            ps.setInt(2,data.getUmur());
            ps.setString(3, data.getKeahlian());
            ps.setInt(4,data.getId());
            result = ps.executeUpdate();
            if (result != 0){
                conn.commit();
            }else {
                conn.rollback();
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
