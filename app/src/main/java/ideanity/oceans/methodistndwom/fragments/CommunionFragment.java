package ideanity.oceans.methodistndwom.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ideanity.oceans.methodistndwom.R;
import ideanity.oceans.methodistndwom.utils.AppHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunionFragment extends Fragment {

    public CommunionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_communion, container, false);

       ((TextView) view.findViewById(R.id.comm)).setText(Html.fromHtml(new AppHelper(getActivity()).gfile("communion/com.txt")));
        return view;
    }
}
