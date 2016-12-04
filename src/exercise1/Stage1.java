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
    private static final String URL = "jdbc:sqlserver://sahil.database.windows.net:1433;"
            + "databaseName=game_tracker;"
            + "user=sahil;"
            + "password=Azure754";

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
            System.out.println("Connecting to database...");
            //1) Get a connection to the database
            Connection connection = DriverManager.getConnection(URL);
            System.out.println("Connected to database...");
            //2) Create a statement using the connection object
            Statement statement = connection.createStatement();
            System.out.println("Statement created...");
            //3) Execute a query using the statement object by creating a result set
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GameJava");
            System.out.println("Query successfully executed...");
            //4) Print out the result by calling the getResultSetData method
            // and pass the resultSet as an argument
            System.out.printf("<<The data of GameJava table>>\n"
                    + getResultSetData(resultSet));
        }
        catch(SQLException exception){
            JOptionPane.showMessageDialog(null,"Look at console for error details...");
            System.out.println(exception.getStackTrace().getClass());
        }
    }
    /**
     * @method getResultSetData
     * @return => String
     * @purpose => To get the resultSet data
     * */
    private static String getResultSetData(ResultSet resultSet) throws SQLException{
        String resultString = "";
        while(resultSet.next()){
            resultString = resultString + "<<" +resultSet.getString("game_id") + ">> " +
                    "<<" + resultSet.getString("game_title") + ">>" + "\n";
        }
        return resultString;
    }
}
