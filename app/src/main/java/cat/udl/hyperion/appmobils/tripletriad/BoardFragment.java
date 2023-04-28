package cat.udl.hyperion.appmobils.tripletriad;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.udl.hyperion.appmobils.tripletriad.databinding.FragmentBoardBinding;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.DeckViewModel;

public class BoardFragment extends Fragment implements OnCellClickListener {

    private BoardViewModel boardViewModel;
    private DeckViewModel deckViewModel;
    private FragmentBoardBinding binding;
    private CellAdapter cellAdapter;

    public static BoardFragment newInstance(DeckViewModel deckViewModel) {
        BoardFragment fragment = new BoardFragment();
        fragment.setDeckViewModel(deckViewModel);
        return fragment;
    }

    public void setDeckViewModel(DeckViewModel deckViewModel){
        this.deckViewModel = deckViewModel;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardViewModel = new BoardViewModel(deckViewModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false);

        binding.setBoardViewModel(boardViewModel);
        binding.setLifecycleOwner(this);
        setupRecyclerView();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        cellAdapter = new CellAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.recyclerView.setAdapter(cellAdapter);
    }

    @Override
    public void onCellClick(int row, int col) {
        boardViewModel.playCard(row, col);
    }
}
