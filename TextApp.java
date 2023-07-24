import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.HPos;

public class TextApp extends Application {
    private RadioButton redRadioButton;
    private RadioButton greenRadioButton;
    private RadioButton blueRadioButton;
    private RadioButton yellowRadioButton;
    private RadioButton blackRadioButton;
    private RadioButton orangeRadioButton;
    private TextField textBox;
    private Label textLabel;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    private int spacing = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Colorful and Moving Text App");

        // Radio buttons to choose text color
        redRadioButton = new RadioButton("Red");
        greenRadioButton = new RadioButton("Green");
        blueRadioButton = new RadioButton("Blue");
        yellowRadioButton = new RadioButton("Yellow");
        blackRadioButton = new RadioButton("Black");
        orangeRadioButton = new RadioButton("Orange");

        ToggleGroup colorToggleGroup = new ToggleGroup();
        redRadioButton.setToggleGroup(colorToggleGroup);
        greenRadioButton.setToggleGroup(colorToggleGroup);
        blueRadioButton.setToggleGroup(colorToggleGroup);
        yellowRadioButton.setToggleGroup(colorToggleGroup);
        blackRadioButton.setToggleGroup(colorToggleGroup);
        orangeRadioButton.setToggleGroup(colorToggleGroup);

        // Buttons to move the text
        Button leftButton = new Button("<=");
        leftButton.setOnMousePressed(e -> deleteSpace());
//        leftButton.setOnMouseReleased(e -> stopMove());

        Button rightButton = new Button("=>");
        rightButton.setOnMousePressed(e -> addSpace());
//        rightButton.setOnMouseReleased(e -> stopMove());

        // Layout setup
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Row constraints to adjust row height
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        row1.setPercentHeight(10); // 10% height
        row2.setPercentHeight(10); // 10% height
        row3.setPercentHeight(70); // 70% height
        row4.setPercentHeight(10); // 10% height
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4);

        // Add radio buttons to the grid pane
        gridPane.add(redRadioButton, 0, 0);
        gridPane.add(greenRadioButton, 1, 0);
        gridPane.add(blueRadioButton, 2, 0);
        gridPane.add(yellowRadioButton, 0, 1);
        gridPane.add(blackRadioButton, 1, 1);
        gridPane.add(orangeRadioButton, 2, 1);

        // Create a TextField to mimic the TextBox
        textBox = new TextField(); // Inisialisasi variabel textBox
        textBox.setText("Programming is fun");
        textBox.setEditable(false);
        textBox.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-border-color: black; -fx-background-color: white;");

        gridPane.add(textBox, 0, 2, 3, 1); // Menggunakan ColumnSpan 3 agar textBox mengisi tiga kolom
        GridPane.setHalignment(textBox, HPos.CENTER); // Set agar textBox berada di tengah kolom secara horizontal

        gridPane.add(leftButton, 1, 3); // Menggunakan kolom pertama
        gridPane.add(rightButton, 2, 3); // Menggunakan kolom ketiga

        Scene scene = new Scene(gridPane, 350, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set listener to update text color when radio buttons are selected
        colorToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            updateText();
        });
    }

    private void addSpace() {
        // Tambahkan spasi di depan teks saat tombol left ditekan
        if (spacing < 9) {
            spacing++;
            updateText();
        } else {
            showNotification("Sudah sampai ujung, text tidak bisa digeser lagi!");
        }
    }

    private void deleteSpace() {
        // Hapus spasi di depan teks saat tombol right ditekan (jika ada)
        if (spacing > 0) {
            spacing--;
            updateText();
        } else if (spacing == 0){
            showNotification("Sudah sampai ujung, text tidak bisa digeser lagi!");
        }
    }

    private void showNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateText() {
        // Atur teks dengan jumlah spasi yang sesuai di depan kata "Sample"
        String originalText = "Programming is fun";
        StringBuilder spacedText = new StringBuilder();
        for (int i = 0; i < spacing; i++) {
            spacedText.append(" ");
        }
        spacedText.append(originalText);
        textBox.setText(spacedText.toString());

        // Update the text color based on the selected radio button
        if (redRadioButton.isSelected()) {
            textBox.setStyle("-fx-text-fill: red; -fx-font-size: 24; -fx-font-weight: bold; -fx-border-color: black; -fx-background-color: white;");
        } else if (greenRadioButton.isSelected()) {
            textBox.setStyle("-fx-text-fill: green; -fx-font-size: 24; -fx-font-weight: bold; -fx-border-color: black; -fx-background-color: white;");
        } else if (blueRadioButton.isSelected()) {
            textBox.setStyle("-fx-text-fill: blue; -fx-font-size: 24; -fx-font-weight: bold; -fx-border-color: black; -fx-background-color: white;");
        } else if (yellowRadioButton.isSelected()) {
            textBox.setStyle("-fx-text-fill: yellow; -fx-font-size: 24; -fx-font-weight: bold; -fx-border-color: black; -fx-background-color: white;");
        } else if (blackRadioButton.isSelected()) {
            textBox.setStyle("-fx-text-fill: black; -fx-font-size: 24; -fx-font-weight: bold; -fx-border-color: black; -fx-background-color: white;");
        } else if (orangeRadioButton.isSelected()) {
            textBox.setStyle("-fx-text-fill: orange; -fx-font-size: 24; -fx-font-weight: bold; -fx-border-color: black; -fx-background-color: white;");
        }
    }
}
