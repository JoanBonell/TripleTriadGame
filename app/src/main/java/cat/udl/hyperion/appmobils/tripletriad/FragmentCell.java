package cat.udl.hyperion.appmobils.tripletriad;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.udl.hyperion.appmobils.tripletriad.databinding.FragmentCellBinding;
import cat.udl.hyperion.appmobils.tripletriad.models.Cell;

public class FragmentCell extends Fragment {

    private FragmentCellBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cell, container, false);

        Cell cell = getArguments().getParcelable("cell");
        binding.setCellViewModel(cell);
       // binding.setCell(cell);
        binding.executePendingBindings();

        return binding.getRoot();
    }
}
