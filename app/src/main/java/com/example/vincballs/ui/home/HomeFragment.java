package com.example.vincballs.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vincballs.DesmosWebView;
import com.example.vincballs.MainActivity;
import com.example.vincballs.R;
import com.example.vincballs.Sketch;
import com.example.vincballs.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import processing.android.PFragment;
import processing.core.PApplet;
import processing.core.PVector;

public class HomeFragment extends Fragment {

    private PApplet sketch;
    Button btnGraphNow;
    Button btnReset;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        FrameLayout frame = (FrameLayout) homeView.findViewById(R.id.container);

        Sketch sketch = new Sketch();
        PFragment fragment = new PFragment(sketch);
        fragment.setView(frame, getActivity());


        btnGraphNow = homeView.findViewById(R.id.btnGraphNow);
        btnReset = homeView.findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sketch.onReset();
            }
        });

        btnGraphNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DesmosWebView.class);
                Bundle extra = new Bundle();
                ArrayList<ArrayList<PVector>> drawing = sketch.getDrawing();
                extra.putSerializable("drawing", drawing);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });

        return homeView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}