package com.example.hay;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import net.daum.mf.map.api.MapView;

public class MapFragment extends BottomSheetDialogFragment {

MapView mapView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = new MapView(getContext());
        RelativeLayout mapViewContainer = v.findViewById(R.id.maplayout);
        mapViewContainer.addView(mapView);
       return v;
    }
}
