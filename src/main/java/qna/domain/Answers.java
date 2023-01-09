package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> values = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return this.values.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

    public List<Answer> getValues() {
        return Collections.unmodifiableList(values);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> list = new ArrayList<>();
        for (Answer answer : values) {
            list.add(answer.delete(loginUser));
        }
        return list;
    }
}
