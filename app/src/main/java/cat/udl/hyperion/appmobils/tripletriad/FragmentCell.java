package cat.udl.hyperion.appmobils.tripletriad;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.udl.hyperion.appmobils.tripletriad.databinding.CellLayoutBinding;
import cat.udl.hyperion.appmobils.tripletriad.models.Cell;
import cat.udl.hyperion.appmobils.tripletriad.viewmodels.CellViewModel;

public class FragmentCell extends Fragment {

    private CellLayoutBinding binding;

    private Cell cell;

    public static FragmentCell newInstance(Cell cell) {
        FragmentCell fragment = new FragmentCell();
        fragment.setCell(cell);
        return fragment;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.cell_layout, container, false);
        CellViewModel cellViewModel = new CellViewModel(cell);
        binding.setCellViewModel(cellViewModel);
        return binding.getRoot();
    }
}
