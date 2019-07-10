package com.example.sti_agent.operation_fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sti_agent.GridSpacingItemDecoration;
import com.example.sti_agent.Model.QuoteCard;
import com.example.sti_agent.R;
import com.example.sti_agent.adapter.QuoteBuyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuoteBuyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.recycler_quote_card)
    RecyclerView recyclerView;

    private QuoteBuyAdapter quotebuyAdapter;
    private List<QuoteCard> cardList;



    public QuoteBuyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static QuoteBuyFragment newInstance(String param1, String param2) {
        QuoteBuyFragment fragment = new QuoteBuyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quotebuy, container, false);
        ButterKnife.bind(this,view);

        cardList = new ArrayList<>();
        quotebuyAdapter = new QuoteBuyAdapter(getContext(), cardList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(quotebuyAdapter);

//        populating the card
        insertElement();


        return  view;
    }

    private void insertElement() {
//        referencing drawable for the logo
        int[] icons = new int[]{
                R.drawable.ic_car,
                R.drawable.ic_family,
                R.drawable.ic_ship,
                R.drawable.ic_risks,
                R.drawable.ic_travel,
                R.drawable.ic_scroll
        };

        QuoteCard m = new QuoteCard("Motor Insurance", icons[0]);
        cardList.add(m);

        m = new QuoteCard("SWIS-F Insurance", icons[1]);
        cardList.add(m);

        m = new QuoteCard("Marine Insurance", icons[2]);
        cardList.add(m);

        m = new QuoteCard("All Risks Insurance", icons[3]);
        cardList.add(m);

        m = new QuoteCard("ETIC Insurance", icons[4]);
        cardList.add(m);

        m = new QuoteCard("Other Insurance", icons[5]);
        cardList.add(m);






    }



    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
