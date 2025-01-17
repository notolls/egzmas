package com.example.egzmas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class LineChartView extends View {

    private final int vowels;
    private final int consonants;
    private final int digits;

    private final Paint paint = new Paint();

    public LineChartView(Context context, int vowels, int consonants, int digits) {
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
        int padding = 100; // Tarpo dydis nuo viršaus ir apačios

        // Normalizuoti aukščiai
        int maxCount = Math.max(vowels, Math.max(consonants, digits));
        float scale = (height - 2 * padding) / (float) maxCount;

        int gap = width / 4;

        // Taškai
        float[] points = {
                gap, height - padding - vowels * scale,                // Balsės
                gap * 2, height - padding - consonants * scale,        // Priebalsės
                gap * 3, height - padding - digits * scale             // Skaitmenys
        };

        // Linija tarp taškų
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5f);
        canvas.drawLines(new float[]{
                points[0], points[1], points[2], points[3],
                points[2], points[3], points[4], points[5]
        }, paint);

        // Taškai kaip apskritimai
        paint.setColor(Color.RED);
        for (int i = 0; i < points.length; i += 2) {
            canvas.drawCircle(points[i], points[i + 1], 15f, paint);
        }

        // Užrašai
        paint.setColor(Color.BLACK);
        paint.setTextSize(40f);
        canvas.drawText("Balsės", gap - 50, height - padding - vowels * scale - 20f, paint);
        canvas.drawText("Priebalsės", gap * 2 - 70, height - padding - consonants * scale - 20f, paint);
        canvas.drawText("Skaitmenys", gap * 3 - 70, height - padding - digits * scale - 20f, paint);
    }
}
