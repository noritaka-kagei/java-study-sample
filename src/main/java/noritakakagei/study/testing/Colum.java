package noritakakagei.study.testing;

public class Colum {
    boolean passExam(int score) {
        if ( score < 60 ) return false;
        else return true;
    }

    boolean passExam2(int score) {
        return score < 60 ? false : true;
    }
}