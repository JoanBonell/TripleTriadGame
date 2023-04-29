package cat.udl.hyperion.appmobils.tripletriad;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
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
        CellLayoutBinding binding = CellLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }


    /*@Override
    public void onBindViewHolder(@NonNull CellViewHolder holder, int position) {
        int row = position / 3;
        int col = position % 3;
        holder.itemView.setOnClickListener(view -> onCellClickListener.onCellClick(row, col));

        // Añade estas líneas para actualizar la vista con los datos de la tarjeta
        Cell cell = new Cell(row, col);
        Board board = boardViewModel.getBoard().getValue();
        if (board != null) {
            Card card = board.getCardAt(cell);
            holder.binding.setCellViewModel(cell); // Cambia esta línea
        }
    }*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        CellViewModel cellViewModel = cellViewModels.get(position);
        viewHolder.binding.setCellViewModel(cellViewModel);
        viewHolder.binding.executePendingBindings();
    }





    @Override
    public int getItemCount() {
        return numCells;
    }

    static class CellViewHolder extends RecyclerView.ViewHolder {

        private final CellLayoutBinding binding;

        public CellViewHolder(@NonNull CellLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
