package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.models.Cell;

public class CellViewModel extends BaseObservable {
    private Cell cell;

    public CellViewModel(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    @Bindable
    public Card getCard() {
        return cell.getCard();
    }

    public void setCard(Card card) {
        cell.setCard(card);
        notifyChange();
    }

    @Bindable
    public Integer getImageResource() {
        Card card = cell.getCard();
        return card !=  null ? card.getImageResource() : null;
    }
}
