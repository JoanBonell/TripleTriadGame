package cat.udl.hyperion.appmobils.tripletriad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import cat.udl.hyperion.appmobils.tripletriad.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.DeckViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear una instancia de DeckViewModel
        DeckViewModel deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);

        PlayerFragment playerFragment = PlayerFragment.newInstance();
        DeckFragment deckFragment = DeckFragment.newInstance();

        // Crear una instancia de BoardViewModel
        BoardViewModel boardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
        // Asignar la instancia de DeckViewModel al BoardViewModel
        boardViewModel.setDeckViewModel(deckViewModel);

        // Crear una instancia de BoardFragment sin pasar DeckViewModel como parámetro
        BoardFragment boardFragment = BoardFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.boardFragment, boardFragment, "boardFragment");
        fragmentTransaction.add(R.id.playerFragment, playerFragment, "playerFragment");
        fragmentTransaction.add(R.id.deckFragment, deckFragment, "deckFragment");

        fragmentTransaction.commit();
    }
}
