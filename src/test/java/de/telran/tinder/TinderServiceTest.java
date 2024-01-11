package de.telran.tinder;

import de.telran.tinder.entity.Person;
import de.telran.tinder.repository.TinderRepository;
import de.telran.tinder.service.TinderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TinderServiceTest {

    @Mock
    private TinderRepository tinderRepository;

    @InjectMocks
    private TinderServiceImpl tinderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPointsToUser() {
        // Arrange
        Person user = new Person();
        user.setId(1);
        user.setRating(50);

        // Stubbing repository method
        when(tinderRepository.findById(1)).thenReturn(java.util.Optional.of(user));

        // Act
        tinderService.addPointsToUser(1, 100);

        // Assert
        assertEquals(150, user.getRating());
        verify(tinderRepository, times(1)).save(user);
    }

    @Test
    public void testSubtractPointsFromUser() {
        // Arrange
        Person user = new Person();
        user.setId(1);
        user.setRating(150);

        // Stubbing repository method
        when(tinderRepository.findById(1)).thenReturn(java.util.Optional.of(user));

        // Act
        tinderService.subtractPointsFromUser(1, 100);

        // Assert
        assertEquals(50, user.getRating());
        verify(tinderRepository, times(1)).save(user);
    }
}