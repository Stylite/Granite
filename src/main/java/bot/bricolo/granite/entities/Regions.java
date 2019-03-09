package bot.bricolo.granite.entities;

import net.dv8tion.jda.api.Region;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Regions {
    // Europe
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

    static List<Region> getClassicEurope () {
        return Arrays.asList(
                Region.AMSTERDAM,
                Region.EU_CENTRAL,
                Region.EU_WEST,
                Region.FRANKFURT,
                Region.LONDON,
                Region.RUSSIA
        );
    }

    static List<Region> getVIPEurope () {
        return Arrays.asList(
                Region.VIP_AMSTERDAM,
                Region.VIP_EU_CENTRAL,
                Region.VIP_EU_WEST,
                Region.VIP_FRANKFURT,
                Region.VIP_LONDON
        );
    }

    // America
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

    static List<Region> getClassicAmerica () {
        return Arrays.asList(
                Region.BRAZIL,
                Region.US_CENTRAL,
                Region.US_EAST,
                Region.US_SOUTH,
                Region.US_WEST
        );
    }

    static List<Region> getVIPAmerica () {
        return Arrays.asList(
                Region.VIP_BRAZIL,
                Region.VIP_US_CENTRAL,
                Region.VIP_US_EAST,
                Region.VIP_US_SOUTH,
                Region.VIP_US_WEST
        );
    }

    // Asia
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

    static List<Region> getClassicAsia () {
        return Arrays.asList(
                Region.HONG_KONG,
                Region.JAPAN,
                Region.SINGAPORE,
                Region.SYDNEY
        );
    }

    static List<Region> getVIPAsia () {
        return Arrays.asList(
                Region.VIP_JAPAN,
                Region.VIP_SINGAPORE,
                Region.VIP_SYDNEY
        );
    }

    // Africa
    static List<Region> getAfrica () {
        return Arrays.asList(
                Region.SOUTH_AFRICA,
                Region.VIP_SOUTH_AFRICA
        );
    }

    static List<Region> getClassicAfrica () {
        return Collections.singletonList(
                Region.SOUTH_AFRICA
        );
    }

    static List<Region> getVIPAfrica () {
        return Collections.singletonList(
                Region.VIP_SOUTH_AFRICA
        );
    }
}
