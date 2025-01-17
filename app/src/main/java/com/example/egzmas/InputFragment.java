package com.example.egzmas;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InputFragment extends Fragment {

    private EditText inputText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        inputText = view.findViewById(R.id.edit_text_input);
        Button barChartButton = view.findViewById(R.id.button_bar_chart);
        Button lineChartButton = view.findViewById(R.id.button_line_chart);
        Button notifyButton = view.findViewById(R.id.button_notify);

        // Pasirinkimas stulpelinei diagramai
        barChartButton.setOnClickListener(v -> showChart("BAR"));

        // Pasirinkimas linijinei diagramai
        lineChartButton.setOnClickListener(v -> showChart("LINE"));

        // Notifikacijos siuntimas
        notifyButton.setOnClickListener(v -> sendNotification());

        return view;
    }

    private void showChart(String chartType) {
        String text = inputText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(getContext(), "Įveskite tekstą!", Toast.LENGTH_SHORT).show();
            return;
        }

        int vowels = countVowels(text);
        int consonants = countConsonants(text);
        int digits = countDigits(text);

        Bundle bundle = new Bundle();
        bundle.putInt("vowels", vowels);
        bundle.putInt("consonants", consonants);
        bundle.putInt("digits", digits);
        bundle.putString("chartType", chartType);

        Fragment chartFragment = new ChartFragment();
        chartFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_chart_container, chartFragment)
                .commit();
    }

    private void sendNotification() {
        String text = inputText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(getContext(), "Įveskite tekstą!", Toast.LENGTH_SHORT).show();
            return;
        }

        int vowels = countVowels(text);
        int consonants = countConsonants(text);
        int digits = countDigits(text);

        // Siunčiame notifikaciją (pavyzdžiui, naudojame `NotificationManager`)
        Toast.makeText(getContext(),
                "Balsių: " + vowels + ", Priebalsių: " + consonants + ", Skaitmenų: " + digits,
                Toast.LENGTH_LONG).show();
    }

    private int countVowels(String text) {
        return (int) text.chars().filter(c -> "AEIOUaeiou".indexOf(c) != -1).count();
    }

    private int countConsonants(String text) {
        return (int) text.chars().filter(c -> Character.isLetter(c) && "AEIOUaeiou".indexOf(c) == -1).count();
    }

    private int countDigits(String text) {
        return (int) text.chars().filter(Character::isDigit).count();
    }
}
