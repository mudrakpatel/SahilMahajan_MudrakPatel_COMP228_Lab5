package exercise1;
/**
 * Import statements
 * */
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mudrak and Sahil on 11/27/2016.
 */
public class Stage1 extends Application {
    /**
     * Required variables
     * */
    private static Label firstNameLabel, lastNameLabel, playerIDLabel, addressLabel, postalcodeLabel, provinceLabel, phonelabel, gameLabel;
    private static TextField firstNameTextField, playerIDTextField, lastNameTextField, addressTextField, postalcodetextField, provinceTextField, phonetextField, gametextField;
    private static Button editButton, displayButton;
    private static GridPane gridPaneLayout;
    //Database variables
    private static final String URL = "jdbc:sqlserver://sahil.database.windows.net:1433;database=game_tracker";
    private static final String username = "sahil";
    private static final String password = "Azure754";

    /**
     * main method
     * */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * start method
     * */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Player Information Window");
        /**
         * Initializing the components
         * */
        firstNameLabel = new Label("First name:");
        lastNameLabel = new Label("Last name:");
        playerIDLabel = new Label("Player ID:");
        addressLabel = new Label("Address:");
        postalcodeLabel = new Label("Postal code:");
        provinceLabel = new Label("Province:");
        phonelabel = new Label("Phone:");
        gameLabel = new Label("Game name:");
        firstNameTextField = new TextField();
        lastNameTextField = new TextField();
        playerIDTextField = new TextField();
        addressTextField = new TextField();
        postalcodetextField = new TextField();
        provinceTextField = new TextField();
        phonetextField = new TextField();
        gametextField = new TextField();
        editButton = new Button("Edit");
        displayButton = new Button("Display");
        gridPaneLayout = new GridPane();
        /**
         * Add the components to the gridPaneLayout
         * */
        gridPaneLayout.add(firstNameLabel,0,0);
        gridPaneLayout.setHalignment(firstNameLabel, HPos.LEFT);
        gridPaneLayout.add(firstNameTextField,1,0);
        gridPaneLayout.add(lastNameLabel,0,1);
        gridPaneLayout.setHalignment(lastNameLabel, HPos.LEFT);
        gridPaneLayout.add(lastNameTextField,1,1);
        gridPaneLayout.add(playerIDLabel,0,2);
        gridPaneLayout.setHalignment(playerIDLabel, HPos.LEFT);
        gridPaneLayout.add(playerIDTextField,1,2);
        gridPaneLayout.add(addressLabel,0,3);
        gridPaneLayout.setHalignment(addressLabel, HPos.LEFT);
        gridPaneLayout.add(addressTextField,1,3);
        gridPaneLayout.add(postalcodeLabel,0,4);
        gridPaneLayout.setHalignment(postalcodeLabel, HPos.LEFT);
        gridPaneLayout.add(postalcodetextField,1,4);
        gridPaneLayout.add(provinceLabel,0,5);
        gridPaneLayout.setHalignment(provinceLabel, HPos.LEFT);
        gridPaneLayout.add(provinceTextField,1,5);
        gridPaneLayout.add(phonelabel,0,6);
        gridPaneLayout.setHalignment(phonelabel, HPos.LEFT);
        gridPaneLayout.add(phonetextField,1,6);
        gridPaneLayout.add(gameLabel,0,7);
        gridPaneLayout.setHalignment(gameLabel, HPos.LEFT);
        gridPaneLayout.add(gametextField,1,7);
        gridPaneLayout.add(editButton,0,8);
        gridPaneLayout.setHalignment(editButton, HPos.CENTER);
        gridPaneLayout.add(displayButton,1,8);
        gridPaneLayout.setHalignment(displayButton, HPos.CENTER);
        gridPaneLayout.setAlignment(Pos.CENTER);
        gridPaneLayout.setPadding(new Insets(11.5, 12.5, 10.5, 5.5));
        gridPaneLayout.setHgap(6.0);
        gridPaneLayout.setVgap(6.0);
        /**
         * Add event listener to editButton and displayButton
         * */
        editButton.setOnAction(event -> {
            JOptionPane.showMessageDialog(null,"You clicked edit button.");
        });
        displayButton.setOnAction(event -> {

        });
        /**
         * Setting up the scene
         * */
        primaryStage.setScene(new Scene(gridPaneLayout,400,350));
        primaryStage.show();

        /**
         * The database connection part
         * */
        try{
            //1) Get a connection to the mysql database
            Connection connection = DriverManager.getConnection(URL, username, password);
            //2) Create a sql statement
            Statement sqlStatement = connection.createStatement();
            //3) Execute sql query
            ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM GameJava");
            //4) Process the result set
            showData(resultSet);
        } catch(Exception exception){
//                JOptionPane.showMessageDialog(null,exception.getStackTrace().toString());
            System.out.println(exception.getStackTrace());
        }
    }
    /**
     * @method showData
     * @void
     * @purpose: This method shows all the default data from the database
     * */
    private void showData(ResultSet resultSet) throws SQLException{
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }
}
