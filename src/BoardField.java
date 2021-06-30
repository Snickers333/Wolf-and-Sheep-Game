import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;

public class BoardField extends Region {

    public BoardField(Color color) {
        setColor(color);
    }

    private void setColor(Color color) {
        BackgroundFill fill = new BackgroundFill(color, null, null);
        Background background = new Background(fill);
        setBackground(background);
    }


    public static void lighten(int i, int i2, StackPane[][] stackPanes) {
        stackPanes[i][i2].setOnMouseEntered(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[i][i2].getChildren().get(0);
            field.setColor(Color.GREENYELLOW);
        });
    }

    public static void darken(StackPane[][] stackPanes, int i, int i2) {
        stackPanes[i][i2].setOnMouseExited(mouseEvent -> {
            BoardField field = (BoardField) stackPanes[i][i2].getChildren().get(0);
            field.setColor(Color.BLACK);
        });
    }


    public static StackPane getFieldPane(int col, int row, List<Pawn> pawns) {
        StackPane stackPane = new StackPane();

        BoardField fieldOrange = new BoardField(Color.ORANGE);
        BoardField fieldBlack = new BoardField(Color.BLACK);
        Pawn sheep;

        if ((col + row) % 2 == 0) {
            stackPane.getChildren().add(fieldOrange);
        } else {
            stackPane.getChildren().add(fieldBlack);
            if (row == 0) {
                sheep = new Pawn(fieldBlack, Color.DARKGREEN, col, row);
                pawns.add(sheep);
                stackPane.getChildren().add(sheep);
            }
        }
        return stackPane;
    }
}
