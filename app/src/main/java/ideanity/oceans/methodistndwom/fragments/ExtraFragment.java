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
public class ExtraFragment extends Fragment {

    public ExtraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_extra, container, false);

        ((TextView) view.findViewById(R.id.anthem)).setText("MHB 396\n\n1. LORD,In the fullness of my might,\nI would for Thee be strong:\nWhile runneth o\\'er each dear delight,\nTo Thee should soar my song.\n\n2. I would not give the world my heart,\nAnd then profess Thy love;\nI would not feel my strength depart,\nAnd then Thy service prove.\n\n3. I would not with swift-winged zeal\nOn the world\\'s errands go,\nAnd labour up the heavenly hill\nWith weary feet and slow.\n\n4. O not for Thee my weak desires,\nMy poorer, baser part! O not for\nThee my fading fires,\nThe ashes of my heart!\n\n5. choose me In my golden time,\nIn my dear joys have part!\nFor Thee the glory of my prime,\nThe fullness of my heart\n\n6. I cannot, Lord, too early take\nThe covenant divine; O\nne\\'er the happy heart may break\nWhose earliest love was Thine.\n\n\n");

        return  view;
    }
}
