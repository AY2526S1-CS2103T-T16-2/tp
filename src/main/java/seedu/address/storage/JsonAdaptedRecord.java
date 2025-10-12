package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionScore;

/**
 * Jackson-friendly version of {@link Record}.
 */
public class JsonAdaptedRecord {

    /**
     * A Jackson-friendly wrapper for a score value to match the JSON structure { "score": value }.
     */
    public static final class ScoreWrapper {
        public final Integer score;

        /**
         * Constructs a {@code ScoreWrapper} with the given score.
         *
         * @param score The integer value of the score.
         */
        @JsonCreator
        public ScoreWrapper(@JsonProperty("score") Integer score) {
            this.score = score;
        }
    }

    private final ScoreWrapper attendanceScore;
    private final ScoreWrapper submissionScore;
    private final ScoreWrapper participationScore;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given record details.
     * This constructor is used by Jackson to deserialize the JSON object.
     *
     * @param attendanceScore The attendance score from the JSON file.
     * @param submissionScore The submission score from the JSON file.
     * @param participationScore The participation score from the JSON file.
     */
    @JsonCreator
    public JsonAdaptedRecord(
            @JsonProperty("attendanceScore") ScoreWrapper attendanceScore,
            @JsonProperty("submissionScore") ScoreWrapper submissionScore,
            @JsonProperty("participationScore") ScoreWrapper participationScore) {
        this.attendanceScore = attendanceScore;
        this.submissionScore = submissionScore;
        this.participationScore = participationScore;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     *
     * @param record The source {@code Record} object to convert.
     */
    public JsonAdaptedRecord(Record record) {
        this.attendanceScore = new ScoreWrapper(record.getAttendanceScore().getScore());
        this.participationScore = new ScoreWrapper(record.getParticipationScore().getScore());
        this.submissionScore = new ScoreWrapper(record.getSubmissionScore().getScore());

    }

    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code Record} object.
     */
    public Record toModelType() {
        AttendanceScore att = attendanceScore == null
                ? new AttendanceScore(0)
                : new AttendanceScore(attendanceScore.score);

        SubmissionScore sub = submissionScore == null
                ? new SubmissionScore(0)
                : new SubmissionScore(submissionScore.score);

        ParticipationScore par = participationScore == null
                ? new ParticipationScore(0)
                : new ParticipationScore(participationScore.score);

        return new Record(att, sub, par);
    }
}


