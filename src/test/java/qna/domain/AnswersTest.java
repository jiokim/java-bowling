package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    Answers answers;
    QuestionTest questionTest;

    @BeforeEach
    void setUp() {
        answers = new Answers();
    }

    @Test
    @DisplayName("삭제성공_모든답변자와 일치")
    void delete_success() {
        answers.add(new Answer(0L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(1L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));

        User loginUser = UserTest.SANJIGI;
        assertThat(answers.isOwner(loginUser)).isTrue();
    }

    @Test
    @DisplayName("삭제실패_모든답변자와 불일치")
    void delete_fail() {
        answers.add(new Answer(0L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));

        User loginUser = UserTest.JAVAJIGI;
        assertThat(answers.isOwner(loginUser)).isFalse();
    }
}
