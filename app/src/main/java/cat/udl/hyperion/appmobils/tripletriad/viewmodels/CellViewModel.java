package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import cat.udl.hyperion.appmobils.tripletriad.BR;
import cat.udl.hyperion.appmobils.tripletriad.R;
import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.models.Cell;

public class CellViewModel extends BaseObservable {
    private static final String TAG = "CellViewModel";
    private Card card;
    private Cell cell;

    public CellViewModel(Cell cell) {
        this.cell = cell;
    }

    @Bindable
    public Card getCard() {
        return card;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCard(Card card) {
        Log.d(TAG, "La carta es " + card.getName() + " su image es "+card.getImageResource());
        this.card = card;
        notifyPropertyChanged(BR.card);
    }


    @Bindable
    public int getImageResource() {
        if (card != null) {
            return card.getImageResource();
        } else {
            return R.drawable.card_placeholder;
        }
    }
}
