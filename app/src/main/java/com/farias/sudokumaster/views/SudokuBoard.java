package com.farias.sudokumaster.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.farias.sudokumaster.R;

import androidx.annotation.Nullable;

public class SudokuBoard extends View {

    private final int boardColor;
    private final Paint boardColorPaint = new Paint();
    private int cellSize;
    private final int BOARD_BOARDER_LINE = 16;
    private final int THICK_LINE = 10;
    private final int THIN_LINE = 4;

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes =
              context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard, 0, 0);

        try {
            boardColor = attributes.getInteger(R.styleable.SudokuBoard_boardColor, 0);
        } finally {
            attributes.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension / 9;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //setup canvas properties
        boardColorPaint.setStyle(Style.STROKE);
        boardColorPaint.setStrokeWidth(BOARD_BOARDER_LINE);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        //start drawing
        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawBoard(canvas);
    }

    private void drawThickLine() {
        boardColorPaint.setStyle(Style.STROKE);
        boardColorPaint.setStrokeWidth(THICK_LINE);
        boardColorPaint.setColor(boardColor);
    }

    private void drawThinLine() {
        boardColorPaint.setStyle(Style.STROKE);
        boardColorPaint.setStrokeWidth(THIN_LINE);
        boardColorPaint.setColor(boardColor);
    }

    private void drawBoard(Canvas canvas) {
        for (int column = 0; column < 10; column++) {
            if (column % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }
            canvas.drawLine(cellSize * column, 0, cellSize * column, getWidth(), boardColorPaint);
        }

        for (int row = 0; row < 10; row++) {
            if (row % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }
            canvas.drawLine(0, cellSize * row,  getHeight(), cellSize * row, boardColorPaint);
        }
    }
}
