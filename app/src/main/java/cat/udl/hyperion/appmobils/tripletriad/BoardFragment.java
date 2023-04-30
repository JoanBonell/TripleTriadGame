package cat.udl.hyperion.appmobils.tripletriad;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.udl.hyperion.appmobils.tripletriad.databinding.FragmentBoardBinding;
import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.DeckViewModel;

public class BoardFragment extends Fragment {

    private BoardViewModel boardViewModel;
    private DeckViewModel deckViewModel;
    private FragmentBoardBinding binding;
    private CellAdapter cellAdapter;

    public static BoardFragment newInstance() {
        BoardFragment fragment = new BoardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    public void setDeckViewModel(DeckViewModel deckViewModel){
        this.deckViewModel = deckViewModel;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener una instancia de BoardViewModel
        boardViewModel = new ViewModelProvider(requireActivity()).get(BoardViewModel.class);

        // Obtener una instancia de DeckViewModel
        deckViewModel = new ViewModelProvider(requireActivity()).get(DeckViewModel.class);

        // Asignar la instancia de DeckViewModel al BoardViewModel
        boardViewModel.setDeckViewModel(deckViewModel);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false);

        binding.setBoardViewModel(boardViewModel);
        binding.setLifecycleOwner(this);
        setupRecyclerView();

        deckViewModel.getSelectedCard().observe(getViewLifecycleOwner(), new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                // Aquí puede manejar los cambios en la carta seleccionada si es necesario
               // boardViewModel.playCard(1,0,card);
            }
        });

        return binding.getRoot();
    }



    private void setupRecyclerView() {
        cellAdapter = new CellAdapter(boardViewModel);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerView.setAdapter(cellAdapter);
    }






}
