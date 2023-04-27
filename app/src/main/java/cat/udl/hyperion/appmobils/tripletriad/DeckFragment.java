package cat.udl.hyperion.appmobils.tripletriad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import cat.udl.hyperion.appmobils.tripletriad.databinding.FragmentDeckBinding;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.DeckViewModel;

public class DeckFragment extends Fragment {

    private DeckViewModel deckViewModel;
    private FragmentDeckBinding binding;
    private CardAdapter cardAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deck, container, false);

        deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        binding.setDeckViewModel(deckViewModel);
        binding.setLifecycleOwner(this);

        cardAdapter = new CardAdapter(deckViewModel.getDeck().getValue().getCards(), card -> deckViewModel.setSelectedCard(card));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerView.setAdapter(cardAdapter);

        deckViewModel.getDeck().observe(getViewLifecycleOwner(), deck -> {
            cardAdapter.notifyDataSetChanged();
        });

        return binding.getRoot();
    }
}
