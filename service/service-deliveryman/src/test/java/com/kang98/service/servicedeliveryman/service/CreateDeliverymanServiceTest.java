package com.kang98.service.servicedeliveryman.service;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateDeliverymanServiceTest {

    @Mock
    private DeliverymenRepository deliverymenRepository;

    @InjectMocks
    private CreateDeliverymenService createDeliverymanService;

    @Test
    void createDeliveryman_callMethod_expectedDeliveryman() {
        when(deliverymenRepository.save(any())).thenReturn(Deliveryman.builder().build());
        assertAll("create deliveryman",
                () -> createDeliverymanService.createDeliveryman(Deliveryman.builder().build()),
                () -> verify(deliverymenRepository, times(1)).save(any(Deliveryman.class))
        );
    }
}
