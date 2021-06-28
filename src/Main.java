import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int SIZE_OF_BOARD = 8;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane board = new GridPane();
        Scene scene = new Scene(board, 800, 800);

        drawBoard(board);

        stage.setTitle("Wolf and Sheep Game");
        stage.setScene(scene);
        stage.show();
    }

    private void drawBoard(GridPane board) {
        for (int col = 0; col < SIZE_OF_BOARD; col++) {
            for (int row = 0; row < SIZE_OF_BOARD; row++) {
                board.add(BoardField.getFieldPane(col,row), col, row);
            }
        }

        for (int col = 0; col < SIZE_OF_BOARD; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100 / (double) SIZE_OF_BOARD);
            board.getColumnConstraints().add(columnConstraints);
        }

        for (int row = 0; row < SIZE_OF_BOARD; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100 / (double) SIZE_OF_BOARD);
            board.getRowConstraints().add(rowConstraints);
        }
    }
}
