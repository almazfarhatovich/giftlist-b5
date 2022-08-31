package kg.giftlist.giftlist.db.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "complaints")
@NoArgsConstructor
@Getter
@Setter
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_gen")
    @SequenceGenerator(name = "complaint_gen", sequenceName = "complaint_seq", allocationSize = 1)
    private Long id;

    private String text;

    @OneToOne
    private User fromUser;

    @OneToOne(cascade = CascadeType.ALL)
    private Wish wish;

    @OneToOne
    private Gift gift;
}
