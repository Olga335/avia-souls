package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketTimeComparator;

public class AviaSoulsTest {

    @Test
    public void shouldCompareTicketsByPrice() {
        Ticket t1 = new Ticket("Moscow", "Sochi", 5000, 10, 12);
        Ticket t2 = new Ticket("Moscow", "Sochi", 7000, 10, 12);

        Assertions.assertTrue(t1.compareTo(t2) < 0);
        Assertions.assertTrue(t2.compareTo(t1) > 0);
        Assertions.assertEquals(0, t1.compareTo(new Ticket("A", "B", 5000, 1, 2)));
    }

    @Test
    public void shouldSearchAndSortByDefaultPrice() {
        AviaSouls aviaSouls = new AviaSouls();
        Ticket t1 = new Ticket("Moscow", "Sochi", 8000, 10, 12);
        Ticket t2 = new Ticket("Moscow", "Sochi", 4000, 10, 12);
        Ticket t3 = new Ticket("Moscow", "Sochi", 6000, 10, 12);
        Ticket other = new Ticket("Moscow", "Kazan", 3000, 10, 12);

        aviaSouls.add(t1);
        aviaSouls.add(t2);
        aviaSouls.add(t3);
        aviaSouls.add(other);

        Ticket[] expected = {t2, t3, t1}; // 4000, 6000, 8000
        Ticket[] actual = aviaSouls.search("Moscow", "Sochi");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsByTime() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket shortFlight = new Ticket("A", "B", 1000, 10, 11); // 1 час
        Ticket longFlight = new Ticket("A", "B", 1000, 10, 15); // 5 часов

        Assertions.assertTrue(comparator.compare(shortFlight, longFlight) < 0);
        Assertions.assertTrue(comparator.compare(longFlight, shortFlight) > 0);
    }

    @Test
    public void shouldSearchAndSortByTimeComparator() {
        AviaSouls aviaSouls = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket t1 = new Ticket("Moscow", "Sochi", 8000, 10, 15); // 5 часов
        Ticket t2 = new Ticket("Moscow", "Sochi", 4000, 10, 11); // 1 час
        Ticket t3 = new Ticket("Moscow", "Sochi", 6000, 10, 13); // 3 часа

        aviaSouls.add(t1);
        aviaSouls.add(t2);
        aviaSouls.add(t3);

        Ticket[] expected = {t2, t3, t1}; // Сначала самый быстрый
        Ticket[] actual = aviaSouls.searchAndSortBy("Moscow", "Sochi", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}