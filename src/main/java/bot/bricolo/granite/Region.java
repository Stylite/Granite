package bot.bricolo.granite;

import java.util.Arrays;
import java.util.List;

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

    static List<Region> getEurope () {
        return Arrays.asList(
                Region.AMSTERDAM,
                Region.EU_CENTRAL,
                Region.EU_WEST,
                Region.FRANKFURT,
                Region.LONDON,
                Region.RUSSIA,
                Region.VIP_AMSTERDAM,
                Region.VIP_EU_CENTRAL,
                Region.VIP_EU_WEST,
                Region.VIP_FRANKFURT,
                Region.VIP_LONDON
        );
    }

    static List<Region> getAmerica () {
        return Arrays.asList(
                Region.BRAZIL,
                Region.US_CENTRAL,
                Region.US_EAST,
                Region.US_SOUTH,
                Region.US_WEST,
                Region.VIP_BRAZIL,
                Region.VIP_US_CENTRAL,
                Region.VIP_US_EAST,
                Region.VIP_US_SOUTH,
                Region.VIP_US_WEST
        );
    }

    static List<Region> getAsia () {
        return Arrays.asList(
                Region.HONG_KONG,
                Region.JAPAN,
                Region.SINGAPORE,
                Region.SYDNEY,
                Region.VIP_JAPAN,
                Region.VIP_SINGAPORE,
                Region.VIP_SYDNEY
        );
    }

    static List<Region> getAfrica () {
        return Arrays.asList(
                Region.SOUTH_AFRICA,
                Region.VIP_SOUTH_AFRICA
        );
    }
}
