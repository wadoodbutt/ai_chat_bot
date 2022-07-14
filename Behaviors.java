import javax.swing.*;

public class Behaviors {

    public Behaviors() {}

    public ImageIcon normalState() {
        ImageIcon ico = new ImageIcon(getClass().getResource("normal.png"));
        return ico;
    }

    public ImageIcon sad() {
        ImageIcon ico = new ImageIcon(getClass().getResource("sad.png"));
        return ico;
    }

    public ImageIcon mad() {
        ImageIcon ico = new ImageIcon(getClass().getResource("mad.png"));
        return ico;
    }

    public ImageIcon happy() {
        ImageIcon ico = new ImageIcon(getClass().getResource("happy.png"));
        return ico;
    }

    public ImageIcon confused() {
        ImageIcon ico = new ImageIcon(getClass().getResource("confused.png"));
        return ico;
    }

    public ImageIcon cocky() {
        ImageIcon ico = new ImageIcon(getClass().getResource("cocky.png"));
        return ico;
    }

    public ImageIcon cool() {
        ImageIcon ico = new ImageIcon(getClass().getResource("cool.png"));
        return ico;
    }

    public ImageIcon gethelp() {
        ImageIcon ico = new ImageIcon(getClass().getResource("gethelp.png"));
        return ico;
    }

    public ImageIcon jealous() {
        ImageIcon ico = new ImageIcon(getClass().getResource("jealous.png"));
        return ico;
    }

    public ImageIcon love() {
        ImageIcon ico = new ImageIcon(getClass().getResource("love.png"));
        return ico;
    }

    public ImageIcon secret() {
        ImageIcon ico = new ImageIcon(getClass().getResource("secret.png"));
        return ico;
    }

    public ImageIcon ssj() {
        ImageIcon ico = new ImageIcon(getClass().getResource("ssj.png"));
        return ico;
    }

    public ImageIcon surprised() {
        ImageIcon ico = new ImageIcon(getClass().getResource("surprised.png"));
        return ico;
    }

    public ImageIcon tearyeyes() {
        ImageIcon ico = new ImageIcon(getClass().getResource("tearyeyes.png"));
        return ico;
    }

    public ImageIcon wise() {
        ImageIcon ico = new ImageIcon(getClass().getResource("wise.png"));
        return ico;
    }

    public ImageIcon worried() {
        ImageIcon ico = new ImageIcon(getClass().getResource("worried.png"));
        return ico;
    }

    public ImageIcon getBehavior(String emotion) {
        switch(emotion) {
            case "HAPPY":
                return happy();
            case "SAD":
                return sad();
            case "CONFUSED":
                return confused();
            case "MAD":
                return mad();
            case "NORMAL":
                return normalState();
            case "WORRIED":
                return worried();
            case "TEARYEYES":
                return tearyeyes();
            case "WISE":
                return wise();
            case "SURPRISED":
                return surprised();
            case "SSJ":
                return ssj();
            case "LOVE":
                return love();
            case "COOL":
                return cool();
            case "COCKY":
                return cocky();
            case "GETHELP":
                return gethelp();
            case "JEALOUS":
                return jealous();
            case "SECRET":
                return secret();
        }
        return null;
    }

}
