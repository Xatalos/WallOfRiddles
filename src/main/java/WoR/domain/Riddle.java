package WoR.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity
public class Riddle extends AbstractPersistable<Long> {

    @NotBlank
    private String creator;
    
    @NotBlank
    private String description;
    
    @NotBlank
    private String answer;
    
    private int correctGuesses;
    private int wrongGuesses;

    public String getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public String getAnswer() {
        return answer;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }
    
    
    
}
