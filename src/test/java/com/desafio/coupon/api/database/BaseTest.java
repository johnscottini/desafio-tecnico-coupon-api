package com.desafio.coupon.api.database;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public abstract class BaseTest {
    protected EasyRandom random = new EasyRandom();
}
