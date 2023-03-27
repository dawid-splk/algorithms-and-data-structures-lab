package lab1;

import java.util.Comparator;

public class IndexComp implements Comparator<StudentPodstawowy> {

    @Override
    public int compare(StudentPodstawowy s1, StudentPodstawowy s2){
        if(s1 == null)
            return s2 == null ? 0 : -1;
        if(s2 == null)
            return 1;

        if(s1.getNrIndeksu() > s2.getNrIndeksu())
            return 1;
        else
            return s1.getNrIndeksu() < s2.getNrIndeksu() ? -1 : 0;
    }
}
