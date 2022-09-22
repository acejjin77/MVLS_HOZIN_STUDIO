package MVLS.HOZIN.STUDIO.mvls.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Teacher {

    @Id @GeneratedValue
    @Column(name = "teacher_seq")
    private Long seq;

    private String name;
    private String about;
    private String youtubeURL;
    private String instagramURL;
    private String userReview;


    @OneToMany(mappedBy = "teacher")
    private List<Class> classes = new ArrayList<>();


}
