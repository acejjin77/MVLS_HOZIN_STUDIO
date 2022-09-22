package MVLS.HOZIN.STUDIO.mvls.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Users {

    @Id @GeneratedValue
    @Column(name = "user_seq")
    private Long seq;
    private String name;
    private String id;
    private String pwd;
    private String hp;
    private String email;
    private String addr1;
    private String addr2;
    private String smsAgree;
    private String emailAgree;


}
