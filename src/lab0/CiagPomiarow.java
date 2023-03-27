package lab0;

import java.io.Serializable;

public class CiagPomiarow implements Serializable {

    Pomiar [] pomiary;

    public Pomiar[] getPomiary() {
        return pomiary;
    }

    public void setPomiary(Pomiar[] pomiary) {
        this.pomiary = pomiary;
    }

    public String toString(){
        String s = "";
        for (int i = 0; i < pomiary.length; i++) {
            s += pomiary[i].toString() + "\n";
        }
        return s;
    }
}
