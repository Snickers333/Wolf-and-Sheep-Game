import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pawn extends Circle {
    private boolean isSheep;

    public Pawn (BoardField field,Color color) {
        this.isSheep = color == Color.DARKGREEN;
        setFill(color);

        radiusProperty().bind(
                Bindings.when(field.heightProperty().lessThan(field.widthProperty())).then(field.heightProperty().subtract(10).divide(2)).otherwise(field.widthProperty().subtract(10).divide(2))
        );
    }
}
