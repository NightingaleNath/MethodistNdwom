package ideanity.oceans.methodistndwom.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ideanity.oceans.methodistndwom.R;
import ideanity.oceans.methodistndwom.activities.AddNoteActivity;
import ideanity.oceans.methodistndwom.adapters.NoteAdapter;
import ideanity.oceans.methodistndwom.database.DatabaseAccess;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {

    public RecyclerView recyclerView;
    FloatingActionButton fab;

    public NoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        this.fab = (FloatingActionButton) view.findViewById(R.id.fab_add);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setHasFixedSize(true);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
        databaseAccess.open();
        List<HashMap<String, String>> noteData = databaseAccess.getAllNote();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(noteData.size());
        Log.d("data", sb.toString());

        this.recyclerView.setAdapter(new NoteAdapter(getContext(), noteData));

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
                Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), AddNoteActivity.class));
            }
        });

        return view;
    }
}
