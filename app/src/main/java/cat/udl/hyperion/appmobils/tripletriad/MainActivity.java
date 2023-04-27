package cat.udl.hyperion.appmobils.tripletriad;

import static cat.udl.hyperion.appmobils.tripletriad.BR.deckViewModel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
