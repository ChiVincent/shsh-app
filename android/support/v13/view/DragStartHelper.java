package android.support.v13.view;

import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class DragStartHelper {
    private int mLastTouchX;
    private int mLastTouchY;
    private final OnDragStartListener mListener;
    private final OnLongClickListener mLongClickListener = new C00291();
    private final OnTouchListener mTouchListener = new C00302();
    private final View mView;

    class C00291 implements OnLongClickListener {
        C00291() {
        }

        public boolean onLongClick(View v) {
            return DragStartHelper.this.onLongClick(v);
        }
    }

    class C00302 implements OnTouchListener {
        C00302() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return DragStartHelper.this.onTouch(v, event);
        }
    }

    public interface OnDragStartListener {
        boolean onDragStart(View view, DragStartHelper dragStartHelper);
    }

    public DragStartHelper(View view, OnDragStartListener listener) {
        this.mView = view;
        this.mListener = listener;
    }

    public void attach() {
        this.mView.setOnLongClickListener(this.mLongClickListener);
        this.mView.setOnTouchListener(this.mTouchListener);
    }

    public void detach() {
        this.mView.setOnLongClickListener(null);
        this.mView.setOnTouchListener(null);
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == 0 || event.getAction() == 2) {
            this.mLastTouchX = (int) event.getX();
            this.mLastTouchY = (int) event.getY();
        }
        if (event.getAction() == 2 && MotionEventCompat.isFromSource(event, 8194) && (MotionEventCompat.getButtonState(event) & 1) != 0) {
            return this.mListener.onDragStart(v, this);
        }
        return false;
    }

    public boolean onLongClick(View v) {
        return this.mListener.onDragStart(v, this);
    }

    public void getTouchPosition(Point point) {
        point.set(this.mLastTouchX, this.mLastTouchY);
    }
}
