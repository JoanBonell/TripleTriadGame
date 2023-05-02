package cat.udl.hyperion.appmobils.tripletriad.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import cat.udl.hyperion.appmobils.tripletriad.fragments.BoardFragment;
import cat.udl.hyperion.appmobils.tripletriad.fragments.DeckFragment;
import cat.udl.hyperion.appmobils.tripletriad.fragments.PlayerFragment;
import cat.udl.hyperion.appmobils.tripletriad.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        PlayerFragment playerFragment = PlayerFragment.newInstance();
        DeckFragment deckFragment = DeckFragment.newInstance();
        BoardFragment boardFragment = BoardFragment.newInstance();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.boardFragment, boardFragment, "boardFragment");
        fragmentTransaction.add(R.id.playerFragment, playerFragment, "playerFragment");
        fragmentTransaction.add(R.id.deckFragment, deckFragment, "deckFragment");

        fragmentTransaction.commit();
    }
}
