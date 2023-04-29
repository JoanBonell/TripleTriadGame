package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.models.Deck;
import cat.udl.hyperion.appmobils.tripletriad.models.Player;

public class DeckViewModel extends ViewModel {
    private MutableLiveData<Deck> deck;
    private Player player;
    private MutableLiveData<Card> cardSelected;

    public DeckViewModel() {
        deck = new MutableLiveData<>();
        deck.setValue(new Deck());
        cardSelected = new MutableLiveData<>();
    }

    public LiveData<Deck> getDeck() {
        return deck;
    }

    public void addCardToDeck(Card card) {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null) {
            currentDeck.agregarCarta(card);
            deck.postValue(currentDeck);
        }
    }

    public void removeCardFromDeck(Card card) {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null) {
            currentDeck.eliminarCarta(card);
            deck.postValue(currentDeck);
        }
    }

    public void shuffleDeck() {
        Deck currentDeck = deck.getValue();
        if (currentDeck != null) {
            currentDeck.barajar();
            deck.postValue(currentDeck);
        }
    }

    public void setSelectedCard(Card card) {
        cardSelected.setValue(card);
    }

    public LiveData<Card> getSelectedCard(){
        return cardSelected;
    }
}
