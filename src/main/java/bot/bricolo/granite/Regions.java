package bot.bricolo.granite;

import net.dv8tion.jda.api.Region;

import java.util.Arrays;
import java.util.List;

class Regions {
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
