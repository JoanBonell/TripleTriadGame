package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import cat.udl.hyperion.appmobils.tripletriad.models.Card;

public class CellViewModel extends BaseObservable {
    private Card card;

    @Bindable
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
        notifyChange();
    }
}
