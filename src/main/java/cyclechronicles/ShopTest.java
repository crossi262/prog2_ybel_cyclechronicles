package cyclechronicles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopTest {

    private Shop shop;

    @BeforeEach
    public void setUp() {
        shop = new Shop();
    }

    @Test
    public void testRepair_TakeOldestPendingOrder() {
        Order orderToRepair = mock(Order.class);
        when(shop.repair()).thenReturn(Optional.of(orderToRepair));

        Optional<Order> repairedOrder = shop.repair();

        assertTrue(repairedOrder.isPresent());
        assertEquals(orderToRepair, repairedOrder.get());
    }

    @Test
    public void testRepair_NoPendingOrders() {
        when(shop.repair()).thenReturn(Optional.empty());

        Optional<Order> repairedOrder = shop.repair();

        assertFalse(repairedOrder.isPresent());
    }

    @Test
    public void testDeliver_FindCompletedOrderForCustomer() {
        String customerName = "John Doe";
        Customer customer = mock(Customer.class);
        when(customer.getName()).thenReturn(customerName);

        Order completedOrder = mock(Order.class);
        when(completedOrder.getCustomer()).thenReturn(customer);

        shop.deliver(customerName);
        Optional<Order> deliveredOrder = shop.deliver(customerName);

        assertTrue(deliveredOrder.isPresent());
        assertEquals(completedOrder, deliveredOrder.get());
    }

    @Test
    public void testDeliver_NoCompletedOrderForCustomer() {
        String customerName = "Jane Smith";

        Optional<Order> deliveredOrder = shop.deliver(customerName);

        assertFalse(deliveredOrder.isPresent());
    }
}
