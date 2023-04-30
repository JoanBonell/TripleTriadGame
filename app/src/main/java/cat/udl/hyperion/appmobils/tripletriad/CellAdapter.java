package cat.udl.hyperion.appmobils.tripletriad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.hyperion.appmobils.tripletriad.databinding.CellLayoutBinding;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.BoardViewModel;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.CellViewModel;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder> {

    private final int numCells = 9;
    private final BoardViewModel boardViewModel;

    private List<CellViewModel> cellViewModels;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CellLayoutBinding binding;

        public ViewHolder(@NonNull CellLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public CellAdapter(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
        cellViewModels = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CellViewModel cellViewModel = boardViewModel.getCellAt(row, col);
                cellViewModels.add(cellViewModel);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CellLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.cell_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int row = position / 3;
        int col = position % 3;
        CellViewModel cellViewModel = boardViewModel.getCellAt(row, col);
        holder.binding.setCellViewModel(cellViewModel);
        holder.binding.setBoard(boardViewModel);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return numCells;
    }
}
