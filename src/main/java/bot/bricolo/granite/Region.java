package bot.bricolo.granite;

enum Region {
    AMSTERDAM("amsterdam", "Amsterdam", false),
    BRAZIL("brazil", "Brazil", false),
    EU_CENTRAL("eu-central", "EU Central", false),
    EU_WEST("eu-west", "EU West", false),
    FRANKFURT("frankfurt", "Frankfurt", false),
    HONG_KONG("hongkong", "Hong Kong", false),
    JAPAN("japan", "Japan", false),
    LONDON("london", "London", false),
    RUSSIA("russia", "Russia", false),
    SINGAPORE("singapore", "Singapore", false),
    SOUTH_AFRICA("southafrica", "South Africa", false),
    SYDNEY("sydney", "Sydney", false),
    US_CENTRAL("us-central", "US Central", false),
    US_EAST("us-east", "US East", false),
    US_SOUTH("us-south", "US South", false),
    US_WEST("us-west", "US West", false),

    VIP_AMSTERDAM("vip-amsterdam", "Amsterdam (VIP)", true),
    VIP_BRAZIL("vip-brazil", "Brazil (VIP)", true),
    VIP_EU_CENTRAL("vip-eu-central", "EU Central (VIP)", true),
    VIP_EU_WEST("vip-eu-west", "EU West (VIP)", true),
    VIP_FRANKFURT("vip-frankfurt", "Frankfurt (VIP)", true),
    VIP_JAPAN("vip-japan", "Japan (VIP)", true),
    VIP_LONDON("vip-london", "London (VIP)", true),
    VIP_SINGAPORE("vip-singapore", "Singapore (VIP)", true),
    VIP_SOUTH_AFRICA("vip-southafrica", "South Africa (VIP)", true),
    VIP_SYDNEY("vip-sydney", "Sydney (VIP)", true),
    VIP_US_CENTRAL("vip-us-central", "US Central (VIP)", true),
    VIP_US_EAST("vip-us-east", "US East (VIP)", true),
    VIP_US_SOUTH("vip-us-south", "US South (VIP)", true),
    VIP_US_WEST("vip-us-west", "US West (VIP)", true);

    private final String key;
    private final String name;
    private final boolean vip;

    Region(String key, String name, boolean vip) {
        this.key = key;
        this.name = name;
        this.vip = vip;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public boolean isVip() {
        return vip;
    }
}
