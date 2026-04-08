package com.example.library.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PREMIUM")
@NoArgsConstructor
public class PremiumMember extends Member {
    public PremiumMember(String name) {
        super(name);
    }

    @Override
    public int getMaxBorrowingLimit() {
        return 10;
    }
}
