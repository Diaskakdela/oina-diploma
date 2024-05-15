package kz.oina.repository;

import java.util.UUID;

public interface ToyIdCountProjection {
    UUID getToyId();

    Integer getCount();
}
