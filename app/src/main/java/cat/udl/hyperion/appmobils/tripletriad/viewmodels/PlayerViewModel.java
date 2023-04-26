package cat.udl.hyperion.appmobils.tripletriad.viewmodels;

import androidx.lifecycle.ViewModel;
import cat.udl.hyperion.appmobils.tripletriad.models.Player;

public class PlayerViewModel extends ViewModel {
    private Player player;

    public PlayerViewModel() {
        // Asume que el nombre del jugador es "Jugador1" por defecto.
        player = new Player("Joan");
        player.getDeck().initializeDeck();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // Puedes agregar más métodos si es necesario para actualizar y gestionar la información del jugador.
}
