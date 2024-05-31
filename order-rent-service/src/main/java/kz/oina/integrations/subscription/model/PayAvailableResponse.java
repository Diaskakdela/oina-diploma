package kz.oina.integrations.subscription.model;

public record PayAvailableResponse(Data data) {
    public record Data(String status) {
    }
}
