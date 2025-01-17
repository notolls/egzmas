package com.example.egzmas;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChartFragment extends Fragment {

 private int vowels = 0;
 private int consonants = 0;
 private int digits = 0;
 private String chartType = "BAR";

 @Nullable
 @Override
 public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
  Bundle args = getArguments();
  if (args != null) {
   vowels = args.getInt("vowels");
   consonants = args.getInt("consonants");
   digits = args.getInt("digits");
   chartType = args.getString("chartType");
  }

  // Priklausomai nuo grafiko tipo parenkame tinkamą View
  if ("BAR".equals(chartType)) {
   return new BarChartView(getContext(), vowels, consonants, digits);
  } else {
   return new LineChartView(getContext(), vowels, consonants, digits);
  }
 }
}
