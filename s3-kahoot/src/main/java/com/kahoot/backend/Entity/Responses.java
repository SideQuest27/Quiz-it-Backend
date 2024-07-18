package com.kahoot.backend.Entity;
import com.kahoot.backend.DTO.ResultStatsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NamedNativeQuery(name = "Responses.questionResults",
                  query = "Select S.response AS response,  CAST(COUNT(S) AS INTEGER) AS votes FROM Responses S Where S.q_id = ?1 GROUP BY S.response ORDER BY response ASC",
                  resultSetMapping = "Mapping.ResultStatsDTO")
@SqlResultSetMapping(name = "Mapping.ResultStatsDTO",classes = @ConstructorResult(targetClass = ResultStatsDTO.class,
                     columns = {@ColumnResult(name = "response"),
                                @ColumnResult(name = "votes")}))

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Responses
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resId;
    private Character response;
    @ManyToOne
    @JoinColumn(name = "qId",referencedColumnName = "qId")
    private Question question;

    public Responses(Character response, Question question) {
        this.response = response;
        this.question = question;
    }
}
