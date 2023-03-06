package com.example.gestioncartes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController implements Initializable {
    /*@FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/
    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;

    private Label label;
    @FXML
    private TextField id;
    @FXML
    private TextField recto;
    @FXML
    private TextField verso;

    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
    @FXML
    private TableView<Carte> table;
    @FXML
    private TableColumn<Carte, Integer> colid;
    @FXML
    private TableColumn<Carte, String> colrecto;
    @FXML
    private TableColumn<Carte, String> colverso;

/*
    /**
     * @param url
     * @param resourceBundle
     *
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }*/
    @Override
    public void initialize (URL url, ResourceBundle rb) /*(URL url, ResourceBundle rb)*/ {

        // TODO
        affiche();
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        Carte et = table.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(et.getId()));
        recto.setText(et.getRecto());
        verso.setText(et.getVerso());
        bsave.setDisable(true);
    }

    public ObservableList<Carte> getCarte() {
        ObservableList<Carte> list = FXCollections.observableArrayList();

        String select = "SELECT * from carte";
        con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                Carte et = new Carte();
                et.setId(rs.getInt("id"));
                et.setRecto(rs.getString("recto"));
                et.setVerso(rs.getString("verso"));
                list.add(et);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HelloController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }


        return list;

    }

    public void affiche() {
        ObservableList<Carte> list = getCarte();
        colid.setCellValueFactory(new PropertyValueFactory<Carte, Integer>("id"));
        colrecto.setCellValueFactory(new PropertyValueFactory<Carte, String>("recto"));
        colverso.setCellValueFactory(new PropertyValueFactory<Carte, String>("verso"));

        table.setItems(list);

    }

    private void insert() {
        con = Connexion.getCon();
        String insert = "INSERT INTO carte (recto, verso)VALUES(?,?)";
        try {
            st = con.prepareStatement(insert);
            st.setString(1, recto.getText());
            st.setString(2, verso.getText());
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(HelloController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public void delete() {
        con = Connexion.getCon();
        String delete = "DELETE FROM carte  where id = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(id.getText()));
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(HelloController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private void update() {
        con = Connexion.getCon();
        String update
                = "UPDATE carte SET recto =?,verso = ? where id =?";
        try {
            st = con.prepareStatement(update);
            st.setString(1, recto.getText());
            st.setString(2, verso.getText());
            st.setInt(4, Integer.parseInt(id.getText()));
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(HelloController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    void clear() {
        id.setText(null);
        recto.setText(null);
        verso.setText(null);
        bsave.setDisable(false);
    }

    @FXML
    private void saveEvent(ActionEvent event) {
        insert();
        clear();
    }

    @FXML
    private void updateEvent(ActionEvent event) {
        update();
        clear();
        bsave.setDisable(false);
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();
        clear();
    }

    @FXML
    private void clearEvent(ActionEvent event) {
        clear();
    }


}

