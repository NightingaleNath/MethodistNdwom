package ideanity.oceans.methodistndwom.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import java.util.HashMap;
import java.util.List;

import ideanity.oceans.methodistndwom.R;
import ideanity.oceans.methodistndwom.adapters.HymnAdapter;
import ideanity.oceans.methodistndwom.database.DatabaseAccess;


/**
 * A simple {@link Fragment} subclass.
 */
public class HymnFragment extends Fragment {

    EditText etxtSearch;
    ImageView imgNoProduct;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;

    public HymnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hymn, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.item_list);
        this.etxtSearch = (EditText) view.findViewById(R.id.dialog_input);
        this.imgNoProduct = (ImageView) view.findViewById(R.id.image_no_product);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setHasFixedSize(true);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        List<HashMap<String, String>> hymnData = databaseAccess.getHymns();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(hymnData.size());
        Log.d("data", sb.toString());

        this.recyclerView.setAdapter(new HymnAdapter(getContext(), hymnData));

        this.etxtSearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
                databaseAccess.open();
                List<HashMap<String, String>> searchHymnList = databaseAccess.searchHymns(s.toString());
                if (searchHymnList.size() <= 0) {
                    recyclerView.setVisibility(View.GONE);
                    imgNoProduct.setVisibility(View.VISIBLE);
                    imgNoProduct.setImageResource(R.drawable.not_found);
                    return;
                }
                recyclerView.setVisibility(View.VISIBLE);
                imgNoProduct.setVisibility(View.GONE);
                recyclerView.setAdapter(new HymnAdapter(getActivity(), searchHymnList));
            }

            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }
}
