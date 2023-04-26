package cat.udl.hyperion.appmobils.tripletriad.models;

public class Game {
    private Player player;
    private Board board;
    private Deck deck;

    public Game(){
        board = new Board();
        board.initializeBoard();
        deck.initializeDeck();
        player = new Player("Joan");
    }

    public void placeCard(Card card, Cell cell) {
        board.placeCard(card, cell);
    }

    public void addCardToPlayerDeck(Card card) {
        player.addCardToDeck(card);
    }

    public void removeCardFromPlayerDeck(Card card) {
        player.removeCardFromDeck(card);
    }

    public void shufflePlayerDeck() {
        player.shuffleDeck();
    }

    public void increasePlayerPoints(int points) {
        player.increasePoints(points);
    }

    public Player getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }
}
