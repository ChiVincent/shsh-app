package okhttp3;

import java.io.IOException;

public interface Authenticator {
    public static final Authenticator NONE = new C08591();

    static class C08591 implements Authenticator {
        C08591() {
        }

        public Request authenticate(Route route, Response response) {
            return null;
        }
    }

    Request authenticate(Route route, Response response) throws IOException;
}
