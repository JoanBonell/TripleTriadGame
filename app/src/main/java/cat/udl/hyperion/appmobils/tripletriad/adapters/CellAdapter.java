package cat.udl.hyperion.appmobils.tripletriad.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.hyperion.appmobils.tripletriad.R;
import cat.udl.hyperion.appmobils.tripletriad.databinding.CellLayoutBinding;
import cat.udl.hyperion.appmobils.tripletriad.GameController;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.CellViewModel;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder> {

    private final int numCells = 9;
    private final GameController gameController;

    private List<CellViewModel> cellViewModels;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CellLayoutBinding binding;

        public ViewHolder(@NonNull CellLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public CellAdapter(GameController gameController) {
        this.gameController = gameController;
        this.cellViewModels = gameController.getBoardViewModel().getCellViewModels();
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
        CellViewModel cellViewModel = cellViewModels.get(position);
        holder.binding.setCellViewModel(cellViewModel);
        holder.binding.executePendingBindings();

        View.OnClickListener cellClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameController.isCellEmpty(row, col)) {
                    gameController.playCard(row, col);
                }
            }
        };

        holder.binding.setCellClickListener(cellClickListener);
    }
    @Override
    public int getItemCount() {
        return numCells;
    }
}
