package ideanity.oceans.methodistndwom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ideanity.oceans.methodistndwom.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CanFanteFragment extends Fragment {

    public RecyclerView recyclerView;
    FloatingActionButton fab;
    EditText etxtSearch;
    ImageView imgNoProduct;

    public CanFanteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_can_fante, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.item_list);
        this.fab = (FloatingActionButton) view.findViewById(R.id.fab_switch);
        this.etxtSearch = (EditText) view.findViewById(R.id.dialog_input);
        this.imgNoProduct = (ImageView) view.findViewById(R.id.image_no_product);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CanticleFragment();
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
