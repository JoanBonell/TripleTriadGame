package cat.udl.hyperion.appmobils.tripletriad.models;

import androidx.databinding.ObservableField;

import cat.udl.hyperion.appmobils.tripletriad.viewmodels.CellViewModel;

public class Cell extends CellViewModel {
    private ObservableField<Card> card;
    private int row;
    private int col;

    public Cell(int row, int col){
        this.card = new ObservableField<>(null);
        this.row = row;
        this.col = col;
    }

    public Card getCard() {
        return card.get();
    }

    public void setCard(Card card) {
        this.card.set(card);
    }

    public ObservableField<Card> getCardField() {
        return card;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void notifyPropertyChanged(int fieldId) {
        card.notifyChange();
    }
}
