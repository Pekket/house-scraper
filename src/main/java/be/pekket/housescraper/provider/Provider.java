package be.pekket.housescraper.provider;

public enum Provider {
    IMMOSCOOP("immoscoop"),
    ZIMMO("zimmo"),
    IMMOWEB("immoweb"),
    IMMOVLAN("immovlan"),
    TWEEDEHANDS("tweedehands"),
    REALO("realo");

    private final String provider;

    Provider(final String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public static Provider byValue(String value) {
        switch ( value ) {
            case "immoscoop":
                return IMMOSCOOP;
            case "zimmo":
                return ZIMMO;
            case "immoweb":
                return IMMOWEB;
            case "immovlan":
                return IMMOVLAN;
            case "tweedehands":
                return TWEEDEHANDS;
            case "realo":
                return REALO;
            default:
                return null;
        }
    }
}
