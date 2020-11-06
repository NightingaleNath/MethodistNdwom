package ideanity.oceans.methodistndwom.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ideanity.oceans.methodistndwom.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoirAnthemFragment extends Fragment {

    public ChoirAnthemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choir_anthem, container, false);

        TextView textView = (TextView) view.findViewById(R.id.anthem);
        textView.setText("\n1. SERVICE TO GOD\n   We'll sing with heart and voice\n   Sing of his praise\n   Aloud in all the earth.\n\nCHORUS:\n   Together let us sing to God\n   Whose goodness faileth never\n   We'll sing and praise the Lord,\n   Forever-more.\n\n2. SERVICE TO GOD\n   We'll sing his love forever\n   Sing of his power\n   And glory his name.\n \n3. SERVICE TO GOD\n   We'll serve the Lord with gladness\n   Sing unto him\n   Exalt and bless his name.\n\n4. SERVICE TO GOD\n   To him be the glory given\n   Sing to the lord\n   Whom heaven and earth adore.\n\n\n\n");

        return view;
    }
}
