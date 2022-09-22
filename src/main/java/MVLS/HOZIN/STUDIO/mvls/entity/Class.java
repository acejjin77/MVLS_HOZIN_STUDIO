package MVLS.HOZIN.STUDIO.mvls.entity;


import MVLS.HOZIN.STUDIO.mvls.enums.Studio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "classes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Class {

    @Id @GeneratedValue
    @Column(name = "schedule_seq")
    private Long seq;

    private String date;
    private String startTime;
    private String endTime;
    private String currentHeadcount;
    private String maximumHeadcount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_seq")
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Studio studio;

}
