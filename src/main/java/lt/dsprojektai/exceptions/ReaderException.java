package lt.dsprojektai.exceptions;

public class ReaderException extends Exception {
    private String eCause;
    public ReaderException(String eCause){
        super();
        this.eCause = eCause;
    }

    public String geteCause(){
        return eCause;
    }
}
