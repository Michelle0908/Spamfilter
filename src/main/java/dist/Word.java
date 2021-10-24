package dist;

public class Word {

    private int cOfHam  =    0;
    private int cOfSpam =    0;

    public void incrementSpam() {
        this.cOfSpam++;
    }

    public void incrementHam() {
        this.cOfHam++;
    }

    public int getcOfHam() {
        return cOfHam;
    }

    public void setcOfHam(int cOfHam) {
        this.cOfHam = cOfHam;
    }

    public int getcOfSpam() {
        return cOfSpam;
    }

    public void setcOfSpam(int cOfSpam) {
        this.cOfSpam = cOfSpam;
    }
}
