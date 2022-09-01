package com.elisarovani.helpdek.domain;

import com.elisarovani.helpdek.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.weaver.ast.Call;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Client extends Person {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Call> calls = new ArrayList<>();

    public Client() {

        super();
        addProfiles(Profile.CLIENT);
    }

    public Client(Integer id, String name, String email, String password) {
        super(id, name, email, password);

    }


    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }
}
