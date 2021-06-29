import com.sun.javafx.event.EventHandlerManager;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main extends Application {
    public static final int SIZE_OF_BOARD = 8;
    public List<Pawn> pawns = new ArrayList<>();
    public StackPane[][] stackPanes = new StackPane[SIZE_OF_BOARD][SIZE_OF_BOARD];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane board = new GridPane();
        Scene scene = new Scene(board, 800, 800);

        drawBoard(board);

        Pawn wolf = Pawn.getWolf(pawns);

        makeSurroundingFieldsActive(stackPanes,wolf);


        stage.setTitle("Wolf and Sheep Game");
        stage.setScene(scene);
        stage.show();
    }

    private void drawBoard(GridPane board) {
        for (int col = 0; col < SIZE_OF_BOARD; col++) {
            for (int row = 0; row < SIZE_OF_BOARD; row++) {
                StackPane stackPane = BoardField.getFieldPane(col, row, pawns);
                stackPanes[col][row] = stackPane;
                board.add(stackPane, col, row);
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

    private static void makeSurroundingFieldsActive(StackPane[][] stackPanes, Pawn wolf) {
        int col = wolf.getColumn();
        int row = wolf.getRow();
        if (!(col == 0)){
            if (!(row == 0)){
                stackPanes[col-1][row-1].setOnMouseEntered(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col-1][row-1].getChildren().get(0);
                    field.lighten();
                });

                stackPanes[col-1][row-1].setOnMouseClicked(mouseEvent -> {
                    stackPanes[col][row].getChildren().remove(wolf);
                    stackPanes[col-1][row-1].getChildren().add(wolf);
                    removeSurroundingFieldsActive(stackPanes, wolf, 1);
                });

                stackPanes[col-1][row-1].setOnMouseExited(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col-1][row-1].getChildren().get(0);
                    field.darken();
                });
            }
            if (!(row == (SIZE_OF_BOARD - 1))){
                stackPanes[col-1][row+1].setOnMouseEntered(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col-1][row+1].getChildren().get(0);
                    field.lighten();
                });

                stackPanes[col-1][row+1].setOnMouseClicked(mouseEvent -> {
                    stackPanes[col][row].getChildren().remove(wolf);
                    stackPanes[col-1][row+1].getChildren().add(wolf);
                    removeSurroundingFieldsActive(stackPanes, wolf, 4);
                });

                stackPanes[col-1][row+1].setOnMouseExited(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col-1][row+1].getChildren().get(0);
                    field.darken();
                });
            }
        }

        if (!(col == (SIZE_OF_BOARD - 1))) {
            if (!(row == 0)){
                stackPanes[col+1][row-1].setOnMouseEntered(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col+1][row-1].getChildren().get(0);
                    field.lighten();
                });

                stackPanes[col+1][row-1].setOnMouseClicked(mouseEvent -> {
                    stackPanes[col][row].getChildren().remove(wolf);
                    stackPanes[col+1][row-1].getChildren().add(wolf);
                    removeSurroundingFieldsActive(stackPanes, wolf, 2);
                });

                stackPanes[col+1][row-1].setOnMouseExited(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col+1][row-1].getChildren().get(0);
                    field.darken();
                });
            }
            if (!(row == (SIZE_OF_BOARD - 1))){
                stackPanes[col+1][row+1].setOnMouseEntered(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col+1][row+1].getChildren().get(0);
                    field.lighten();
                });

                stackPanes[col+1][row+1].setOnMouseClicked(mouseEvent -> {
                    stackPanes[col][row].getChildren().remove(wolf);
                    stackPanes[col+1][row+1].getChildren().add(wolf);
                    removeSurroundingFieldsActive(stackPanes, wolf, 3);
                });

                stackPanes[col+1][row+1].setOnMouseExited(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col+1][row+1].getChildren().get(0);
                    field.darken();
                });
            }
        }
    }

    private static void removeSurroundingFieldsActive(StackPane[][] stackPanes, Pawn wolf, int clicked) {
        int col = wolf.getColumn();
        int row = wolf.getRow();
        if (!(col == 0)){
            if (!(row == (SIZE_OF_BOARD - 1))){
                stackPanes[col-1][row+1].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[col-1][row+1].setOnMouseClicked(mouseEvent -> {
                });
                stackPanes[col-1][row+1].setOnMouseExited(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col-1][row+1].getChildren().get(0);
                    field.darken();
                });
            }
            if (!(row == 0)){
                stackPanes[col-1][row-1].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[col-1][row-1].setOnMouseClicked(mouseEvent -> {
                });
                stackPanes[col-1][row-1].setOnMouseExited(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col-1][row-1].getChildren().get(0);
                    field.darken();
                });
            }
        }

        if (!(col == (SIZE_OF_BOARD - 1))) {
            if (!(row == (SIZE_OF_BOARD - 1))){
                stackPanes[col+1][row+1].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[col+1][row+1].setOnMouseClicked(mouseEvent -> {
                });
                stackPanes[col+1][row+1].setOnMouseExited(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col+1][row+1].getChildren().get(0);
                    field.darken();
                });
            }
            if (!(row == 0)){
                stackPanes[col+1][row-1].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[col+1][row-1].setOnMouseClicked(mouseEvent -> {
                });
                stackPanes[col+1][row-1].setOnMouseExited(mouseEvent -> {
                    BoardField field = (BoardField) stackPanes[col+1][row-1].getChildren().get(0);
                    field.darken();
                });
            }
        }

        switch (clicked){
            case 1 ->{
                wolf.setColumn(col-1);
                wolf.setRow(row-1);
                makeSurroundingFieldsActive(stackPanes, wolf);
            }
            case 2 ->{
                wolf.setColumn(col+1);
                wolf.setRow(row-1);
                makeSurroundingFieldsActive(stackPanes, wolf);
            }
            case 3 ->{
                wolf.setColumn(col+1);
                wolf.setRow(row+1);
                makeSurroundingFieldsActive(stackPanes, wolf);
            }
            case 4 ->{
                wolf.setColumn(col-1);
                wolf.setRow(row+1);
                makeSurroundingFieldsActive(stackPanes, wolf);
            }
        }
    }
}
