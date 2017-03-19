package errors;

import java.util.ArrayList;
import java.util.List;

public class ListModif {

    public static void main(String[] args) {

        List<Pers> list = new ArrayList<>();

        for (int i = 1; i<6; i++) {
            list.add(new Pers("n_"+i));
        }

        for (Pers p : list) {
            p.setSur("sur_"+p.getName());
        }

        for (Pers p : list) {
            System.out.println("pers = " + p.getName() + ", " + p.getSur());
        }

    }
}
