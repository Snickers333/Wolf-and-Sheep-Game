import javafx.application.Application;
import javafx.scene.Scene;
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

        makeRulesActive(stackPanes, pawns);

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

    private static void makeRulesActive (StackPane[][] stackPanes, List<Pawn> pawns) {
        Pawn wolf = null;
        for (Pawn pawn : pawns) {
            if (!pawn.isSheep()) {
                wolf = pawn;
            }
        }
        pawns.remove(wolf);

        Pawn finalWolf = wolf;
        stackPanes[wolf.getColumn()-1][wolf.getRow()-1].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[finalWolf.getColumn()-1][finalWolf.getRow()-1].getChildren().get(0);
            field.lighten();
        });

        stackPanes[wolf.getColumn()-1][wolf.getRow()-1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[finalWolf.getColumn()-1][finalWolf.getRow()-1].getChildren().get(0);
            field.darken();
        });

        stackPanes[wolf.getColumn()+1][wolf.getRow()-1].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[finalWolf.getColumn()+1][finalWolf.getRow()-1].getChildren().get(0);
            field.lighten();
        });

        stackPanes[wolf.getColumn()+1][wolf.getRow()-1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[finalWolf.getColumn()+1][finalWolf.getRow()-1].getChildren().get(0);
            field.darken();
        });

        stackPanes[wolf.getColumn()+1][wolf.getRow()+1].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[finalWolf.getColumn()+1][finalWolf.getRow()+1].getChildren().get(0);
            field.lighten();
        });

        stackPanes[wolf.getColumn()+1][wolf.getRow()+1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[finalWolf.getColumn()+1][finalWolf.getRow()+1].getChildren().get(0);
            field.darken();
        });

        stackPanes[wolf.getColumn()-1][wolf.getRow()+1].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[finalWolf.getColumn()-1][finalWolf.getRow()+1].getChildren().get(0);
            field.lighten();
        });

        stackPanes[wolf.getColumn()-1][wolf.getRow()+1].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[finalWolf.getColumn()-1][finalWolf.getRow()+1].getChildren().get(0);
            field.darken();
        });
    }
}
