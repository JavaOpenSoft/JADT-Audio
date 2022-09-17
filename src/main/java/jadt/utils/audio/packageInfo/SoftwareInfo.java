package jadt.utils.audio.packageInfo;
import java.io.File;
import java.net.URISyntaxException;

public class SoftwareInfo {
    /**
    * @author Rishon Jonathan
     * @version 3.0 Beta 1
     * @Documentation https://github.com/RishonDev/Audio/wiki
    * */
    private static final String version = "v3.0";
    private static final String buildMode=  "Beta";
    private static final String buildDate = "2202.9.8";
    private static final String About = "Audio plugin for JUIT Library";
    private static final File icon;

    static {
        try {
            icon = new File(SoftwareInfo.class.getProtectionDomain().getCodeSource().getLocation().toURI() +"/src/main/java/juit/utils/Audio/Audio.png");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getVersion() {
        return version;
    }

    public static String getBuildMode() {
        return buildMode;
    }

    public static String getBuildDate() {
        return buildDate;
    }

    public static String getAbout() {
        return About;
    }

    public static File getIcon() {
        return icon;
    }
}