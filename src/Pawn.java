import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class Pawn extends Circle {
    private boolean isSheep;
    private int row;
    private int column;

    public Pawn(BoardField field, Color color, int column, int row) {
        this.row = row;
        this.column = column;
        this.isSheep = color == Color.DARKGREEN;
        setFill(color);

        radiusProperty().bind(
                Bindings.when(field.heightProperty().lessThan(field.widthProperty())).then(field.heightProperty().subtract(10).divide(2)).otherwise(field.widthProperty().subtract(10).divide(2))
        );
    }

    public static Pawn getWolf (List<Pawn> pawns) {
        Pawn wolf = null;
        for (Pawn pawn : pawns) {
            if (!pawn.isSheep()) {
                wolf = pawn;
            }
        }
        pawns.remove(wolf);
        return wolf;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isSheep() {
        return isSheep;
    }

    public void setSheep(boolean sheep) {
        isSheep = sheep;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "isSheep=" + isSheep +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
