package com.example.squiddemo.dao;

import com.example.squiddemo.entity.Hutang;
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
public class HutangDao implements DaoInterface<Hutang>{
    @Override
    public ObservableList<Hutang> showAllData() {
        ObservableList<Hutang> hutangs = FXCollections.observableArrayList();
        try {
            String query = "SELECT h.id,h.pemberiutang,h.jumlah,p.id as player_id FROM hutang h JOIN player p ON h.player_id = p.id";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                Player player = new Player();
                player.setId(res.getInt("player_id"));
                Hutang hutang = new Hutang();
                hutang.setId(res.getInt("id"));
                hutang.setPemeberiUtang(res.getString("pemberiutang"));
                hutang.setJumlah(res.getInt("jumlah"));
                hutang.setPlayer(player);
                hutangs.add(hutang);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return hutangs;
    }

    @Override
    public int addData(Hutang data) {
        int result = 0;
        try {
            String query = "INSERT INTO hutang (id,pemberiutang,jumlah,player_id) VALUES (?,?,?,?) WHERE player_id = ?";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getPemeberiUtang());
            ps.setDouble(3,data.getJumlah());
            ps.setInt(4, data.getPlayer().getId());
            ps.setInt(5, data.getPlayer().getId());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Hutang data) {
        int result = 0;
        try {
            String query = "DELETE FROM hutang WHERE id = ? AND player_id = ?";
            Connection conn = JDBCConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setInt(2,data.getPlayer().getId());
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
    public int updateData(Hutang data) {
        return 0;
    }
}
