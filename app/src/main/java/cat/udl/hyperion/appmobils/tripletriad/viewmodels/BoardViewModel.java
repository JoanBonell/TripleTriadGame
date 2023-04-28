package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.tripletriad.models.Board;
import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.models.Cell;

public class BoardViewModel extends ViewModel {
    private MutableLiveData<Board> board;
    private DeckViewModel deckViewModel;

    public BoardViewModel(DeckViewModel deckViewModel) {
        board = new MutableLiveData<>();
        board.setValue(new Board());
        this.deckViewModel = deckViewModel;
    }


    public LiveData<Board> getBoard() {
        return board;
    }

    public void placeCard(int row, int col, Card card) {
        if (board.getValue() != null) {
            board.getValue().placeCard(card, new Cell(row, col));
            board.postValue(board.getValue());
        }
    }
    public void playCard(int row, int col, Card selectedCard) {
        if (selectedCard != null) {
            placeCard(row, col, selectedCard);
            // Hacer que la carta seleccionada sea nula después de colocarla en el tablero
            deckViewModel.setSelectedCard(null);
        } else {
            // TODO: Mostrar un mensaje para informar al usuario que no ha seleccionado una carta
        }
    }
}
