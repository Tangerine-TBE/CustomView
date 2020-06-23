package com.tangerine.customview.day_01;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.tangerine.customview.R;

public class ItemFragment extends Fragment {


    public static ItemFragment newInstance (String item){
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",item);
        itemFragment.setArguments(bundle);
        return itemFragment;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_item,container,false);
        AppCompatTextView appCompatTextView = view.findViewById(R.id.text);
        Bundle bundle  =getArguments();
        String text = bundle.getString("title") ;
        appCompatTextView.setText(text);


        return view;
    }
}
