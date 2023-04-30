package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.tripletriad.CardAdapter;
import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.models.Deck;
import cat.udl.hyperion.appmobils.tripletriad.models.Player;

public class DeckViewModel extends ViewModel {
    private MutableLiveData<Deck> deck;
    private Player player;
    private MutableLiveData<Card> cardSelected;

    public DeckViewModel() {
        Log.d("DeckViewModel", "Creando el DeckViewModel...");
        deck = new MutableLiveData<>();
        deck.setValue(new Deck());
        cardSelected = new MutableLiveData<>();
    }

    public LiveData<Deck> getDeck() {
        Log.d("DeckViewModel", "Obteniendo el DeckViewModel...");
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
        Log.d("DeckViewModel", "Selected card: " + card.getName());
        cardSelected.setValue(card);
    }



    public LiveData<Card> getSelectedCard() {
        if (cardSelected.getValue() != null) {
            Log.d("DeckViewModel: ", "selectedCard is... "+ cardSelected.getValue().getName());
        } else {
            Log.d("DeckViewModel: ", "selectedCard is null.");
        }
        return cardSelected;
    }

}
