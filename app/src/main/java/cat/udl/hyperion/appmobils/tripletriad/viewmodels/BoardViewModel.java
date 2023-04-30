package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.hyperion.appmobils.tripletriad.R;
import cat.udl.hyperion.appmobils.tripletriad.models.Board;
import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.models.Cell;

public class BoardViewModel extends ViewModel {
    private MutableLiveData<Board> board;
    private DeckViewModel deckViewModel;
    private static final String TAG = "BoardViewModel";

    private CellViewModel[][] cells;
    private MutableLiveData<Card> selectedCard = new MutableLiveData<>();



    public LiveData<Card> getSelectedCard(){
        return deckViewModel.getSelectedCard();
    }

    public void setSelectedCard(Card card) {
        selectedCard.setValue(card);
    }


    public BoardViewModel() {
        this.cells = new CellViewModel[3][3];
        Log.d(TAG, "Creando el BoardViewModel...");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Cell cell = new Cell(row, col);
                cells[row][col] = new CellViewModel(cell);
            }
        }
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
    public void playCard(int row, int col, Card card) {
        Log.d(TAG, "Jugando una carta " + (card != null ? card.toString() : "null"));
        if (card != null) {
            Board boardInstance = board.getValue();
            if (boardInstance != null && boardInstance.isEmpty(row, col)) {
                boardInstance.setCardAt(row, col, card);
                board.setValue(boardInstance);
                // Aquí puedes agregar cualquier lógica adicional del juego
                // como verificar si el juego ha terminado o cambiar de jugador
            }
        }
    }

    public CellViewModel getCellAt(int row, int col) {
        return cells[row][col];
    }
    public void playSelectedCard(int row, int col, DeckViewModel deckViewModel) {
        LiveData<Card> selectedCardLiveData = deckViewModel.getSelectedCard();
        Card selectedCard = new Card(R.drawable.barrio_adri_contreras_presidente,"AdriContreras", 9, 7, 10,10);
        Log.d("BoardViewModel", "Colocando una carta en la posicion " + row + " " + col + " la carta es " + (selectedCard != null ? selectedCard.getName() : "null"));
        if (selectedCard != null) {
            playCard(row, col, selectedCard);
            cells[row][col].setCard(selectedCard);
        }
    }
    public DeckViewModel getDeckViewModel() {
        return deckViewModel;
    }

    public void setDeckViewModel(DeckViewModel deckViewModel) {
        this.deckViewModel = deckViewModel;
    }






}
