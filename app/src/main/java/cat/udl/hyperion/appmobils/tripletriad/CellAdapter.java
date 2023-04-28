package cat.udl.hyperion.appmobils.tripletriad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cat.udl.hyperion.appmobils.tripletriad.databinding.CellLayoutBinding;
import cat.udl.hyperion.appmobils.tripletriad.models.Board;
import cat.udl.hyperion.appmobils.tripletriad.models.Card;
import cat.udl.hyperion.appmobils.tripletriad.models.Cell;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.BoardViewModel;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.CellViewHolder> {

    private final int numCells = 9;
    private final OnCellClickListener onCellClickListener;
    private final BoardViewModel boardViewModel;

    public CellAdapter(OnCellClickListener onCellClickListener, BoardViewModel boardViewModel) {
        this.onCellClickListener = onCellClickListener;
        this.boardViewModel = boardViewModel;
    }

    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CellLayoutBinding binding = CellLayoutBinding.inflate(inflater, parent, false);
        return new CellViewHolder(binding);
    }

    @Override
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
