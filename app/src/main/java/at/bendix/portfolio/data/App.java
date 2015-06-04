package at.bendix.portfolio.data;

/**
 * Created by bendix on 02/06/15.
 */
public class App {
    private int color;
    private String name;
    private String packageName;

    public App(int color, String name, String packageName) {
        this.color = color;
        this.name = name;
        this.packageName = packageName;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

}
