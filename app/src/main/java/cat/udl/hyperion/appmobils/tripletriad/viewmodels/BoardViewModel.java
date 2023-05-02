package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.tripletriad.BR;
import cat.udl.hyperion.appmobils.tripletriad.R;
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
        initializeCellViewModels();
        board = new MutableLiveData<>();
        board.setValue(new Board());
    }
    private void initializeCellViewModels() {
        cellViewModels = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CellViewModel cellViewModel = getCellViewModelAt(row, col);
                cellViewModels.add(cellViewModel);
            }
        }
    }


    public LiveData<Board> getBoard() {
        return board;
    }

    public void placeCard(int row, int col, Card card) {
        Cell cell = getCellAt(row, col);
        cell.setCard(card);
        updateCellInCellsList(cell);
    }

    public void playCard(int row, int col, Card card) {
        Log.d(TAG, "Jugando una carta " + (card != null ? card.toString() : "null"));
        if (card != null) {
            Board boardInstance = board.getValue();
            if (boardInstance != null && boardInstance.isEmpty(row, col)) {
                boardInstance.setCardAt(row, col, card);
                board.setValue(boardInstance);
            }
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
        List<Cell> cellList = cells.getValue();
        if (cellList != null) {
            return new CellViewModel(cellList.get(row * 3 + col));
        } else {
            throw new IllegalStateException("Cells MutableLiveData has a null value");
        }
    }


    public void playSelectedCard(int row, int col) {
        Card cardToPlay = deckViewModel.getSelectedCard().getValue();
        if (cardToPlay != null) {
            Log.d(TAG, "Colocando una carta en la posición " + row + " " + col + " la carta es " + cardToPlay.getName());
            placeCard(row, col, cardToPlay);

            // Elimina la carta del deck una vez que se ha jugado
            deckViewModel.removeCardFromDeck(cardToPlay);

            // Imprime el estado del tablero
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
    private void updateCellInCellsList(Cell updatedCell) {
        List<Cell> cellList = cells.getValue();
        if (cellList != null) {
            int index = updatedCell.getRow() * 3 + updatedCell.getCol();
            cellList.set(index, updatedCell);
            cells.setValue(cellList);
        } else {
            throw new IllegalStateException("Cells MutableLiveData has a null value");
        }
    }
    public void logBoardState() {
        StringBuilder boardState = new StringBuilder("Estado del tablero:\n");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Cell cell = getCellAt(row, col);
                Card card = cell.getCard();
                if (card != null) {
                    boardState.append(String.format("Posición (%d, %d): %s\n", row, col, card.getName()));
                } else {
                    boardState.append(String.format("Posición (%d, %d): Vacío\n", row, col));
                }
            }
        }
        Log.d(TAG, boardState.toString());
    }
    // BoardViewModel.java
    public List<CellViewModel> getCellViewModels() {
        return cellViewModels;
    }


}
