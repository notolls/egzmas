package com.example.egzmas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BarChartView extends View {

    private final int vowels;
    private final int consonants;
    private final int digits;

    private final Paint paint = new Paint();

    public BarChartView(Context context, int vowels, int consonants, int digits) {
        super(context);
        this.vowels = vowels;
        this.consonants = consonants;
        this.digits = digits;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int padding = 100; // Viršutinis tarpas diagramai

        // Normalizuoti aukščiai
        int maxCount = Math.max(vowels, Math.max(consonants, digits));
        float scale = (height - 2 * padding) / (float) maxCount;

        int barWidth = width / 6;

        // Balsių stulpelis
        paint.setColor(Color.BLUE);
        canvas.drawRect(width / 6f - barWidth / 2f, height - padding - vowels * scale, width / 6f + barWidth / 2f, height - padding, paint);

        // Priebalsių stulpelis
        paint.setColor(Color.GREEN);
        canvas.drawRect(width / 2f - barWidth / 2f, height - padding - consonants * scale, width / 2f + barWidth / 2f, height - padding, paint);

        // Skaitmenų stulpelis
        paint.setColor(Color.RED);
        canvas.drawRect(width * 5 / 6f - barWidth / 2f, height - padding - digits * scale, width * 5 / 6f + barWidth / 2f, height - padding, paint);

        // Užrašai
        paint.setColor(Color.BLACK);
        paint.setTextSize(40f);
        canvas.drawText("Balsės", width / 6f - barWidth / 2f, height - padding - vowels * scale - 20f, paint);
        canvas.drawText("Priebalsės", width / 2f - barWidth / 2f, height - padding - consonants * scale - 20f, paint);
        canvas.drawText("Skaitmenys", width * 5 / 6f - barWidth / 2f, height - padding - digits * scale - 20f, paint);
    }
}
