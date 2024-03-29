package cat.udl.hyperion.appmobils.tripletriad.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cat.udl.hyperion.appmobils.tripletriad.fragments.BoardFragment;
import cat.udl.hyperion.appmobils.tripletriad.fragments.DeckFragment;
import cat.udl.hyperion.appmobils.tripletriad.fragments.PlayerFragment;
import cat.udl.hyperion.appmobils.tripletriad.R;
import cat.udl.hyperion.appmobils.tripletriad.GameController;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.DeckViewModel;

public class GameActivity extends AppCompatActivity {
    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        BoardViewModel boardViewModel = new BoardViewModel(gameController);
        DeckViewModel humanDeckViewModel = new DeckViewModel();
        DeckViewModel computerDeckViewModel = new DeckViewModel();

        gameController = new GameController(boardViewModel, humanDeckViewModel, computerDeckViewModel);

        PlayerFragment playerFragment = PlayerFragment.newInstance();
        DeckFragment deckFragment = DeckFragment.newInstance(humanDeckViewModel);
        BoardFragment boardFragment = BoardFragment.newInstance(gameController);

        // Comienza una transacción de fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Agrega los fragmentos a la actividad utilizando contenedores en el archivo de diseño activity_game.xml
        fragmentTransaction.add(R.id.playerFragment, playerFragment);
        fragmentTransaction.add(R.id.deckFragment, deckFragment);
        fragmentTransaction.add(R.id.boardFragment, boardFragment);

        // Realiza la transacción
        fragmentTransaction.commit();
    }
}
