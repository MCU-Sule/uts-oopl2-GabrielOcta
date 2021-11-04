package com.example.squiddemo.controller;

import com.example.squiddemo.dao.PlayerDao;
import com.example.squiddemo.entity.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class StageModalController implements Initializable {
    public TextField txtId;
    public TextField txtNama;
    public TextField txtUmur;
    public TextField txtKeahlian;
    public Button btnOk;
    public Button btnCancel;
    public VBox stage;

    private ObservableList<Player>players;
    private SquidController squidController;

    public void okAction(ActionEvent actionEvent) {
        if (txtId.isDisable()) {
            PlayerDao playerDao = new PlayerDao();
            Player player = new Player();
            player.setId(Integer.parseInt(txtId.getText()));
            player.setNama(txtNama.getText());
            player.setKeahlian(txtKeahlian.getText());
            player.setUmur(Integer.parseInt(txtUmur.getText()));
            int result = playerDao.updateData(player);
            if (result != 0) {
                System.out.println("Update Berhasil");
            }
            players.clear();
            players.addAll(playerDao.showAllData());

        }else {
            Player player = new Player();
            player.setId(Integer.parseInt(txtId.getText()));
            player.setNama(txtNama.getText());
            player.setKeahlian(txtKeahlian.getText());
            player.setUmur(Integer.parseInt(txtUmur.getText()));
            players.add(player);
            PlayerDao playerDao = new PlayerDao();
            playerDao.addData(player);
            players.clear();
            players.addAll(playerDao.showAllData());
        }
    }

    public void cancelAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayerDao playerDao = new PlayerDao();
    }

    public void setSquidController(SquidController squidController) {
        this.squidController = squidController;
    }
}
