package com.example.squiddemo.controller;

import com.example.squiddemo.SquidApplication;
import com.example.squiddemo.dao.HutangDao;
import com.example.squiddemo.dao.PlayerDao;
import com.example.squiddemo.entity.Hutang;
import com.example.squiddemo.entity.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class SquidController implements Initializable {

    public TableView<Player> tablePemain;
    public ComboBox<Player> cmbPeserta;
    public TextField txtPemberiUtang;
    public TextField txtJumlah;
    public TableView<Hutang> tableHutang;
    public TableColumn<Player, String> columnId;
    public TableColumn<Player, String> columnNama;
    public TableColumn<Player, String > columnUmur;
    public TableColumn<Player, String> columnKeahlian;
    public Button addPemain;
    public Button editPemain;
    public Button hapusPemain;
    public Button tambahHutang;
    public TableColumn<Hutang, String> columnId2;
    public TableColumn<Hutang,String> columnHutang;
    public TableColumn<Hutang, String> columnJumlah;
    public Button hapusHutang;

    private ObservableList<Player>players;
    private ObservableList<Hutang>hutangs;
    private Player selectedPlayer;
    private Hutang selectedHutang;

    public void addActionPemain(ActionEvent actionEvent) throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(SquidApplication.class.getResource("ModalPage.fxml"));
        Parent root = loader.load();
        StageModalController controller2 = loader.getController();
        controller2.setSquidController(this);
        Scene new_scene = new Scene(root);
        new_stage.setScene(new_scene);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.show();
    }

    public void editActionPemain(ActionEvent actionEvent) throws IOException {
        selectedPlayer = tablePemain.getSelectionModel().getSelectedItem();
        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(SquidApplication.class.getResource("ModalPage.fxml"));
        Parent root = loader.load();
        StageModalController controller2 = loader.getController();
        controller2.setSquidController(this);
        Scene new_scene = new Scene(root);
        new_stage.setScene(new_scene);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.show();
        controller2.txtId.setDisable(true);
        controller2.txtId.setText(String.valueOf(selectedPlayer.getId()));
        controller2.txtNama.setText(selectedPlayer.getNama());
        controller2.txtUmur.setText(String.valueOf(selectedPlayer.getUmur()));
        controller2.txtKeahlian.setText(selectedPlayer.getKeahlian());
    }

    public void hapusActionPemain(ActionEvent actionEvent) {
        selectedPlayer = tablePemain.getSelectionModel().getSelectedItem();
        PlayerDao playerDao = new PlayerDao();
        int result = playerDao.delData(selectedPlayer);
        if (result != 0) {
            System.out.println("Delete Berhasil");
        }
        players.clear();
        players.addAll(playerDao.showAllData());
    }

    public void hapusActionHutang(ActionEvent actionEvent) {

    }

    public void hutangActionAdd(ActionEvent actionEvent) {
        Hutang hutang = new Hutang();
        hutang.setId(cmbPeserta.getValue().getId());
        hutang.setPemeberiUtang(txtPemberiUtang.getText());
        hutang.setJumlah(Double.parseDouble(txtJumlah.getText()));
        hutangs.add(hutang);
        HutangDao hutangDao = new HutangDao();
        hutangDao.addData(hutang);
        hutangs.clear();
        hutangs.addAll(hutangDao.showAllData());
        tableHutang.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayerDao playerDao = new PlayerDao();
        HutangDao hutangDao = new HutangDao();
        players = playerDao.showAllData();
        cmbPeserta.setItems(players);
        tablePemain.setItems(players);
        columnId.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        columnNama.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getNama()));
        columnUmur.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getUmur())));
        columnKeahlian.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getKeahlian()));
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        selectedPlayer = tablePemain.getSelectionModel().getSelectedItem();
        HutangDao hutangDao = new HutangDao();
        hutangs = hutangDao.showAllData();
        tableHutang.setItems(hutangs);
        columnId2.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        columnHutang.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getPemeberiUtang()));
        columnJumlah.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getJumlah())));
    }
}
