package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        launch(args);
    }

    public Scene mainScene;
    public Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        stage.setTitle("Test App");

        TabPane tabs = new TabPane();

        Tab mainGrid = new Tab("Main");
        Tab secondTab = new Tab("SubMain");
        Tab thirdTab = new Tab("Canvas");
        mainGrid.setClosable(false);
        secondTab.setClosable(false);
        thirdTab.setClosable(false);
        tabs.getTabs().add(mainGrid);
        tabs.getTabs().add(secondTab);
        tabs.getTabs().add(thirdTab);

        Canvas canvas = new Canvas(300, 300);
        thirdTab.setContent(canvas);
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLUE);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu newMenu = new Menu("New");
        MenuItem item = new MenuItem("Item");
        MenuItem item2 = new MenuItem("Item2!");
        item2.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        newMenu.getItems().add(item);
        fileMenu.getItems().add(newMenu);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(item2);

        menuBar.getMenus().add(fileMenu);
        secondTab.setContent(menuBar);

        SingleSelectionModel<Tab> selection = tabs.getSelectionModel();
        tabs.setSelectionModel(selection);

        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(10);
        root.setHgap(10);

        mainGrid.setContent(root);

        Text welcome = new Text("Welcome!");
        welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        Text userName = new Text("Username: ");
        Text passWord = new Text("Password: ");

        Button enter = new Button("Enter");
        enter.setId("Enter");
        enter.setOnAction(this);
        Button cancel = new Button("Cancel");
        cancel.setId("Cancel");

        TextField userField = new TextField();
        userField.setId("userField");
        userField.setPromptText("Put username dummy");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("**********");

        ChoiceBox<String> choices = new ChoiceBox<String>();
        choices.setItems(FXCollections.observableArrayList("Apples", "Oranges", "Bananas"));

        ToggleButton toggleButton = new ToggleButton("Toggle?");
        toggleButton.setId("toggleButton");

        TreeItem<String> treeItem = new TreeItem<String>();
        treeItem.setValue("Root");
        treeItem.getChildren().add(new TreeItem<String>("Blah"));
        treeView.setRoot(treeItem);
        treeItem.setExpanded(true);

        root.add(userName, 0, 1);
        root.add(passWord, 0, 2);
        root.add(userField, 1, 1);
        root.add(passwordField, 1, 2);
        root.add(enter, 0, 3);
        root.add(cancel, 1, 3);
        root.add(welcome, 0, 0);
        root.add(choices, 0, 4);
        root.add(toggleButton, 1, 4);
        root.add(treeView, 0, 5);

        mainScene = new Scene(tabs, 500, 500);
        mainScene.getStylesheets().add(Main.class.getResource("Enter.css").toExternalForm());
        stage.setScene(mainScene);
        stage.show();
    }

    TreeView<String> treeView = new TreeView<String>();

    @Override
    public void handle(ActionEvent actionEvent) {
        TreeItem<String> temp = new TreeItem<String>("Boop");
        treeView.getRoot().getChildren().add(temp);
    }
}