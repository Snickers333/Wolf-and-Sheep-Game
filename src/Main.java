import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static final int SIZE_OF_BOARD = 8;
    private static List<Pawn> pawns = new ArrayList<>();
    private static List<Pawn> grave = new ArrayList<>();
    private StackPane[][] stackPanes = new StackPane[SIZE_OF_BOARD][SIZE_OF_BOARD];
    private static boolean wolfTurn = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane board = new GridPane();
        Scene scene = new Scene(board, 800, 800);
        // TODO SET BOARD SIZE
        // TODO COLLISION
        // TODO SHOW WIN LOSE MESSAGES
        drawBoard(board);
        initializeWolf();

        stage.setTitle("Wolf and Sheep Game");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeWolf() {
        for (int col = 0; col < stackPanes.length; col++){
            for (int row = 0; row < stackPanes[col].length; row++){
                if ((col % 2 == 0) && row == SIZE_OF_BOARD - 1){
                    BoardField.lighten(col,row,stackPanes);
                    BoardField.darken(stackPanes,col,row);
                    int i = col;
                    int j = row;
                    stackPanes[col][row].setOnMouseClicked(mouseEvent -> {
                        createWolfAndContinue(i, j);
                    });
                }
            }
        }
    }

    private void createWolfAndContinue(int col, int row) {
        BoardField field = (BoardField) stackPanes[col][row].getChildren().get(0);
        Pawn wolf = new Pawn(field, Color.RED, col,row);
        pawns.add(wolf);
        stackPanes[col][row].getChildren().add(wolf);

        for (int i = 0; i < stackPanes.length; i++){
            for (int j = 0; j < stackPanes[i].length; j++){
                if ((i % 2 == 0) && j == SIZE_OF_BOARD - 1){
                    stackPanes[i][j].setOnMouseEntered(mouseEvent -> {
                    });
                    stackPanes[i][j].setOnMouseClicked(mouseEvent -> {
                    });
                }
            }
        }
        makeSurroundingFieldsActive(stackPanes, pawns);
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

    private static void makeSurroundingFieldsActive(StackPane[][] stackPanes, List<Pawn> pawns) {
        if (wolfTurn) {
            checkPawnsPosition(pawns);
            Pawn pawn = Pawn.getWolf(pawns);
            int col = pawn.getColumn();
            int row = pawn.getRow();
            if (!(col == 0)) {
                if (!(row == 0) && isPawnThere(col - 1, row - 1)) {
                    BoardField.lighten(col - 1, row - 1, stackPanes);
                    movePawnOnMouseClick(stackPanes, pawn, pawns, col, row, 1);
                    BoardField.darken(stackPanes, col - 1, row - 1);

                }
                if (!(row == (SIZE_OF_BOARD - 1)) && isPawnThere(col - 1, row + 1)) {
                    BoardField.lighten(col - 1, row + 1, stackPanes);
                    movePawnOnMouseClick(stackPanes, pawn, pawns, col, row, 4);
                    BoardField.darken(stackPanes, col - 1, row + 1);
                }
            }
            if (!(col == (SIZE_OF_BOARD - 1))) {
                if (!(row == 0) && isPawnThere(col + 1, row - 1)) {
                    BoardField.lighten(col + 1, row - 1, stackPanes);
                    movePawnOnMouseClick(stackPanes, pawn, pawns, col, row, 2);
                    BoardField.darken(stackPanes, col + 1, row - 1);
                }
                if (!(row == (SIZE_OF_BOARD - 1)) && isPawnThere(col + 1, row + 1)) {
                    BoardField.lighten(col + 1, row + 1, stackPanes);
                    movePawnOnMouseClick(stackPanes, pawn, pawns, col, row, 3);
                    BoardField.darken(stackPanes, col + 1, row + 1);
                }
            }
            wolfTurn = false;
        } else {
            checkPawnsPosition(Pawn.getWolf(pawns));
            for (Pawn pawn : pawns) {
                if (pawn.isSheep()) {
                    BoardField.lighten(pawn.getColumn(), pawn.getRow(), stackPanes);
                    BoardField.darken(stackPanes, pawn.getColumn(), pawn.getRow());
                    stackPanes[pawn.getColumn()][pawn.getRow()].setOnMouseClicked(mouseEvent -> {
                        MakeFieldsActiveForSheep(stackPanes, pawn, pawns);
                    });
                }
            }
        }
    }

    private static void MakeFieldsActiveForSheep(StackPane[][] stackPanes, Pawn pawn, List<Pawn> pawns) {
        for (Pawn sheep : pawns) {
            if (sheep.isSheep()) {
                stackPanes[sheep.getColumn()][sheep.getRow()].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[sheep.getColumn()][sheep.getRow()].setOnMouseClicked(mouseEvent -> {
                });
                BoardField.darken(stackPanes, sheep.getColumn(), sheep.getRow());
            }
        }
        int col = pawn.getColumn();
        int row = pawn.getRow();
        if (!(col == 0)) {
            if (!(row == (SIZE_OF_BOARD - 1)) && isPawnThere(col - 1, row + 1)) {
                BoardField.lighten(col - 1, row + 1, stackPanes);
                movePawnOnMouseClick(stackPanes, pawn, pawns, col, row, 4);
                BoardField.darken(stackPanes, col - 1, row + 1);
            }
        }

        if (!(col == (SIZE_OF_BOARD - 1))) {
            if (!(row == (SIZE_OF_BOARD - 1)) && isPawnThere(col + 1, row + 1)) {
                BoardField.lighten(col + 1, row + 1, stackPanes);
                movePawnOnMouseClick(stackPanes, pawn, pawns, col, row, 3);
                BoardField.darken(stackPanes, col + 1, row + 1);
            }
        }
        wolfTurn = true;
    }

    private static void removeSurroundingFieldsActive(StackPane[][] stackPanes, Pawn pawn, List<Pawn> pawns, int clicked) {
        int col = pawn.getColumn();
        int row = pawn.getRow();
        if (!(col == 0)) {
            if (!(row == (SIZE_OF_BOARD - 1))) {
                stackPanes[col - 1][row + 1].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[col - 1][row + 1].setOnMouseClicked(mouseEvent -> {
                });
                BoardField.darken(stackPanes, col - 1, row + 1);
            }
            if (!(row == 0)) {
                stackPanes[col - 1][row - 1].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[col - 1][row - 1].setOnMouseClicked(mouseEvent -> {
                });
                BoardField.darken(stackPanes, col - 1, row - 1);
            }
        }

        if (!(col == (SIZE_OF_BOARD - 1))) {
            if (!(row == (SIZE_OF_BOARD - 1))) {
                stackPanes[col + 1][row + 1].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[col + 1][row + 1].setOnMouseClicked(mouseEvent -> {
                });
                BoardField.darken(stackPanes, col + 1, row + 1);
            }
            if (!(row == 0)) {
                stackPanes[col + 1][row - 1].setOnMouseEntered(mouseEvent -> {
                });
                stackPanes[col + 1][row - 1].setOnMouseClicked(mouseEvent -> {
                });
                BoardField.darken(stackPanes, col + 1, row - 1);
            }
        }

        switch (clicked) {
            case 1 -> {
                pawn.setColumn(col - 1);
                pawn.setRow(row - 1);
                makeSurroundingFieldsActive(stackPanes, pawns);
            }
            case 2 -> {
                pawn.setColumn(col + 1);
                pawn.setRow(row - 1);
                makeSurroundingFieldsActive(stackPanes, pawns);
            }
            case 3 -> {
                pawn.setColumn(col + 1);
                pawn.setRow(row + 1);
                makeSurroundingFieldsActive(stackPanes, pawns);
            }
            case 4 -> {
                pawn.setColumn(col - 1);
                pawn.setRow(row + 1);
                makeSurroundingFieldsActive(stackPanes, pawns);
            }
        }
    }

    private static void movePawnOnMouseClick(StackPane[][] stackPanes, Pawn pawn, List<Pawn> pawns, int col, int row, int clicked) {
        switch (clicked) {
            case 1 -> stackPanes[col - 1][row - 1].setOnMouseClicked(mouseEvent -> {
                stackPanes[col][row].getChildren().remove(pawn);
                stackPanes[col - 1][row - 1].getChildren().add(pawn);
                removeSurroundingFieldsActive(stackPanes, pawn, pawns, clicked);
            });
            case 2 -> stackPanes[col + 1][row - 1].setOnMouseClicked(mouseEvent -> {
                stackPanes[col][row].getChildren().remove(pawn);
                stackPanes[col + 1][row - 1].getChildren().add(pawn);
                removeSurroundingFieldsActive(stackPanes, pawn, pawns, clicked);
            });
            case 3 -> stackPanes[col + 1][row + 1].setOnMouseClicked(mouseEvent -> {
                stackPanes[col][row].getChildren().remove(pawn);
                stackPanes[col + 1][row + 1].getChildren().add(pawn);
                removeSurroundingFieldsActive(stackPanes, pawn, pawns, clicked);
            });
            case 4 -> stackPanes[col - 1][row + 1].setOnMouseClicked(mouseEvent -> {
                stackPanes[col][row].getChildren().remove(pawn);
                stackPanes[col - 1][row + 1].getChildren().add(pawn);
                removeSurroundingFieldsActive(stackPanes, pawn, pawns, clicked);
            });
        }
    }

    private static void checkPawnsPosition(Pawn pawn) {
        if (pawn.getRow() == 0) {
            System.out.println("Wilk wygrywa !");
        }
    }

    private static void checkPawnsPosition(List<Pawn> pawns) {
        for (Pawn pawn : pawns){
            if (pawn.isSheep() && pawn.getRow() == SIZE_OF_BOARD - 1){
                grave.add(pawn);
            }
        }
        pawns.removeAll(grave);
        if (pawns.size() == 1){
            System.out.println("Wilkor WINS");
        }
    }

    private static boolean isPawnThere (int col, int row) {
        for (Pawn pawn : pawns) {
            if (pawn.getColumn() == col && pawn.getRow() == row)
                return false;
        }
        for (Pawn pawn : grave) {
            if (pawn.getColumn() == col && pawn.getRow() == row)
                return false;
        }
        return true;
    }
}
