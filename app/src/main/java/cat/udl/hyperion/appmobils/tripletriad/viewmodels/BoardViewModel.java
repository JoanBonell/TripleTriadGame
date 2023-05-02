package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.tripletriad.models.Board;
import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.models.Cell;

public class BoardViewModel extends ViewModel {
    private MutableLiveData<Board> board;
    private DeckViewModel deckViewModel;
    private static final String TAG = "BoardViewModel";
    private MutableLiveData<Card> selectedCard = new MutableLiveData<>();

    private MutableLiveData<List<Cell>> cells;
    private List<CellViewModel> cellViewModels;

    public LiveData<Card> getSelectedCard(){
        return deckViewModel.getSelectedCard();
    }

    public void setSelectedCard(Card card) {
        selectedCard.setValue(card);
    }

    public BoardViewModel() {
        Log.d(TAG, "Creando el BoardViewModel...");

        cells = new MutableLiveData<>();
        List<Cell> initialCells = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Cell cell = new Cell(row, col);
                initialCells.add(cell);
            }
        }

        cells.setValue(initialCells);

        cellViewModels = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            cellViewModels.add(new CellViewModel(initialCells.get(i)));
        }

        board = new MutableLiveData<>();
        board.setValue(new Board());
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

    public Cell getCellAt(int row, int col) {
        List<Cell> cellList = cells.getValue();
        if (cellList != null) {
            return cellList.get(row * 3 + col);
        } else {
            throw new IllegalStateException("Cells MutableLiveData has a null value");
        }
    }

    public CellViewModel getCellViewModelAt(int row, int col) {
        if (cellViewModels != null) {
            return cellViewModels.get(row * 3 + col);
        } else {
            throw new IllegalStateException("CellViewModels list is not initialized");
        }
    }

    public void playSelectedCard(int row, int col) {
        Card cardToPlay = deckViewModel.getSelectedCard().getValue();
        if (cardToPlay != null) {
            Log.d(TAG, "Colocando una carta en la posición " + row + " " + col + " la carta es " + cardToPlay.getName());
            placeCard(row, col, cardToPlay);

            CellViewModel cellViewModel = getCellViewModelAt(row, col);
            cellViewModel.setCard(cardToPlay);

            deckViewModel.removeCardFromDeck(cardToPlay);

            logBoardState();
        } else {
            Log.d(TAG, "La carta seleccionada es null");
        }
    }

    public DeckViewModel getDeckViewModel() {
        return deckViewModel;
    }

    public void setDeckViewModel(DeckViewModel deckViewModel) {
        this.deckViewModel = deckViewModel;
    }

    public void logBoardState() {
        StringBuilder boardState = new StringBuilder("Estado del tablero:\n");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CellViewModel cellViewModel = getCellViewModelAt(row, col);
                Card card = cellViewModel.getCard().getValue();
                if (card != null) {
                    boardState.append(String.format("Posición (%d, %d): %s\n", row, col, card.getName()));
                } else {
                    boardState.append(String.format("Posición (%d, %d): Vacío\n", row, col));
                }
            }
        }
        Log.d(TAG, boardState.toString());
    }


    public List<CellViewModel> getCellViewModels() {
        return cellViewModels;
    }
}

