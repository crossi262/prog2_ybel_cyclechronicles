import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShopTest {
    private Shop shop;
    private Order order;

    @BeforeEach
    void setUp() {
        shop = new Shop();
        order = Mockito.mock(Order.class);
    }

    @Test
    void testAcceptOtherBikeNoOpenOrdersLessThanFiveTotal() {
        Mockito.when(order.getBikeType()).thenReturn("Mountain");
        Mockito.when(order.getCustomer().getOpenOrders()).thenReturn(0);
        assertTrue(shop.accept(order));
    }

    @Test
    void testRejectEBike() {
        Mockito.when(order.getBikeType()).thenReturn("E-Bike");
        assertFalse(shop.accept(order));
    }

    @Test
    void testRejectGravelBike() {
        Mockito.when(order.getBikeType()).thenReturn("Gravel");
        assertFalse(shop.accept(order));
    }

    @Test
    void testRejectCustomerHasOpenOrders() {
        Mockito.when(order.getBikeType()).thenReturn("Mountain");
        Mockito.when(order.getCustomer().getOpenOrders()).thenReturn(1);
        assertFalse(shop.accept(order));
    }

    @Test
    void testRejectTooManyTotalOrders() {
        for (int i = 0; i < 4; i++) {
            shop.accept(Mockito.mock(Order.class));
        }
        Mockito.when(order.getBikeType()).thenReturn("Mountain");
        assertFalse(shop.accept(order));
    }
}
