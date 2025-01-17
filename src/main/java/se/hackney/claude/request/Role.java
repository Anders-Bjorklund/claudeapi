package se.hackney.claude.request;

public enum Role {
    ASSISTANT, USER, SYSTEM, EXAMPLE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    
}
