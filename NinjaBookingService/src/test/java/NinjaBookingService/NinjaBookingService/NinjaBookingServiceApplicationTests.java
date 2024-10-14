package NinjaBookingService.NinjaBookingService;


import NinjaBookingService.bookingservice.exception.BookingNotFoundException;
import NinjaBookingService.bookingservice.model.Booking;
import NinjaBookingService.bookingservice.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    private BookingRepository bookingRepository;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingRepository = Mockito.mock(BookingRepository.class);
        bookingService = new BookingService(bookingRepository);
    }

    @Test
    void createBooking() {
        Booking booking = new Booking("user1", "hotel1", LocalDate.now(), LocalDate.now().plusDays(1), 2);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking createdBooking = bookingService.createBooking(booking);
        assertNotNull(createdBooking);
        assertEquals("user1", createdBooking.getUserId());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void getBookingById_NotFound() {
        when(bookingRepository.findById("unknownId")).thenReturn(Optional.empty());

        Exception exception = assertThrows(BookingNotFoundException.class, () -> {
            bookingService.getBookingById("unknownId");
        });

        assertEquals("Booking not found with id: unknownId", exception.getMessage());
    }

    // Additional tests for updateBooking, deleteBooking, etc.
}
