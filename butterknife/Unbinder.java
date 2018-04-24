package butterknife;

import android.support.annotation.UiThread;

public interface Unbinder {
    public static final Unbinder EMPTY = new C07041();

    static class C07041 implements Unbinder {
        C07041() {
        }

        public void unbind() {
        }
    }

    @UiThread
    void unbind();
}
