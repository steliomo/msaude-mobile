package mz.co.txova.msaude.consultation.model;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class ConsultationType {

    private String consultationType;

    private int iconId;

    public ConsultationType(String consultationType, final int iconId) {
        this.consultationType = consultationType;
        this.iconId = iconId;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public int getIconId() {
        return iconId;
    }
}
