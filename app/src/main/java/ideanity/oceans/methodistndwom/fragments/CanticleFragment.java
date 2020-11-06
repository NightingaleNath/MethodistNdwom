package ideanity.oceans.methodistndwom.fragments;

import android.content.Intent;
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
import ideanity.oceans.methodistndwom.adapters.CanticleAdapter;
import ideanity.oceans.methodistndwom.database.DatabaseAccess;


/**
 * A simple {@link Fragment} subclass.
 */
public class CanticleFragment extends Fragment {

    public RecyclerView recyclerView;
    FloatingActionButton fab;
    EditText etxtSearch;
    ImageView imgNoProduct;

    public CanticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_canticle, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.item_list);
        this.fab = (FloatingActionButton) view.findViewById(R.id.fab_switch);
        this.etxtSearch = (EditText) view.findViewById(R.id.dialog_input);
        this.imgNoProduct = (ImageView) view.findViewById(R.id.image_no_product);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setHasFixedSize(true);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        List<HashMap<String, String>> canticleData = databaseAccess.getCanticles();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(canticleData.size());
        Log.d("data", sb.toString());

        this.recyclerView.setAdapter(new CanticleAdapter(getContext(), canticleData));

        this.etxtSearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
                databaseAccess.open();
                List<HashMap<String, String>> searchCanticleList = databaseAccess.searchCanticles(s.toString());
                if (searchCanticleList.size() <= 0) {
                    recyclerView.setVisibility(View.GONE);
                    imgNoProduct.setVisibility(View.VISIBLE);
                    imgNoProduct.setImageResource(R.drawable.not_found);
                    return;
                }
                recyclerView.setVisibility(View.VISIBLE);
                imgNoProduct.setVisibility(View.GONE);
                recyclerView.setAdapter(new CanticleAdapter(getActivity(), searchCanticleList));
            }

            public void afterTextChanged(Editable s) {
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
                    fab.show();

                } else if (dy > 0) {
                    fab.hide();
                }

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CanFanteFragment();
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
