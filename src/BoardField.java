import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardField extends Region {
    private Color color;

    public BoardField(Color color) {
        this.color = color;
        BackgroundFill fill = new BackgroundFill(color, null, null);
        Background background = new Background(fill);
        setBackground(background);
    }

    public static StackPane getFieldPane(int col, int row) {
        StackPane stackPane = new StackPane();

        BoardField fieldOrange = new BoardField(Color.ORANGE);
        BoardField fieldBlack = new BoardField(Color.BLACK);

        Pawn pawn = new Pawn(fieldBlack, Color.DARKGREEN);
        pawn.setOnMouseClicked(mouseEvent -> {
            stackPane.getChildren().remove(pawn);
        });

        fieldBlack.setOnMouseClicked(mouseEvent -> {
            if (!stackPane.getChildren().contains(pawn)){
                stackPane.getChildren().add(pawn);
            }
        });

        if ((col + row) % 2 == 0) {
            stackPane.getChildren().add(fieldOrange);
        } else {
            stackPane.getChildren().add(fieldBlack);
            if (row == 0) {
                stackPane.getChildren().add(pawn);
            }
        }

        return stackPane;
    }
}
