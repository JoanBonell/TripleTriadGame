package cat.udl.hyperion.appmobils.tripletriad;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import cat.udl.hyperion.appmobils.tripletriad.BoardFragment;
import cat.udl.hyperion.appmobils.tripletriad.DeckFragment;
import cat.udl.hyperion.appmobils.tripletriad.PlayerFragment;
import cat.udl.hyperion.appmobils.tripletriad.R;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.DeckViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear una instancia de DeckViewModel
        DeckViewModel deckViewModel = new DeckViewModel();

        PlayerFragment playerFragment = PlayerFragment.newInstance();
        DeckFragment deckFragment = DeckFragment.newInstance();

        // Pasar la instancia de DeckViewModel al constructor de BoardFragment
        BoardFragment boardFragment = BoardFragment.newInstance(deckViewModel);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.boardFragment, boardFragment, "boardFragment");
        fragmentTransaction.add(R.id.playerFragment, playerFragment, "playerFragment");
        fragmentTransaction.add(R.id.deckFragment, deckFragment, "deckFragment");

        fragmentTransaction.commit();
    }
}
