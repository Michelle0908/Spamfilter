package dist;

public class Word {

    private float cOfHam        =    0f;
    private float cOfSpam       =    0f;
    private float probOfSpam    =    0f;

    public void incrementSpam() {
        this.cOfSpam++;
    }

    public void incrementHam() {
        this.cOfHam++;
    }

    public float getcOfHam() {
        return cOfHam;
    }

    public void setcOfHam(int cOfHam) {
        this.cOfHam = cOfHam;
    }

    public float getcOfSpam() {
        return cOfSpam;
    }

    public void setcOfSpam(int cOfSpam) {
        this.cOfSpam = cOfSpam;
    }

    public void setcOfHam(float cOfHam) {
        this.cOfHam = cOfHam;
    }

    public void setcOfSpam(float cOfSpam) {
        this.cOfSpam = cOfSpam;
    }

    public float getProbOfSpam() {
        return probOfSpam;
    }

    public void setProbOfSpam(float probOfSpam) {
        this.probOfSpam = probOfSpam;
    }
}
