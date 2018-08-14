package mz.co.msaude.mobile.consultation.model;

/**
 * Created by Stélio Moiane on 7/19/17.
 */
public enum Hour implements Availability {

    NINE_TO_NINE_HALF("9:00 - 09:30"),

    NINE_HALF_TO_TEN("09:30 - 10:00"),

    TEN_TO_TEN_HALF("10:00 - 10:30"),

    TEN_HALF_TO_ELEVEN("10:30 - 11:00"),

    ELEVEN_HALF_TO_TWELVE("11:30 - 12:00"),

    TWO_TO_TWO_HALF("14:00 - 14:30"),

    TWO_HALF_TO_THREE("14:30 - 15:00"),

    THREE_HALF_TO_FOUR("15:30 - 14:00"),

    FOUR_TO_FOUR_HALF("16:00 - 14:30"),

    FOUR_HALF_TO_FIVE("16:30 - 17:00");

    private String hour;

    private Hour(String hour) {
        this.hour = hour;
    }

    @Override
    public String getAvailability() {
        return this.hour;
    }
}
