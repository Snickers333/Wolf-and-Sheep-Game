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

        BoardField field;
        if ((col + row) % 2 == 0) {
            field = new BoardField(Color.ORANGE);
            stackPane.getChildren().addAll(field);
        } else {
            field = new BoardField(Color.BLACK);
            stackPane.getChildren().addAll(field);
            if (row == 0) {
                Circle pawn = new Circle();
                pawn.setFill(Color.DARKGREEN);
                pawn.radiusProperty().bind(
                        Bindings.when(field.heightProperty().lessThan(field.widthProperty())).then(field.heightProperty().subtract(10).divide(2)).otherwise(field.widthProperty().subtract(10).divide(2))
                );
                stackPane.getChildren().addAll(pawn);
            }
        }
        return stackPane;
    }
}
