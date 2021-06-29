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
        makeRulesActive(stackPanes, pawns, wolf);


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

    private static void makeRulesActive (StackPane[][] stackPanes, List<Pawn> pawns, Pawn wolf) {
        makeSurroundingFieldsActive(stackPanes,wolf,wolf.getColumn(),wolf.getRow());
    }

    private static void makeSurroundingFieldsActive(StackPane[][] stackPanes, Pawn wolf, int col, int row) {
        stackPanes[col-1][row-1].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col-1][row-1].getChildren().get(0);
            field.lighten();
        });

        stackPanes[col-1][row-1].setOnMouseClicked(mouseEvent -> {
            stackPanes[col][row].getChildren().remove(wolf);
            stackPanes[col-1][row-1].getChildren().add(wolf);
            removeSurroundingFieldsActive(stackPanes, wolf, col, row, 1);
        });

        stackPanes[col-1][row-1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col-1][row-1].getChildren().get(0);
            field.darken();
        });

        stackPanes[col+1][row-1].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col+1][row-1].getChildren().get(0);
            field.lighten();
        });

        stackPanes[col+1][row-1].setOnMouseClicked(mouseEvent -> {
            stackPanes[col][row].getChildren().remove(wolf);
            stackPanes[col+1][row-1].getChildren().add(wolf);
            removeSurroundingFieldsActive(stackPanes, wolf, col, row, 2);
        });

        stackPanes[col+1][row-1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col+1][row-1].getChildren().get(0);
            field.darken();
        });

        stackPanes[col+1][row+1].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col+1][row+1].getChildren().get(0);
            field.lighten();
        });

        stackPanes[col+1][row+1].setOnMouseClicked(mouseEvent -> {
            stackPanes[col][row].getChildren().remove(wolf);
            stackPanes[col+1][row+1].getChildren().add(wolf);
            removeSurroundingFieldsActive(stackPanes, wolf, col, row, 3);
        });

        stackPanes[col+1][row+1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col+1][row+1].getChildren().get(0);
            field.darken();
        });

        stackPanes[col-1][row+1].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col-1][row+1].getChildren().get(0);
            field.lighten();
        });

        stackPanes[col-1][row+1].setOnMouseClicked(mouseEvent -> {
            stackPanes[col][row].getChildren().remove(wolf);
            stackPanes[col-1][row+1].getChildren().add(wolf);
            removeSurroundingFieldsActive(stackPanes, wolf, col, row, 4);
        });

        stackPanes[col-1][row+1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col-1][row+1].getChildren().get(0);
            field.darken();
        });
    }

    private static void removeSurroundingFieldsActive(StackPane[][] stackPanes, Pawn wolf, int col, int row, int clicked) {
        stackPanes[col-1][row+1].setOnMouseEntered(mouseEvent -> {
        });
        stackPanes[col-1][row+1].setOnMouseClicked(mouseEvent -> {
        });
        stackPanes[col-1][row+1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col-1][row+1].getChildren().get(0);
            field.darken();
        });
        stackPanes[col+1][row+1].setOnMouseEntered(mouseEvent -> {
        });
        stackPanes[col+1][row+1].setOnMouseClicked(mouseEvent -> {
        });
        stackPanes[col+1][row+1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col+1][row+1].getChildren().get(0);
            field.darken();
        });
        stackPanes[col-1][row-1].setOnMouseEntered(mouseEvent -> {
        });
        stackPanes[col-1][row-1].setOnMouseClicked(mouseEvent -> {
        });
        stackPanes[col-1][row-1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col-1][row-1].getChildren().get(0);
            field.darken();
        });
        stackPanes[col+1][row-1].setOnMouseEntered(mouseEvent -> {
        });
        stackPanes[col+1][row-1].setOnMouseClicked(mouseEvent -> {
        });
        stackPanes[col+1][row-1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[col+1][row-1].getChildren().get(0);
            field.darken();
        });
        switch (clicked){
            case 1 ->{
                wolf.setColumn(col-1);
                wolf.setRow(row-1);
                makeSurroundingFieldsActive(stackPanes, wolf, col-1, row-1);
            }
            case 2 ->{
                wolf.setColumn(col+1);
                wolf.setRow(row-1);
                makeSurroundingFieldsActive(stackPanes, wolf, col+1, row-1);
            }
            case 3 ->{
                wolf.setColumn(col+1);
                wolf.setRow(row+1);
                makeSurroundingFieldsActive(stackPanes, wolf, col+1, row+1);
            }
            case 4 ->{
                wolf.setColumn(col-1);
                wolf.setRow(row+1);
                makeSurroundingFieldsActive(stackPanes, wolf, col-1, row+1);
            }
        }
    }
}
