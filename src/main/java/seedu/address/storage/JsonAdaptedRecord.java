package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.AttendanceScore;
import seedu.address.model.record.ParticipationScore;
import seedu.address.model.record.Record;
import seedu.address.model.record.SubmissionScore;

public class JsonAdaptedRecord {

    // Match your JSON shape: { "score": <int> }
    public static final class ScoreWrapper {
        public final Integer score;
        @JsonCreator
        public ScoreWrapper(@JsonProperty("score") Integer score) {
            this.score = score;
        }
    }

    private final ScoreWrapper attendanceScore;     // may be null
    private final ScoreWrapper submissionScore;     // may be null
    private final ScoreWrapper participationScore;  // may be null

    @JsonCreator
    public JsonAdaptedRecord(
            @JsonProperty("attendanceScore") ScoreWrapper attendanceScore,
            @JsonProperty("submissionScore") ScoreWrapper submissionScore,
            @JsonProperty("participationScore") ScoreWrapper participationScore) {
        this.attendanceScore = attendanceScore;
        this.submissionScore = submissionScore;
        this.participationScore = participationScore;
    }

    public JsonAdaptedRecord(Record record) {
        this.attendanceScore = new ScoreWrapper(record.getAttendanceScore().getScore());
        this.participationScore = new ScoreWrapper(record.getParticipationScore().getScore());
        this.submissionScore = new ScoreWrapper(record.getSubmissionScore().getScore());

    }

    public Record toModelType() throws IllegalValueException {
        // If your app allows “missing” scores inside a record, decide defaults here.
        // Below: treat null as score 0 for att/sub, and 0 for participation too.
        // If you prefer true nulls, you’ll need nullable fields in Record (model change).
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


