import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {


    @Test
    void everyBranchTest() {
        List<Item> allItems1 = null;
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems1, 100));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        List<Item> allItems2 = new ArrayList<>();
        allItems2.add(new Item(null, "1223", 350, 1.5f));
        allItems2.add(new Item("", "1223", 350, 1.5f));
        SILab2.checkCart(allItems2, 300);
        assertEquals("unknown", allItems2.get(0).getName());
        assertEquals("unknown", allItems2.get(1).getName());
        Item item1 = allItems2.get(0);
        Item item2 = allItems2.get(1);
        assertFalse(SILab2.checkCart(List.of(item1), 300));
        assertFalse(SILab2.checkCart(List.of(item2), 300));

        List<Item> allItems3 = new ArrayList<>();
        allItems3.add(new Item("item1", null, 200, 0f));
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems3, 100));
        assertTrue(ex.getMessage().contains("No barcode!"));

        List<Item> allItems4 = new ArrayList<>();
        allItems4.add(new Item("item1", "23code", 200, 0f));
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems4, 100));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        List<Item> allItems5 = new ArrayList<>();
        allItems5.add(new Item("item1", "0123", 350, 1.5f));
        assertTrue(SILab2.checkCart(allItems5, 500));
    }

    @Test
    void multipleConditionTest(){
        //можни комбинации
        // T && T && T
        List<Item> allItems1 = new ArrayList<>();
        allItems1.add(new Item("item1", "0123", 350, 1.5f));
        assertTrue(SILab2.checkCart(allItems1, 550));

        // T && T && F
        List<Item> allItems2 = new ArrayList<>();
        allItems2.add(new Item("item1", "1223", 350, 1.5f));
        assertTrue(SILab2.checkCart(allItems2, 550));

        // T && F && X
        List<Item> allItems3 = new ArrayList<>();
        allItems3.add(new Item("item1", "1223", 350, 0f));
        assertFalse(SILab2.checkCart(allItems3, 250));

        // F && X && X
        List<Item> allItems4 = new ArrayList<>();
        allItems4.add(new Item("item1", "01223", 250, 2.5f));
        assertFalse(SILab2.checkCart(allItems4, 550));

    }


}