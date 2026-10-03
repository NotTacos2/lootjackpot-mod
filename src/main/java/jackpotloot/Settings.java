package jackpotloot;

public class Settings {
    public static class CobblestoneAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }
    public static class DeepslateAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }
    public static class CoalAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class CopperAmount {
        // since copper ore is random, we could use this to simulate out the random value with limit, however it is inaccurate but works.
        public static int normal = Math.toIntExact((Math.round(Math.random() * 3)) + 2);
        public static int lucky = normal * 2; // it could be this idk lmao
        public static int jackpot = normal * 3;
    }

    public static class IronAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class LapisLazuilAmount {
        // since it's random as copper but a bit more so increasing two more would be kinda accurate
        public static int normal = Math.toIntExact((Math.round(Math.random() * 3)) + 4);
        public static int lucky = normal * 2;
        public static int jackpot = normal * 3;
    }

    public static class RedstoneAmount {
        // same as lapis
        public static int normal = Math.toIntExact((Math.round(Math.random() * 3)) + 4);
        public static int lucky = normal * 2;
        public static int jackpot = normal * 3;
    }

    public static class EmeraldAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class DiamondAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class GoldAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class NetherrackAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class NetherQuartzAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class AncientDebrisAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class GoldNuggetAmount {
        public static int normal = Math.toIntExact((Math.round(Math.random() * 3)) + 2);
        public static int lucky = normal * 2;
        public static int jackpot = normal * 3;
    }

    public static class GravelAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }

    public static class FlintAmount {
        public static int lucky = 2;
        public static int jackpot = 5;
        public static int normal = 1;
    }


}
